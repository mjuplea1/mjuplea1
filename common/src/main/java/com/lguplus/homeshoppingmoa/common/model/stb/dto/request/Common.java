package com.lguplus.homeshoppingmoa.common.model.stb.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * 셋톱박스 요청 공통
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Common implements Serializable {

    private static final long serialVersionUID = -533181097538448226L;

    private String appVersion;
    private String stbVersion;
    private String stbModel;

}
