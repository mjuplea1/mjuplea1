package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShoppingChannelFindDto {

    @Schema(name = "type", description = "쇼핑채널 검색 타입")
    private ShoppingChannelSearchType type;

    @Schema(name = "condition", description = "쇼핑채널 검색 조건")
    private String condition;
}
