package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailInfo {
    @Schema(name = "brand_name", description = "브랜드명")
    private String brand;
}
