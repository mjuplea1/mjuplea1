package com.lguplus.homeshoppingmoa.personalization.model.entity;

import com.lguplus.homeshoppingmoa.common.model.entity.BaseInfoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_RSV_ALARM_HISTORY")
@Entity
public class RsvAlarmHistory extends BaseInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALARM_SEQ")
    private Long alarm_seq;

    @Column(name = "STB_MAC", nullable = false, length = 17)
    private String stbMac;

    @Column(name = "STB_SUB", nullable = false, length = 13)
    private String stbSub;

    @Column(name = "MAINPRODUCT_ID")
    private Long mainProductId;

}
