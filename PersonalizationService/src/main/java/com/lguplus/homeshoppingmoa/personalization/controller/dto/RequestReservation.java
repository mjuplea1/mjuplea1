package com.lguplus.homeshoppingmoa.personalization.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "RequestReservation", description = "예약하기")
public class RequestReservation {

    private long mainProductId;
}
