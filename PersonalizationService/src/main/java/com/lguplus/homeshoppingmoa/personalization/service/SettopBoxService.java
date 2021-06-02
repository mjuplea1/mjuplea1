package com.lguplus.homeshoppingmoa.personalization.service;

import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.Payload;
import com.lguplus.homeshoppingmoa.personalization.model.entity.SettopBox;
import com.lguplus.homeshoppingmoa.personalization.repository.SettopBoxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettopBoxService {

    private final SettopBoxRepository settopBoxRepository;

    @Transactional
    public void saveOrUpdate(Payload payload) {
        SettopBox byStbMacAndStbPhone = this.settopBoxRepository.findByStbMacAndStbSub(payload.getParam().getMacAddress(), payload.getParam().getSubNo());

        if (byStbMacAndStbPhone != null) {
            byStbMacAndStbPhone.updateDefaultField(payload.getCommon().getAppVersion(), payload.getCommon().getStbVersion(), payload.getCommon().getStbModel());
        }
        else {
            SettopBox settopBox = new SettopBox(
                    payload.getParam().getMacAddress(),
                    payload.getParam().getSubNo(),
                    payload.getCommon().getAppVersion(),
                    payload.getCommon().getStbVersion(),
                    payload.getCommon().getStbModel()
            );

            this.settopBoxRepository.save(settopBox);
        }
    }

}
