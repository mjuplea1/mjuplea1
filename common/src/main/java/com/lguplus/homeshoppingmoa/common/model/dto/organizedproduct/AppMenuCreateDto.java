package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(value = {
        "appMenuId"
        , "productSortType"
        , "channelIds"
        , "channelNums"
        , "rcmdChannelIds"
        , "nowBroadcastingYn"
        , "sortOrder"
        , "organizedAppMenuList"
        , "regId"
        , "regDt"
        , "modId"
        , "modDt"
})
@Schema(name = "AppMenuCreateDto", description = "앱메뉴 등록 DTO")
public class AppMenuCreateDto extends AppMenuDto {

    private static final long serialVersionUID = -5154790211167492937L;

    @Schema(name = "sortedContainerIds", description = "정렬된 컨테이너 아이디 목록")
    private List<Long> sortedContainerIds = new ArrayList<>();

    public AppMenuCreateDto() {
        LocalDateTime localDatetime = LocalDateTime.now();
        setRegDt(localDatetime);
        setModDt(localDatetime);
        setSortOrder(0);
    }
}
