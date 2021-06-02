package com.lguplus.homeshoppingmoa.config.openfeign;

import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import com.lguplus.homeshoppingmoa.common.sec.model.CustomUserDetailsHelper;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class JwtGenerator4InnerService {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            CustomUserDetailsHelper jwtHelper = new CustomUserDetailsHelper();
            requestTemplate.header(CommonConstants.TOKEN_HEADER, CommonConstants.TOKEN_PREFIX + jwtHelper.generateJwt4System());
        };
    }

}
