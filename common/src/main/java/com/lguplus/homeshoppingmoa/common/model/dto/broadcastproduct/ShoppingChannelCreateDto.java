package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @formatter: off
@JsonIgnoreProperties(value = {
        "channelId",
        "channelLogo",
        "redDt",
        "updDt"
})
// @formatter: on
@Schema(name = "ShoppingChannelCreateDto", description = "쇼핑 채널 등록 DTO")
public class ShoppingChannelCreateDto extends ShoppingChannelDto {
}
