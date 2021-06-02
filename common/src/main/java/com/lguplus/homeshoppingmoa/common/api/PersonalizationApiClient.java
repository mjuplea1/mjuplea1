package com.lguplus.homeshoppingmoa.common.api;

import com.lguplus.homeshoppingmoa.config.openfeign.AuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "personalizationApiClient", url = "${homeshoppingmoa.subdomain.uri-root.personalization}", configuration = { AuthRequestInterceptor.class })
public interface PersonalizationApiClient {
}
