package com.lguplus.homeshoppingmoa.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "RequestNcasAuthDto", description = "nCas 인증 DTO")
public class RequestNcasAuthDto {

    private String memberNo;

    private String subNo;

    private String macAddr1;

    public RequestNcasAuthDto() {
    }

    public RequestNcasAuthDto(String memberNo, String subNo, String macAddr1) {
        this.memberNo = memberNo;
        this.subNo = subNo;
        this.macAddr1 = macAddr1;
    }

    @Override
    public String toString() {
        return "RequestNcasAuthDto{" +
                "memberNo='" + memberNo + '\'' +
                ", subNo='" + subNo + '\'' +
                ", macAddr1='" + macAddr1 + '\'' +
                '}';
    }

}
