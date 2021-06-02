package com.lguplus.homeshoppingmoa.common.constants;

public enum BroadcastProductEventType {

    INSERT_UPDATE("insert_update", "상품 추가 및 변경"),
    DELETE("delete", "상품 삭제")
    ;

    private final String code;

    private final String desc;

    BroadcastProductEventType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

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
