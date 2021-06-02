package com.lguplus.homeshoppingmoa.common.model.dto.operation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(value = {
        "accountId"
        , "username"
        , "loginHistoryList"
        , "name"
        , "companyName"
        , "regId"
        , "regDt"
        , "modId"
        , "modDt"
})
@Schema(name = "AccountCreateDto", description = "권한 DTO 등록")
public class AccountModifyDto extends AccountDto {

    private static final long serialVersionUID = -5154790211167492937L;

    public AccountModifyDto() {
        LocalDateTime localDatetime = LocalDateTime.now();
        setRegDt(localDatetime);
        setUpdDt(localDatetime);
    }
}
