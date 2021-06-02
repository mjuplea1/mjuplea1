package com.lguplus.homeshoppingmoa.common.api;

import com.lguplus.homeshoppingmoa.config.openfeign.AuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "serviceInfoApiClient", url = "${homeshoppingmoa.subdomain.uri-root.serviceinfo}", configuration = { AuthRequestInterceptor.class })
public interface ServiceInfoApiClient {
}
