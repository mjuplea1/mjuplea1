package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import com.lguplus.homeshoppingmoa.common.utils.TextUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MainProductDto {

    private Long mainProductId;

    private String itemCd;

    private String broadcastDt;

    private String startTime;

    private String startDt;

    private String endDt;

    private String endTime;

    private int reviewCnt;

    private float reviewScore;

    private String productName;

    private Long price;

    private int month;

    private float discountRate;

    private String ars;

    private String productImgUrl;

    private String shareUrl;

    private String productBrand;

    private String programName;

    private Integer category1;

    private Integer category2;

    private Integer category3;

    private Integer category4;

    private Boolean soldOutYN;

    private Boolean saleYN;

    private Boolean freeShippingYN;

    private Boolean freeReturnYN;

    private Boolean freeSampleYN;

    private ShoppingChannelDto shoppingChannel;

    private Set<CardDto> cards;

    private Set<HostDto> showHosts;

    private Set<SubProductDto> subProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainProductDto)) return false;
        MainProductDto that = (MainProductDto) o;
        return Objects.equals(itemCd, that.itemCd)
                && Objects.equals(broadcastDt, that.broadcastDt) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime)
                && Objects.equals(productName, that.productName) && month == that.month && Objects.equals(price, that.price) && Objects.equals(productImgUrl, that.productImgUrl) && Objects.equals(shareUrl, that.shareUrl)

                && Objects.equals(productBrand, that.productBrand) && Objects.equals(programName, that.programName) && discountRate == that.discountRate
                && Objects.equals(soldOutYN, that.soldOutYN) && Objects.equals(saleYN, that.saleYN) && Objects.equals(freeShippingYN, that.freeShippingYN)

                && shoppingChannel.equals(that.shoppingChannel)
                && cards.equals(that.cards)
                && showHosts.equals(that.showHosts)
                && subProducts.equals(that.subProducts)

                && reviewCnt >= (int) (that.reviewCnt * 1.1) && Float.compare(that.reviewScore, reviewScore) == 0

                // && Objects.equals(category1, that.category1) && Objects.equals(category2, that.category2) && Objects.equals(category3, that.category3) && Objects.equals(category4, that.category4)
                // && Objects.equals(freeReturnYN, that.freeReturnYN) && Objects.equals(freeSampleYN, that.freeSampleYN)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                itemCd,
                broadcastDt, startTime, endTime,
                // reviewCnt, reviewScore,
                // category1, category2, category3, category4,
                productName, price, month, productImgUrl, shareUrl, productBrand, programName, discountRate,
                soldOutYN, saleYN, freeShippingYN,
                // freeReturnYN, freeSampleYN
                shoppingChannel,
                cards,
                showHosts,
                subProducts);
    }

    public MainProductDto self() {
        return this;
    }


    public MainProductDto(String broadcastDt, ShoppingChannelDto shoppingChannelDto, Item item) {
        this.itemCd = item.getItem_code();

        this.broadcastDt = broadcastDt;
        this.startTime = item.getStart_time();
        this.endTime = item.getEnd_time();

        this.startDt = this.broadcastDt + this.startTime;
        this.endDt = this.broadcastDt + this.endTime;

        this.month = item.getMonth();
        this.discountRate = item.getDiscount_rate();
        this.ars = "ARS NUMBER";

        // ProductDetail
        this.productName = TextUtils.getByteSubstr(item.getName(), 256);
        this.price = item.getPrice();
        this.productImgUrl = item.getImg();
        this.shareUrl = item.getM_url();

        // Review
        this.reviewCnt = item.getReview_count();
        this.reviewScore = item.getReview_score();

        // Brand
        if (item.getDetail() != null) {
            this.productBrand = TextUtils.getByteSubstr(item.getDetail().getBrand(), 256);
        }

        // ProgramName
        this.programName = TextUtils.getByteSubstr(item.getPg_title(), 256);

        // Category
        this.category1 = null;
        this.category2 = null;
        this.category3 = null;
        this.category4 = null;

        this.soldOutYN = item.isSoldout();
        this.saleYN = item.is_sale();
        this.freeShippingYN = item.getFree_shipping();
        this.freeReturnYN = null;
        this.freeSampleYN = null;

        this.shoppingChannel = shoppingChannelDto;

        // Cards
        this.cards = new HashSet<>();
        if (!item.getCard().isEmpty()) {
            this.cards = new HashSet<>(item.getCard());
        }
        if (!item.getCards().isEmpty()) {
            this.cards = new HashSet<>(item.getCards());
        }

        // Shopping Host
        this.showHosts = item.getShopping_host().stream()
                .map(str -> HostDto.builder()
                        .hostName(str)
                        .build())
                .collect(Collectors.toSet());

        this.subProducts = new HashSet<>();
    }

}
