package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Schema(name = "OrganizedProductDto", description = "편성상품 DTO")
@JsonIgnoreProperties(value = {
})
public class OrganizedProductDto implements Serializable {

    private static final long serialVersionUID = 2389323026496370460L;

    @Schema(name = "organizedId", description = "편성상품 id")
    private long organizedId;

    @Schema(name = "containerId", description = "컨테이너 id")
    private long containerId;

    @Schema(name = "mainProductId", description = "메인상품 id")
    private long mainProductId;

}
