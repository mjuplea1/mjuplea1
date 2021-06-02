package com.lguplus.homeshoppingmoa.common.sec.ncas.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class StbUser implements Serializable {

    private final String macAddr;

    private final String subNo;

}
