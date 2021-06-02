package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproductquery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "Card", description = "카드")
public class CardDto implements Serializable {

    @Schema(name = "cardName", description = "할인카드명")
    private String cardName;

    @Schema(name = "discountRate", description = "할인율")
    private float discountRate;

}
