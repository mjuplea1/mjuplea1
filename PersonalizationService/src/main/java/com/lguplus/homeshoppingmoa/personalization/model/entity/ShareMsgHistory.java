package com.lguplus.homeshoppingmoa.personalization.model.entity;

import com.lguplus.homeshoppingmoa.common.model.entity.BaseInfoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_SHARE_MSG_HISTORY")
@Entity
public class ShareMsgHistory extends BaseInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MSG_SEQ")
    private Long msgSeq;

    @Column(name = "STB_MAC", nullable = false, length = 17)
    private String stbMac;

    @Column(name = "STB_SUB", nullable = false, length = 13)
    private String stbSub;

    @Column(name = "RECEIVER", nullable = false, length = 13)
    private String receiver;

    @Column(name = "TITLE", nullable = false, length = 20)
    private String title;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String content;
}
