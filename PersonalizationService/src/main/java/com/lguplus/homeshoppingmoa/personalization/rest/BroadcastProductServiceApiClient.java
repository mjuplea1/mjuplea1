package com.lguplus.homeshoppingmoa.personalization.rest;

import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.MainProductDto;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.config.openfeign.AuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "BroadcastProductServiceApiClient", url = "${homeshoppingmoa.subdomain.uri-root.broadcastproduct}", configuration = {AuthRequestInterceptor.class})
public interface BroadcastProductServiceApiClient {

    @GetMapping("/v1/mainproduct")
    CommonModel<List<MainProductDto>> find(@RequestParam List<Long> mainProductIdList);
}
