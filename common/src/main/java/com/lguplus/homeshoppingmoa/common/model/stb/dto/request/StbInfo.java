package com.lguplus.homeshoppingmoa.common.model.stb.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StbInfo implements Serializable {

    private String memberNo;

    private String subNo;

    private String macAddress;

}
