package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(value = {
        "appMenuId"
        , "sortOrder"
        , "organizedAppMenuList"
        , "regId"
        , "regDt"
        , "modId"
        , "modDt"
})
@Schema(name = "AppMenuModifyDto", description = "앱메뉴 수정 DTO")
public class AppMenuModifyDto extends AppMenuDto {

    private static final long serialVersionUID = -5154790211167492937L;

    @Schema(name = "sortedContainerIds", description = "정렬된 컨테이너 아이디 목록")
    private List<Long> sortedContainerIds = new ArrayList<>();

}
