package com.lguplus.homeshoppingmoa.personalization.controller;

import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.StbInfo;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import com.lguplus.homeshoppingmoa.config.swagger.OpenApiConfig;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.*;
import com.lguplus.homeshoppingmoa.personalization.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "예약 API", description = "")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/stb/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ReservationController {

    private ReservationService reservationService;

    @Operation(
            summary = "예약목록 조회",
            description = "예약목록을 조회한다.",
            security = {
                    @SecurityRequirement(name = OpenApiConfig.HEADER_NAME_AUTHORIZATION)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseReservations.class)))
            }
    )
    @PostMapping("/reservation/list")
    public <U extends NcasUserDetails> ResponseEntity<ResponseReservations> list(@AuthenticationPrincipal U principal, RequestRootBody<?> requestRootBody) {
        StbInfo stbUserInfo = principal.getPayload().getParam();

        return ResponseEntity.ok(reservationService.find(stbUserInfo.getMacAddress(), stbUserInfo.getSubNo()));
    }

    @Operation(
            summary = "방송 예약",
            description = "방송을 예약한다.",
            security = {
                    @SecurityRequirement(name = OpenApiConfig.HEADER_NAME_AUTHORIZATION)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseAddReservation.class)))
            }
    )
    @PostMapping("/reservation/add")
    public <U extends NcasUserDetails> ResponseEntity<ResponseAddReservation> reservation(@AuthenticationPrincipal U principal, RequestRootBody<RequestReservation> requestRootBody) {
        StbInfo stbUserInfo = principal.getPayload().getParam();
        RequestReservation requestReservation = requestRootBody.getParam();

        return ResponseEntity.ok(reservationService.add(stbUserInfo.getMacAddress(), stbUserInfo.getSubNo(), requestReservation));
    }

    @Operation(
            summary = "예약취소",
            description = "예약을 취소한다.",
            security = {
                    @SecurityRequirement(name = OpenApiConfig.HEADER_NAME_AUTHORIZATION)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success")
            }
    )
    @PostMapping("/reservation/cancel")
    public <U extends NcasUserDetails> void cancel(@AuthenticationPrincipal U principal, RequestRootBody<RequestReservationCancel> requestRootBody) {
        StbInfo stbUserInfo = principal.getPayload().getParam();
        RequestReservationCancel requestReservationCancel = requestRootBody.getParam();

        reservationService.remove(stbUserInfo.getMacAddress(), stbUserInfo.getSubNo(), requestReservationCancel);
    }
}
