package com.lguplus.homeshoppingmoa.personalization.controller.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "ResponseAddReservation", description = "예약하기")
@NoArgsConstructor
@ToString
public class ResponseAddReservation {

    private boolean duplicate;
    private long mainProductId;
    private String broadcastStartDt;
    private String broadcastEndDt;
    private String productName;
    private String channelName;
    private String channelNumber;

    @Builder
    public ResponseAddReservation(boolean duplicate, long mainProductId, String broadcastStartDt, String broadcastEndDt, String productName, String channelName, String channelNumber) {
        this.duplicate = duplicate;
        this.mainProductId = mainProductId;
        this.broadcastStartDt = broadcastStartDt;
        this.broadcastEndDt = broadcastEndDt;
        this.productName = productName;
        this.channelName = channelName;
        this.channelNumber = channelNumber;
    }
}
