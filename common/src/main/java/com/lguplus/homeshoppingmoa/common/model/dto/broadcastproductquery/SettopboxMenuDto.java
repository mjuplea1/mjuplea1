package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproductquery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "SettopboxMenu", description = "셋톱박스 (앱)메뉴별 응답 DTO")
public class SettopboxMenuDto {

    private String menuId;

    private List<SettopboxContainerDto> container = new ArrayList<>();

}
