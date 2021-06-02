package com.lguplus.homeshoppingmoa.personalization.controller.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "ResponseReservation", description = "예약내역")
@ToString
public class ResponseReservation {
    private long mainProductId;
    private String broadcastStartDt;
    private String broadcastEndDt;
    private String channelName;
    private String channelNumber;
    private String channelLogoUrl;
    private String serviceId;
    private String productImgUrl;
    private String productName;

    @Builder
    public ResponseReservation(long mainProductId, String broadcastStartDt, String broadcastEndDt, String channelName, String channelNumber, String channelLogoUrl, String serviceId, String productImgUrl, String productName) {
        this.mainProductId = mainProductId;
        this.broadcastStartDt = broadcastStartDt;
        this.broadcastEndDt = broadcastEndDt;
        this.channelName = channelName;
        this.channelNumber = channelNumber;
        this.channelLogoUrl = channelLogoUrl;
        this.serviceId = serviceId;
        this.productImgUrl = productImgUrl;
        this.productName = productName;
    }
}
