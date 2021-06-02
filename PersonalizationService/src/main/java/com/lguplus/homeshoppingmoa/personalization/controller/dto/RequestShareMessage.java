package com.lguplus.homeshoppingmoa.personalization.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "RequestShareMessage", description = "공유문자발송요청")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestShareMessage {

    private long mainProductId;

    private String recvCtn;

    private String msgTitle;

    private String msgContent;
}
