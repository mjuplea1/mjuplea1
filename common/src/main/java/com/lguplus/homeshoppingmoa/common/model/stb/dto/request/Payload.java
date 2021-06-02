package com.lguplus.homeshoppingmoa.common.model.stb.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * 셋톱박스 요청 페이로드
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload<T> implements Serializable {

    private static final long serialVersionUID = 8655535036556384271L;

    private Common common;

    // private StbInfo param;
    private T param;

}