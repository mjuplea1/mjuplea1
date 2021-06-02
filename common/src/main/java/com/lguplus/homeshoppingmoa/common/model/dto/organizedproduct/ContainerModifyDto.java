package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(value = {
        "containerId"
        , "organizedProductList"
        , "regId"
        , "regDt"
        , "modId"
        , "modDt"
})
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ContainerModifyDto", description = "컨테이너 DTO 수정")
public class ContainerModifyDto extends ContainerDto {

    private static final long serialVersionUID = -5154790211167492937L;

    @Schema(name = "addedMainProductIds", description = "추가되는 메인상품 아이디 목록")
    private List<Long> addedMainProductIds = new ArrayList<>();
    @Schema(name = "deletedMainProductIds", description = "삭제되는 메인상품 아이디 목록")
    private List<Long> deletedMainProductIds = new ArrayList<>();

}
