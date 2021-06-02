package com.lguplus.homeshoppingmoa.common.constants;

/**
 * 상품 정렬 유형
 */
public enum ProductSortType {

	// @formatter:off
	BROADCAST_START_TIME("broadcast_start_time", "방송시간(종료임박순) 시작 순"),
	CHANNEL_ID("channel_id", "채널번호 순"),
    ;
    // @formatter:on

	ProductSortType(String code, String desc) {
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
