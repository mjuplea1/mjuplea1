package com.lguplus.homeshoppingmoa.common.constants;

/**
 * 앱메뉴 상태 유형
 */
public enum AppMenuStatusType {

	// @formatter:off
	PROD_OPEN("prod_open", "상용노출"),
	NON_OPEN("non_open", "비노출"),
    ;
    // @formatter:on

	AppMenuStatusType(String code, String desc) {
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
