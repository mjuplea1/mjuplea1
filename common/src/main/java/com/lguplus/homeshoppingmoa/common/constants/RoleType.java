package com.lguplus.homeshoppingmoa.common.constants;

import java.util.Arrays;

public enum RoleType {

    // @formatter:off
    SUPERADMIN("superadmin", "슈퍼어드민"),
    OPERATOR("operator", "운영자"),
    ;
    // @formatter:on

    RoleType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final String code;

    private final String desc;

    public String code() {
        return code;
    }

    public static RoleType findByName(String name) {
        return Arrays.stream(RoleType.values())
                .filter(eventType -> eventType.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public String getName() {
        return this.desc;
    }

    @Override
    public String toString() {
        return String.format("code:%s, desc:%s", code(), getName());
    }

}
