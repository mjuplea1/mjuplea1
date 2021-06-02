package com.lguplus.homeshoppingmoa.personalization.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(name = "ResponseReservations", description = "예약목록")
@ToString
@NoArgsConstructor
public class ResponseReservations implements Serializable {

    private List<ResponseReservation> reservations = new ArrayList<>();

    public ResponseReservations(List<ResponseReservation> reservations) {
        this.reservations = reservations;
    }
}
