package com.lguplus.homeshoppingmoa.personalization.rest;

import com.lguplus.homeshoppingmoa.common.model.dto.RequestNcasAuthDto;
import com.lguplus.homeshoppingmoa.common.model.dto.ResponseNcasAuthDto;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.config.openfeign.JwtGenerator4InnerService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ncasServiceApiClient", url = "${homeshoppingmoa.subdomain.uri-root.ncas}", configuration = JwtGenerator4InnerService.class)
public interface NcasServiceApiClient {

    @PostMapping("/ncas/auth")
    CommonModel<ResponseNcasAuthDto> find(RequestNcasAuthDto requestNcasAuthDto);

}
