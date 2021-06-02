package com.lguplus.homeshoppingmoa.personalization.repository;

import com.lguplus.homeshoppingmoa.personalization.model.entity.SettopBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettopBoxRepository extends JpaRepository<SettopBox, Long> {

    SettopBox findByStbMacAndStbSub(String stbMac, String stbSub);

    List<SettopBox> findByStbIdIn(List<Long> stbIdList);

}
