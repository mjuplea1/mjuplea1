package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = {
        "appMenuId"
        , "appMenu"
        , "appMenuName"
        , "nowBroadcastingYn"
        , "sortOrder"
        , "regId"
        , "regDt"
        , "modId"
        , "modDt"
})
@Schema(name = "AppMenuDetailDto", description = "앱메뉴 상세 DTO")
public class AppMenuDetailDto extends AppMenuDto {

    private static final long serialVersionUID = -5154790211167492937L;

}
