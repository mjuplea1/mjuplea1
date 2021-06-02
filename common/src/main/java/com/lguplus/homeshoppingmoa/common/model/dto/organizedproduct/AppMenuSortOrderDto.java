package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = {
        "organizedAppMenuList"
        , "appMenuName"
        , "status"
        , "openStartDt"
        , "openEndDt"
        , "productSortType"
        , "channelNums"
        , "nowBroadcastingYn"
        , "rcmdChannelIds"
        , "regId"
        , "regDt"
        , "modId"
        , "modDt"
})
@Schema(name = "AppMenuSortOrderDto", description = "앱메뉴 정렬 DTO")
@NoArgsConstructor
public class AppMenuSortOrderDto extends AppMenuDto {

    private static final long serialVersionUID = -5154790211167492937L;

}
