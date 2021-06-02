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
@Schema(name = "ShareLink", description = "공유링크")
public class ShareLinkDto implements Serializable {

    @Schema(name = "productName", description = "상품명")
    private String productName;

    @Schema(name = "price", description = "할인가격")
    private long price;

    @Schema(name = "imgUrl", description = "상품이미지 URL")
    private String imgUrl;

    @Schema(name = "lnkUrl", description = "링크 URL")
    private String lnkUrl;

}
