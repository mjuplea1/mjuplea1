package com.lguplus.homeshoppingmoa.personalization.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "RequestRootBody", description = "예약기능 공통 RequestBody")
public class RequestRootBody<T> {

    private RequestCommon common;
    private T param;
}
