package com.lguplus.homeshoppingmoa.common.model.dto.personalization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PushDto {
    private String macAddress;
    private String subNumber;
    private List<Long> deleted;
}
