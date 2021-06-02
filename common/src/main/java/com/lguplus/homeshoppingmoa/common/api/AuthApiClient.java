package com.lguplus.homeshoppingmoa.common.api;

import com.lguplus.homeshoppingmoa.common.model.dto.auth.AuthCheckDto;
import com.lguplus.homeshoppingmoa.common.model.dto.auth.LoginDto;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authApiClient", url = "${homeshoppingmoa.subdomain.uri-root.operation}")
public interface AuthApiClient {

    /**
     * 로그인
     *
     * @param dto - 로그인 DTO
     * @return 인증토큰(authToken)
     */
    @PostMapping("/v1/auth/login")
    CommonModel login(@RequestBody LoginDto dto);

    /**
     * SMS 인증번호 요청
     *
     * @param authToken - 인증토큰
     */
    @PostMapping("/v1/auth/request-sms-otp")
    CommonModel requestSmsOtp(@RequestHeader("x-auth-token") String authToken);

    /**
     * 인증번호 확인(최종 인증)
     *
     * @param dto - 인증확인 DTO
     * @return JWT(accessToken)
     */
    @PostMapping("/v1/auth/final")
    CommonModel<String> authFinal(@RequestBody AuthCheckDto dto);

}
