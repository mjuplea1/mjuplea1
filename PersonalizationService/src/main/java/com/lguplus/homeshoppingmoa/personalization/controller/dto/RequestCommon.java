package com.lguplus.homeshoppingmoa.personalization.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "RequestCommon", description = "셋탑요청 공통")
public class RequestCommon {

    private String appVersion;
    private String stbVersion;
    private String stbModel;
}
