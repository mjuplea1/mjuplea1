package com.lguplus.homeshoppingmoa.personalization.model.entity;

import com.lguplus.homeshoppingmoa.common.model.entity.BaseInfoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_RESERVATION")
@Entity
public class Reservation extends BaseInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RSV_ID")
    private Long rsvId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STB_ID")
    private SettopBox settopBox;

    @Column(name = "MAINPRODUCT_ID", nullable = false)
    private Long mainProductId;

    @Column(name = "BROADCAST_DT", nullable = false, length = 8)
    private String broadcastDt;

    @Column(name = "START_DT", nullable = false, length = 12)
    private String startDt;

    @Column(name = "END_DT", nullable = false, length = 12)
    private String endDt;

    public void addSettopBox(SettopBox settopBox){
        this.settopBox = settopBox;
        this.settopBox.addReservation(this);
    }

    @Builder
    public Reservation(Long mainProductId, String broadcastDt, String startTime, String endTime) {
        this.mainProductId = mainProductId;
        this.broadcastDt = broadcastDt;
        this.startDt = broadcastDt + startTime;
        this.endDt = broadcastDt + endTime;
    }
}
