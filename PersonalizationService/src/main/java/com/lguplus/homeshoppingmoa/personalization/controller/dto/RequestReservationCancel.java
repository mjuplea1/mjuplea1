package com.lguplus.homeshoppingmoa.personalization.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "RequestReservationCancel", description = "예약취소")
@AllArgsConstructor
@NoArgsConstructor
public class RequestReservationCancel {

    private long mainProductId;
}
