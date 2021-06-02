package com.lguplus.homeshoppingmoa.personalization.model.entity;

import com.lguplus.homeshoppingmoa.common.model.entity.BaseInfoEntity;
import com.lguplus.homeshoppingmoa.personalization.model.entity.enumobject.DisplayYN;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "TB_SETTOP_BOX")
@Entity
public class SettopBox extends BaseInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STB_ID")
    private Long stbId;

    @Column(name = "STB_MAC", nullable = false, length = 17)
    private String stbMac;

    @Column(name = "STB_SUB", nullable = false, length = 13)
    private String stbSub;

    @Enumerated(EnumType.STRING)
    @Column(name = "STB_DISPLAY_YN", nullable = false, length = 10)
    private DisplayYN stbDisplayYn = DisplayYN.Y;

    @Column(name = "STB_APP_VERSION", nullable = false, length = 10)
    private String stbAppVersion;

    @Column(name = "STB_VERSION", nullable = false, length = 10)
    private String stbVersion;

    @Column(name = "STB_MODEL_VERSION", nullable = false, length = 20)
    private String stbModel;

    @OneToMany(mappedBy = "settopBox", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public SettopBox(String stbMac, String stbSub) {
        this.stbMac = stbMac;
        this.stbSub = stbSub;
    }

    public SettopBox(String stbMac, String stbSub, String stbAppVersion, String stbVersion, String stbModel) {
        this.stbMac = stbMac;
        this.stbSub = stbSub;
        this.stbAppVersion = stbAppVersion;
        this.stbVersion = stbVersion;
        this.stbModel = stbModel;
        this.displayOff();
    }

    public void displayOn() {
        this.stbDisplayYn = DisplayYN.Y;
    }

    public void displayOff() {
        this.stbDisplayYn = DisplayYN.N;
    }

    public void updateDefaultField(String appVersion, String stbVersion, String stbModel) {
        this.stbAppVersion = appVersion;
        this.stbVersion = stbVersion;
        this.stbModel = stbModel;
        this.displayOff();
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservations(List<Reservation> reservations) {
        reservations.forEach(reservation -> {
            reservation.setSettopBox(null);
            this.getReservations().remove(reservation);
        });
    }

}
