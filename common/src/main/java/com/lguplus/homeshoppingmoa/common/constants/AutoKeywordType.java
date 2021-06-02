package com.lguplus.homeshoppingmoa.common.constants;

/**
 * 자동검색어 유형
 */
public enum AutoKeywordType {

	// @formatter:off
	TOP15("top15", "TOP15"),
	RECENT_SHOWN_PRODUCT("recent_shown_product", "최근본상품"),
	PRICE("price", "가격"),
	FREE("free", "무료"),
	SHOW_HOST("show_host", "쇼호스트"),
	POPULATED_PROGRAM("populated_program", "인기프로그램"),
	PRODUCT_GRADE("product_grade", "상품평점"),
	INTEREST_FREE_MONTHS("interest_free_months", "무이자개월수"),
	DISCOUNT_RATE("discount_rate", "할인율"),
	BROADCASTING_COMPANY_NAME("broadcasting_company_name", "방송사명"),
	CHANNEL_ID("channel_id", "채널번호"),
	BRAND("brand", "브랜드"),
	CATEGORY("category", "카테고리"),
    ;
    // @formatter:on

	AutoKeywordType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private final String code;

	private final String desc;

	public String code() {
		return code;
	}

	public String desc() {
		return desc;
	}

	@Override
	public String toString() {
		return String.format("code:%s, desc:%s", code(), desc());
	}

}
