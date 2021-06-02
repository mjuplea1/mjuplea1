package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

public enum ShoppingChannelSearchType {
    TOTAL("전체", "all"),
    CHANNEL_NUMBER("채널번호", "CHANNEL_NUM"),
    SERVICE_ID("서비스아이디", "CHANNEL_SERVICE_ID");

    ShoppingChannelSearchType(String type, String actual) {
        this.type = type;
        this.actual = actual;
    }

    private String type;

    private String actual;
}
