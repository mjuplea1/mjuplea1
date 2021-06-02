package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
public class Item {

    @Schema(name = "item_code", description = "상품 코드")
    private String item_code;

    @Schema(name = "main", description = "메인 상품 여부")
    private Boolean main;

    @Schema(name = "priority", description = "우선 순위")
    private Integer priority;

    @Schema(name = "start_time", description = "방송 시작 시간")
    private String start_time;

    @Schema(name = "end_time", description = "방송 종료 시간")
    private String end_time;

    @Schema(name = "soldout", description = "품절 여부")
    private boolean soldout = true;

    @Schema(name = "name", description = "상품 이름")
    private String name;

    @Schema(name = "m_url", description = "상품 URL")
    private String m_url;

    @Schema(name = "img", description = "상품 이미지 경로 URL")
    private String img;

    @Schema(name = "org_price", description = "원가")
    private Long org_price;

    @Schema(name = "discount_rate", description = "할인율")
    private float discount_rate;

    @Schema(name = "price", description = "할인가")
    private Long price;

    @Schema(name = "free_shipping", description = "무료 배송 여부")
    private Boolean free_shipping;

    @Schema(name = "free_return", description = "무료 반품 여부")
    private Boolean free_return;

    @Schema(name = "free_sample", description = "무료 샘플 여부")
    private Boolean free_sample;

    @Schema(name = "review_count", description = "상품평 수")
    private int review_count;

    @Schema(name = "review_score", description = "상품평점")
    private float review_score;

    @Schema(name = "category1", description = "1차 카테고리")
    private String category1;

    @Schema(name = "category2", description = "2차 카테고리")
    private String category2;

    @Schema(name = "category3", description = "3차 카테고리")
    private String category3;

    @Schema(name = "category4", description = "4차 카테고리")
    private String category4;

    @Schema(name = "is_sale", description = "현재 판매중 여부")
    private boolean is_sale = false;

    @Schema(name = "month", description = "무이자 개월수")
    private int month;

    @Schema(name = "pg_title", description = "편성 프로그램명")
    private String pg_title;

    @Schema(name = "shopping_host", description = "쇼핑 호스트 리스트")
    private List<String> shopping_host;

    @Schema(name = "card", description = "청구할인 카드 정보")
    private List<CardDto> card = new ArrayList<>();

    @Schema(name = "card", description = "청구할인 카드 정보")
    private List<CardDto> cards = new ArrayList<>();

    @Schema(name = "detail", description = "상품 상세 정보")
    private ProductDetailInfo detail;
}
