package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

public enum MainProductSearchType {
    // SingleCondition
    FREE("무료키워드"),
    MONTH("무이자개월수"),
    SHOWHOST("쇼호스트"),
    CHANNEL_NAME("방송사명"),
    CHANNEL_NUM("채널번호"),
    BRAND_NAME("브랜드명"),
    REVIEW("상품평점/상품평수"),

    // MultiCondition
    PRICE("가격"),
    DISCOUNT_RATE("할인율"),

    // CategoryCondition
    CATEGORY("카테고리");

    MainProductSearchType(String type) {
        this.type = type;
    }

    private String type;
}

