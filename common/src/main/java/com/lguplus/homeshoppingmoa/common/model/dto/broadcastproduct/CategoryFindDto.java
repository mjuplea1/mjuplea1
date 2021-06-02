package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFindDto {

    @Schema(name = "level", description = "카테고리 레벨")
    private Integer level;

    @Schema(name = "parentCategoryId", description = "상위 카테고리 아이디")
    private Integer parentCategoryId;
}
