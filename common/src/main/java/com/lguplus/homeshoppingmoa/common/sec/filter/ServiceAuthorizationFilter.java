package com.lguplus.homeshoppingmoa.common.sec.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import com.lguplus.homeshoppingmoa.common.sec.model.CustomUserDetails;
import com.lguplus.homeshoppingmoa.common.sec.model.CustomUserDetailsHelper;
import static com.lguplus.homeshoppingmoa.common.utils.TextUtils.isEmpty;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
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
public class ServiceAuthorizationFilter extends BasicAuthenticationFilter {

    private final CustomUserDetailsHelper customUserDetailsHelper;
    private final ObjectMapper objectMapper;

    public ServiceAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

        this.customUserDetailsHelper = new CustomUserDetailsHelper();
        this.objectMapper = new JacksonMapperConfig().objectMapper();
    }

	@SuppressWarnings("rawtypes")
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        CommonModel commonModel = null;

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
			commonModel = new CommonModel<>(ResultCodeType.SERVER_ERROR_41001002);
        }
        catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			log.info("> JWT 형식 오류: {}", e.getMessage());
			commonModel = new CommonModel<>(ResultCodeType.SERVER_ERROR_41001001);
        }
        catch (RuntimeException e) {
			log.info("> JWT 인증 실패: {}", e.getMessage());
			commonModel = new CommonModel<>(ResultCodeType.SERVER_ERROR_41001001);
        }

        SecurityContextHolder.clearContext();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(this.objectMapper.writeValueAsString(commonModel));
        response.getWriter().flush();
    }

	@SuppressWarnings("deprecation")
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(CommonConstants.TOKEN_HEADER);
        log.debug("> token: {}", token);

		if (isEmpty(token)) {
            return null;
        }

		CustomUserDetails admin = this.customUserDetailsHelper.getSimpleUser(token);
        log.debug("> 인가, accountId: {}, username: {}, name: {}, roles: {}", admin.getAccountId(), admin.getUsername(), admin.getName(), admin.getAuthorities());

		return new UsernamePasswordAuthenticationToken(admin, "", admin.getAuthorities());
    }

}
