package com.lguplus.homeshoppingmoa.common.constants;

/**
 * 컨테이너 유형
 */
public enum ContainerType {

	// @formatter:off
	AUTO("auto", "자동"),
	MANUAL("manual", "수동"),
    ;
    // @formatter:on

	ContainerType(String code, String desc) {
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
