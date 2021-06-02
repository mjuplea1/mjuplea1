package com.lguplus.homeshoppingmoa.personalization.service;

import com.lguplus.homeshoppingmoa.personalization.controller.dto.RequestReservation;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.RequestReservationCancel;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.ResponseAddReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    private final String macAddress = "00-E0-4C-68-0F-5D";
    private final String subNo = "01091332730";

    @Test
    void 정상적인_예약_테스트() {
        RequestReservation requestReservation = new RequestReservation();
        requestReservation.setMainProductId(19888);

        ResponseAddReservation successful = reservationService.add(macAddress, subNo, requestReservation);

        assertThat(successful).isNull();
    }

    @Test
    void 중복_예약_테스트() {
        // 새로 예약하려는 상품
        // 19881 = {
        //    "BROADCAST_DT" : "20210518"
        //    "START_TIME": "0226"
        //    "END_TIME": "0256",
        //  }

        // 기존 예약되있는 상품
        // 19999 = {
        //    "BROADCAST_DT" : "20210518"
        //    "START_TIME": "0230"
        //    "END_TIME": "0256",
        //  }
        RequestReservation requestReservation = new RequestReservation();
        requestReservation.setMainProductId(19881);

        ResponseAddReservation duplicated = reservationService.add(macAddress, subNo, requestReservation);

        // 19999
        assertThat(duplicated).isNotNull();

        System.out.println(duplicated.toString());
    }

    @Test
    void 예약내역_조회_테스트() {
        reservationService.find(macAddress, subNo).getReservations().forEach(System.out::println);
    }

    @Test
    void 예약목록_삭제_테스트() {
        reservationService.remove(macAddress,subNo, new RequestReservationCancel(19888));
    }
}