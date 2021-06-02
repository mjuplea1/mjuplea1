package com.lguplus.homeshoppingmoa.personalization.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.Payload;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.response.StbCommonModel;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetailsHelper;
import com.lguplus.homeshoppingmoa.config.jackson.JacksonMapperConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 인증(로그인) 필터
 */
@Slf4j
public class NcasAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final NcasUserDetailsHelper ncasUserDetailsHelper;

    public NcasAuthenticationFilter(AuthenticationManager authenticationManager) {
        String authUri = "/api/stb/v1/authentication";
        super.setFilterProcessesUrl(authUri);

        this.authenticationManager = authenticationManager;
        this.objectMapper = new JacksonMapperConfig().objectMapper();
        this.ncasUserDetailsHelper = new NcasUserDetailsHelper();
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Payload payload = this.objectMapper.readValue(request.getInputStream(), Payload.class);
        /*
         * 요청
        {
            "common":{
                "appVersion":"1.0",
                "stbVersion":"1.0",
                "stbModel":"UHD1"
            },
            "param":{
                "macAddress":"00-00-00-00"
            }
        }
         */
        log.debug("> NCAS 인증 시도: {}", payload.toString());

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(payload, "");

        // NcasAuthenticationProvider 호출
        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws IOException {
		// loadUserByUsername이 반환한 UserDetails(NcasUserDetails)
    	NcasUserDetails ncasUser = (NcasUserDetails) authentication.getPrincipal();
        log.debug("> NCAS 인증 성공, macAddress: {}, payload: {}, roles: {}", ncasUser.getUsername(), ncasUser.getPayload(), ncasUser.getAuthorities());

        // JWT 생성
        String accessToken = this.ncasUserDetailsHelper.generateJwt(ncasUser);
        /*
         * 응답
         {
             "common":{
                 "code":"2S000000",
                 "msg":""
             },
             "resultData":{
                 "accessToken":"eyJhbGciOiJFUzI1NiIsImtpZCI6IktleSBJRCJ9...."
             }
         }
         */

        Map<String, Object> map = new HashMap<>();
        map.put("accessToken", accessToken);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(this.objectMapper.writeValueAsString(new StbCommonModel<>(map)));
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.info("> NCAS 인증 실패: {}", failed.toString());

        SecurityContextHolder.clearContext();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(this.objectMapper.writeValueAsString(new StbCommonModel<>(ResultCodeType.STB_ERROR_3A301003)));
        response.getWriter().flush();
    }

}
