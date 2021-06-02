package com.lguplus.homeshoppingmoa.personalization.service;

import com.lguplus.homeshoppingmoa.common.event.CustomEvent;
import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.MainProductDto;
import com.lguplus.homeshoppingmoa.common.model.dto.personalization.SharedMessageDto;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.RequestShareMessage;
import com.lguplus.homeshoppingmoa.personalization.event.MessageEventListener;
import com.lguplus.homeshoppingmoa.personalization.model.entity.ShareMsgHistory;
import com.lguplus.homeshoppingmoa.personalization.repository.ShareMsgHistoryRepository;
import com.lguplus.homeshoppingmoa.personalization.rest.BroadcastProductServiceApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageEventListener messageEventListener;
    private final BroadcastProductServiceApiClient broadcastProductServiceApiClient;
    private final ShareMsgHistoryRepository shareMsgHistoryRepository;

    private final int LENGTH_LIMIT = 80;

    @Transactional
    public void sendSharedMessage(String macAddress, String subNo, RequestShareMessage requestShareMessage) {
        // MainProductDto mainProductDto = getMainProductDto(requestShareMessage.getMainProductId());

        SharedMessageDto sharedMessageDto = SharedMessageDto.builder()
                .macAddress(macAddress)
                .subNo(subNo)
                .recvCtn(requestShareMessage.getRecvCtn())
                .mainProductId(requestShareMessage.getMainProductId())
                .msgTitle(requestShareMessage.getMsgTitle())
                .msgContent(requestShareMessage.getMsgContent())
                .build();

        if (isSMS(sharedMessageDto.getMsgContent())) {
            messageEventListener.onSmsMessageEvent(new CustomEvent<>(sharedMessageDto));
            return;
        }

        messageEventListener.onMMSMessageEvent(new CustomEvent<>(sharedMessageDto));

        saveShareMessageHistory(sharedMessageDto);
    }

    private void saveShareMessageHistory(SharedMessageDto sharedMessageDto) {
        ShareMsgHistory shareMsgHistory = new ShareMsgHistory();
        shareMsgHistory.setStbMac(sharedMessageDto.getMacAddress());
        shareMsgHistory.setStbSub(sharedMessageDto.getSubNo());
        shareMsgHistory.setReceiver(sharedMessageDto.getRecvCtn());
        shareMsgHistory.setTitle(sharedMessageDto.getMsgTitle());
        shareMsgHistory.setContent(sharedMessageDto.getMsgContent());

        shareMsgHistoryRepository.save(shareMsgHistory);
    }

    private MainProductDto getMainProductDto(long mainProductId) {
        List<Long> mainProductIdList = new ArrayList<>();
        mainProductIdList.add(mainProductId);

        return broadcastProductServiceApiClient.find(mainProductIdList).getResult().get(0);
    }

    private boolean isSMS(String msg) {
        return msg.getBytes(StandardCharsets.UTF_8).length <= LENGTH_LIMIT;
    }
}
