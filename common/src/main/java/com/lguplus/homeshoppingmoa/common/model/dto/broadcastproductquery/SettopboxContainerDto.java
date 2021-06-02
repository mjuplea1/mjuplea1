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
@Schema(name = "SettopboxContainer", description = "셋톱박스 컨테이너별 응답 DTO")
public class SettopboxContainerDto {

    private String containerId;

    private List<ProductDto> product = new ArrayList<>();

}
