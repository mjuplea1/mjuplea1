package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TotalCategoryRow {

    private CategoryDto depth1;

    private CategoryDto depth2;

    private CategoryDto depth3;

    private CategoryDto depth4;

    public CategoryDto[] getRow() {
        return new CategoryDto[]{depth1, depth2, depth3, depth4};
    }
}
