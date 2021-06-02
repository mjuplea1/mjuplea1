package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter // test 외 사용 안함
@NoArgsConstructor
@AllArgsConstructor
public class MainProductFindDto {

    @NotNull
    @Schema(name = "exposeStartDt", description = "노출 시작 일시(yyyyMMddhhmm)")
    private String exposeStartDt;

    @NotNull
    @Schema(name = "exposeEndDt", description = "노출 종료 일시(yyyyMMddhhmm)")
    private String exposeEndDt;

    @Schema(name = "type", description = "검색 타입")
    private MainProductSearchType type;

    @Schema(name = "value", description = "값")
    private String value;

    @Schema(name = "min", description = "최솟값")
    private String min;

    @Schema(name = "max", description = "최댓값")
    private String max;

    @Schema(name = "depth1", description = "카테고리 깊이 1")
    private String depth1;

    @Schema(name = "depth2", description = "카테고리 깊이 2")
    private String depth2;

    @Schema(name = "depth3", description = "카테고리 깊이 3")
    private String depth3;

    @Schema(name = "depth4", description = "카테고리 깊이 4")
    private String depth4;
}
