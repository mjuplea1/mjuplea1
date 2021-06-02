package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproductquery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.MainProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIgnoreProperties(value = {
        "itemCd",
        "broadcastDt",
        "startTime",
        "startDt",
        "endDt",
        "endTime",
        "reviewCnt",
        "discountRate",
        "shareUrl",
        "productBrand",
        "category1",
        "category2",
        "category3",
        "category4",
        "soldOutYN",
        "saleYN",
        "freeShippingYN",
        "freeReturnYN",
        "freeSampleYN",
        "shoppingChannel",
        "cards",
        "showHosts",
        "subProducts"
})
@Schema(name = "Product", description = "방송상품")
public class ProductDto extends MainProductDto implements Serializable {

    @Schema(name = "itemCode", description = "아이템코드")
    private String itemCode;

    @Schema(name = "broadcastStartDt", description = "방송시작시간")
    private String broadcastStartDt;

    @Schema(name = "broadcastEndDt", description = "방송종료시간")
    private String broadcastEndDt;

    @Schema(name = "channelName", description = "채널명")
    private String channelName;

    @Schema(name = "channelNumber", description = "채널번호")
    private String channelNumber;

    @Schema(name = "channelLogoUrl", description = "채널로고")
    private String channelLogoUrl;

    @Schema(name = "serviceId", description = "채널번호")
    private String serviceId;

    @Schema(name = "recommend", description = "추천채널 여부")
    private boolean recommend;

    @Schema(name = "orgPrice", description = "가격")
    private long orgPrice;

    @Schema(name = "price", description = "할인가격")
    private Long price;

    @Schema(name = "card", description = "청구할인카드정보")
    private List<CardDto> card = new ArrayList<>();

    @Schema(name = "mUrl", description = "공유링크 목록")
    private List<ShareLinkDto> mUrl = new ArrayList<>();

    @Schema(name = "freeShipping", description = "무료배송여부")
    private Boolean freeShipping;

    @Schema(name = "freeReturn", description = "무료반품여부")
    private Boolean freeReturn;

    @Schema(name = "freeSample", description = "무료샘플여부")
    private Boolean freeSample;

    @Schema(name = "isSale", description = "판매여부")
    private Boolean isSale;

    public void mappingData(List<Long> rcmdChannelIds) {
        this.itemCode = super.getItemCd();
        this.broadcastStartDt = super.getStartDt();
        this.broadcastEndDt = super.getEndDt();

        this.channelName = super.getShoppingChannel().getChannelName();
        this.channelLogoUrl = super.getShoppingChannel().getChannelLogoUrl();
        this.channelNumber = String.valueOf(super.getShoppingChannel().getChannelNum());
        this.serviceId = super.getShoppingChannel().getChannelServiceId();

        if (rcmdChannelIds != null && !rcmdChannelIds.isEmpty()
                && rcmdChannelIds.contains(super.getShoppingChannel().getChannelId())) {
            this.recommend = true;
        }

        this.orgPrice = super.getPrice();
        this.price = (long) (this.orgPrice * super.getDiscountRate());

        this.card = super.getCards().stream()
                .map(c -> CardDto.builder().cardName(c.getName()).discountRate(c.getDiscount_rate()).build())
                .collect(Collectors.toList());

        List<ShareLinkDto> shareLinks = super.getSubProducts().stream()
                .map(sp -> ShareLinkDto.builder().productName(sp.getProductName()).price(sp.getPrice()).imgUrl(sp.getProductImgUrl()).lnkUrl(sp.getShareUrl()).build())
                .collect(Collectors.toList());

        ShareLinkDto shareLink = ShareLinkDto.builder()
                .productName(super.getProductName())
                .price(super.getPrice())
                .imgUrl(super.getProductImgUrl())
                .lnkUrl(super.getShareUrl())
                .build();

        this.mUrl.add(shareLink);
        this.mUrl.addAll(shareLinks);

        this.freeShipping = super.getFreeShippingYN();
        this.freeReturn = super.getFreeReturnYN();
        this.freeSample = super.getFreeSampleYN();
        this.isSale = super.getSaleYN();
    }

}
