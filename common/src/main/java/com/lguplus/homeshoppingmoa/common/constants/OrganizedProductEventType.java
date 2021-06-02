package com.lguplus.homeshoppingmoa.common.constants;

import java.util.Arrays;

public enum OrganizedProductEventType {

    // @formatter:off
    MENU_ADDED("menu_added", "메뉴 추가"),
    MENU_MODIFIED("menu_modified", "메뉴 변경"),
    MENU_DELETED("menu_deleted", "메뉴 삭제"),

    CONTAINER_ADDED("container_added", "컨테이너 추가"),
    CONTAINER_MODIFIED("container_modified", "컨테이너 변경"),
    CONTAINER_DELETED("container_deleted", "컨테이너 삭제"),
    ;
    // @formatter:on

    OrganizedProductEventType(String code, String desc) {
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

    public static OrganizedProductEventType findByCode(String code) {
		// @formatter:off
        return Arrays.stream(OrganizedProductEventType.values())
                .filter(eventType -> eventType.code().equals(code))
                .findAny()
                .orElse(null);
        // @formatter:on
    }

    @Override
    public String toString() {
        return String.format("code:%s, desc:%s", code(), desc());
    }

}
