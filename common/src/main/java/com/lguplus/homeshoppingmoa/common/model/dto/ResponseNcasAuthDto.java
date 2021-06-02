package com.lguplus.homeshoppingmoa.common.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ResponseNcasAuthDto implements Serializable {

    private boolean authFlag;

    public ResponseNcasAuthDto() {
    }

    private ResponseNcasAuthDto(boolean authFlag) {
        this.authFlag = authFlag;
    }

    public static ResponseNcasAuthDto ok() {
        return new ResponseNcasAuthDto(true);
    }

    public static ResponseNcasAuthDto fail() {
        return new ResponseNcasAuthDto(false);
    }

}
