package com.lguplus.homeshoppingmoa.personalization.service;

import com.lguplus.homeshoppingmoa.personalization.model.entity.SettopBox;
import com.lguplus.homeshoppingmoa.personalization.repository.SettopBoxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageSetService {

    private final SettopBoxRepository settopBoxRepository;

    @Transactional(readOnly = true)
    public SettopBox getImageSettingValue(String stbMac, String stbSub) {
        return settopBoxRepository.findByStbMacAndStbSub(stbMac, stbSub);
    }

    @Transactional
    public SettopBox updateImageSettingValueTurnOn(String stbMac, String stbSub) {
        SettopBox settopBox = settopBoxRepository.findByStbMacAndStbSub(stbMac, stbSub);
        settopBox.displayOn();

        return settopBox;
    }

    @Transactional
    public SettopBox updateImageSettingValueTurnOff(String stbMac, String stbSub) {
        SettopBox settopBox = settopBoxRepository.findByStbMacAndStbSub(stbMac, stbSub);
        settopBox.displayOff();

        return settopBox;
    }

}
