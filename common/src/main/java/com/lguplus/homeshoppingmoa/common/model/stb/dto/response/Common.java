package com.lguplus.homeshoppingmoa.common.model.stb.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class Common implements Serializable {

    private static final long serialVersionUID = -8403926839353891470L;

    private final String code;
    private final String msg;

}
