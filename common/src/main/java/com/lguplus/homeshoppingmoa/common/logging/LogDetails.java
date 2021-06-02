package com.lguplus.homeshoppingmoa.common.logging;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class LogDetails implements Serializable {

    private final String sid;
    private final String resultCode;
    private final LocalDateTime startDatetime;
    private final LocalDateTime endDatetime;
    private final String clientIp;
    private final String devInfo;
    private final String osInfo;
    private final String applicationName;
    private final String devModel;
    private final String subNo;
    private final String tranId;
    private final String svcType;
    private final String svcClass;
    private final String subSvcClass;

}
