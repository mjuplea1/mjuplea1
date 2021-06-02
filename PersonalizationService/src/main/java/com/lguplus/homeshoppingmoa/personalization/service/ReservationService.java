package com.lguplus.homeshoppingmoa.personalization.service;

import com.lguplus.homeshoppingmoa.common.event.CustomEvent;
import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.MainProductDto;
import com.lguplus.homeshoppingmoa.common.model.dto.personalization.PushDto;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.*;
import com.lguplus.homeshoppingmoa.personalization.event.PushEventListener;
import com.lguplus.homeshoppingmoa.personalization.model.entity.Reservation;
import com.lguplus.homeshoppingmoa.personalization.model.entity.SettopBox;
import com.lguplus.homeshoppingmoa.personalization.repository.ReservationRepository;
import com.lguplus.homeshoppingmoa.personalization.repository.SettopBoxRepository;
import com.lguplus.homeshoppingmoa.personalization.rest.BroadcastProductServiceApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final BroadcastProductServiceApiClient broadcastProductServiceApiClient;
    private final PushEventListener pushEventListener;
    private final SettopBoxRepository settopBoxRepository;
    private final ReservationRepository reservationRepository;

    @Value("${gcp.storage.root-uri}")
    private String rootUri;

    /**
     * 예약 내역 조회
     * - 예약 내역 조회 전 현재 시각 기준으로 이미 지나간 예약은 삭제한다
     *
     * @param macAddress
     * @param subNo
     * @return ResponseReservations
     */
    @Transactional
    public ResponseReservations find(String macAddress, String subNo) {
        SettopBox settopBox = settopBoxRepository.findByStbMacAndStbSub(macAddress, subNo);

        removePastReservation(settopBox);

        List<Long> mainProductIdList = settopBox.getReservations().stream()
                .map(Reservation::getMainProductId)
                .collect(Collectors.toList());

        List<MainProductDto> mainProductDtos = getMainProductDtos(mainProductIdList);

        return makeResponseReservations(settopBox, mainProductDtos);
    }

    /**
     * 예약 단건 등록
     * - 시작시간 기준으로 전후 5분 범위 내의 기등록된 예약 방송상품이 있다면 기존 상품을 리턴한다(duplicate:true)
     *
     * @param macAddress
     * @param subNo
     * @param requestReservation
     * @return ResponseAddReservation
     */
    @Transactional
    public ResponseAddReservation add(String macAddress, String subNo, RequestReservation requestReservation) {
        List<Long> mainProductIdList = new ArrayList<>();
        mainProductIdList.add(requestReservation.getMainProductId());
        MainProductDto mainProductDto = getMainProductDtos(mainProductIdList).get(0);

        SettopBox settopBox = settopBoxRepository.findByStbMacAndStbSub(macAddress, subNo);

        if (settopBox.getReservations().size() >= 12) {
            // 예약 등록 거절
        }

        Reservation duplicatedReservation = isDuplicated(settopBox, mainProductDto);

        if (duplicatedReservation == null) {
            Reservation reservation = makeReservation(mainProductDto);
            reservation.addSettopBox(settopBox);
            settopBoxRepository.save(settopBox);

            return null;
        }

        return makeResponseAddReservation(duplicatedReservation);
    }

    /**
     * 예약 단건 삭제
     *
     * @param macAddress
     * @param subNo
     * @param dto
     */
    @Transactional
    public void remove(String macAddress, String subNo, RequestReservationCancel dto) {
        SettopBox settopBox = settopBoxRepository.findByStbMacAndStbSub(macAddress, subNo);
        removeReservation(settopBox, dto.getMainProductId());
    }

    /**
     * 방송 상품이 삭제됬을 시 해당 상품을 예약하고 있는 셋톱 박스 조회
     *
     * @param deletedMainProductIdList
     */
    @Transactional
    public void pushAlarmToTargets(List<Long> deletedMainProductIdList) {
        List<SettopBox> settopBoxes = reservationRepository.findByMainProductIdIn(deletedMainProductIdList).stream()
                .map(Reservation::getSettopBox)
                .distinct()
                .collect(Collectors.toList());

        List<PushDto> pushDtoList = new ArrayList<>();
        settopBoxes.forEach(settopBox -> {
            List<Long> reservations = settopBox.getReservations().stream()
                    .map(Reservation::getMainProductId)
                    .collect(Collectors.toList());

            reservations.retainAll(deletedMainProductIdList);
            reservations.forEach(mainProductId -> removeReservation(settopBox, mainProductId));

            PushDto pushDto = new PushDto();
            pushDto.setMacAddress(settopBox.getStbMac());
            pushDto.setSubNumber(settopBox.getStbSub());
            pushDto.setDeleted(reservations);

            pushDtoList.add(pushDto);
        });

        pushDtoList.forEach(pushDto -> pushEventListener.onPushEvent(new CustomEvent<>(pushDto)));
    }

    /**
     * 과거 예약 목록 삭제
     *
     * @param settopBox
     */
    private void removePastReservation(SettopBox settopBox) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        List<Reservation> invalidReservations = settopBox.getReservations().stream()
                .filter(reservation -> {
                    LocalDateTime reservationTime = LocalDateTime.parse(reservation.getStartDt(), formatter);
                    return !reservationTime.isAfter(now);
                })
                .collect(Collectors.toList());

        settopBox.removeReservations(invalidReservations);
        settopBoxRepository.save(settopBox);
    }

    /**
     * 예약 단건 삭제
     *
     * @param settopBox
     * @param mainProductId
     */
    private void removeReservation(SettopBox settopBox, long mainProductId) {
        List<Reservation> deleteReservation = settopBox.getReservations().stream()
                .filter(reservation -> reservation.getMainProductId() == mainProductId)
                .collect(Collectors.toList());
        settopBox.removeReservations(deleteReservation);
        settopBoxRepository.save(settopBox);
    }

    private ResponseReservations makeResponseReservations(SettopBox settopBox, List<MainProductDto> mainProductDtos) {
        Map<Long, MainProductDto> mainProductDtoMap = mainProductDtos.stream()
                .collect(Collectors.toMap(MainProductDto::getMainProductId, MainProductDto::self));

        List<ResponseReservation> responseReservations = settopBox.getReservations().stream()
                .map(reservation -> {
                    MainProductDto dto = mainProductDtoMap.get(reservation.getMainProductId());

                    return ResponseReservation.builder()
                            .mainProductId(dto.getMainProductId())
                            .broadcastStartDt(dto.getStartDt())
                            .broadcastEndDt(dto.getEndDt())
                            .channelName(dto.getShoppingChannel().getChannelName())
                            .channelNumber(String.valueOf(dto.getShoppingChannel().getChannelNum()))
                            .channelLogoUrl(rootUri + "/" + dto.getShoppingChannel().getChannelLogoUrl())
                            .serviceId(dto.getShoppingChannel().getChannelServiceId())
                            .productImgUrl(dto.getProductImgUrl())
                            .productName(dto.getProductName())
                            .build();
                }).collect(Collectors.toList());

        return new ResponseReservations(responseReservations);
    }

    private Reservation makeReservation(MainProductDto mainProductDto) {
        return Reservation.builder()
                .mainProductId(mainProductDto.getMainProductId())
                .broadcastDt(mainProductDto.getBroadcastDt())
                .startTime(mainProductDto.getStartTime())
                .endTime(mainProductDto.getEndTime())
                .build();
    }

    private ResponseAddReservation makeResponseAddReservation(Reservation originReservation) {
        List<Long> mainProductIdList = new ArrayList<>();
        mainProductIdList.add(originReservation.getMainProductId());
        MainProductDto originMainProductDto = getMainProductDtos(mainProductIdList).get(0);

        return ResponseAddReservation.builder()
                .duplicate(true)
                .mainProductId(originReservation.getMainProductId())
                .broadcastStartDt(originReservation.getStartDt())
                .broadcastEndDt(originReservation.getEndDt())
                .productName(originMainProductDto.getProductName())
                .channelName(originMainProductDto.getShoppingChannel().getChannelName())
                .channelNumber(String.valueOf(originMainProductDto.getShoppingChannel().getChannelNum()))
                .build();
    }

    /**
     * 예약하려는 방송상품 시작시간 기준으로 전후 5분 범위내 겹치는 예약이 있는지 확인
     *
     * @param settopBox
     * @param mainProductDto
     * @return if duplicated, then return Reservation else return null
     */
    private Reservation isDuplicated(SettopBox settopBox, MainProductDto mainProductDto) {
        // 중복 여부 확인 후 중복이라면 기존상품 리턴
        AtomicReference<Reservation> duplicatedReservation = new AtomicReference<>();
        settopBox.getReservations()
                .forEach(reservation -> {
                    LocalDateTime reservationTime = convertTimeFormat(mainProductDto.getStartDt());
                    LocalDateTime minus5Min = convertTimeFormat(reservation.getStartDt()).minusMinutes(6);
                    LocalDateTime plus5Min = convertTimeFormat(reservation.getStartDt()).plusMinutes(6);

                    if (reservationTime.isAfter(minus5Min) && reservationTime.isBefore(plus5Min)) {
                        duplicatedReservation.set(reservation);
                    }
                });

        return duplicatedReservation.get();
    }

    private LocalDateTime convertTimeFormat(String dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.parse(dateTime, dateFormat);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<MainProductDto> getMainProductDtos(List<Long> mainProductIdList) {
        if (mainProductIdList.size() == 0) {
            return new ArrayList<>();
        }

        CommonModel<List<MainProductDto>> commonModel = broadcastProductServiceApiClient.find(mainProductIdList);

        return commonModel.getResult();
    }

}
