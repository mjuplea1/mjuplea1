package com.lguplus.homeshoppingmoa.common.sec.ncas.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.response.StbCommonModel;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetailsHelper;
import static com.lguplus.homeshoppingmoa.common.utils.TextUtils.isEmpty;
import com.lguplus.homeshoppingmoa.config.jackson.JacksonMapperConfig;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class NcasAuthorizationFilter extends BasicAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final NcasUserDetailsHelper ncasUserDetailsHelper;

    public NcasAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

        this.objectMapper = new JacksonMapperConfig().objectMapper();
        this.ncasUserDetailsHelper = new NcasUserDetailsHelper();
    }

	@SuppressWarnings("rawtypes")
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        StbCommonModel stbCommonModel = null;

        try {
            UsernamePasswordAuthenticationToken authentication = this.getAuthentication(request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
            return;
        }
        catch (ExpiredJwtException e) {
			log.info("> JWT 유효기한 만료: {}", e.getMessage());
			stbCommonModel = new StbCommonModel<>(ResultCodeType.STB_ERROR_3A100002);
        }
        catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			log.info("> JWT 형식 오류: {}", e.getMessage());
			stbCommonModel = new StbCommonModel<>(ResultCodeType.STB_ERROR_3A100003);
        }
        catch (RuntimeException e) {
			log.info("> JWT 인증 실패: {}", e.getMessage());
			stbCommonModel = new StbCommonModel<>(ResultCodeType.STB_ERROR_3A100001);
        }

        SecurityContextHolder.clearContext();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(this.objectMapper.writeValueAsString(stbCommonModel));
        response.getWriter().flush();
    }

	@SuppressWarnings("deprecation")
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(CommonConstants.TOKEN_HEADER);
        log.debug("> token: {}", token);

		if (isEmpty(token)) {
            return null;
        }

		NcasUserDetails ncasUser = this.ncasUserDetailsHelper.getSimpleUser(token);
        log.debug("> NCAS 인가 확인, macAddress: {}, payload: {}, roles: {}", ncasUser.getUsername(), ncasUser.getPayload(), ncasUser.getAuthorities());

		return new UsernamePasswordAuthenticationToken(ncasUser, "", ncasUser.getAuthorities());
    }

}
