package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto {
    private int catg_code;

    private int catg_level;

    private String catg_nm;

    private int parent_catg_code;
}
