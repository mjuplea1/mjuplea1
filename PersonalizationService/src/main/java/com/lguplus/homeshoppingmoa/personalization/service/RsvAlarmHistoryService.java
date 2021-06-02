package com.lguplus.homeshoppingmoa.personalization.service;

import com.lguplus.homeshoppingmoa.common.model.dto.personalization.PushDto;
import com.lguplus.homeshoppingmoa.personalization.model.entity.RsvAlarmHistory;
import com.lguplus.homeshoppingmoa.personalization.repository.RsvAlarmHistoryRepository;
import com.lguplus.homeshoppingmoa.personalization.rest.BroadcastProductServiceApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RsvAlarmHistoryService {

    private final RsvAlarmHistoryRepository rsvAlarmHistoryRepository;

    @Transactional
    public void save(PushDto pushEvent) {
        List<RsvAlarmHistory> rsvAlarmHistories = pushEvent.getDeleted().stream()
                .map(mainProductId -> {
                    RsvAlarmHistory rsvAlarmHistory = new RsvAlarmHistory();
                    rsvAlarmHistory.setStbMac(pushEvent.getMacAddress());
                    rsvAlarmHistory.setStbSub(pushEvent.getSubNumber());
                    rsvAlarmHistory.setMainProductId(mainProductId);

                    return rsvAlarmHistory;
                }).collect(Collectors.toList());

        rsvAlarmHistoryRepository.saveAll(rsvAlarmHistories);
    }
}
