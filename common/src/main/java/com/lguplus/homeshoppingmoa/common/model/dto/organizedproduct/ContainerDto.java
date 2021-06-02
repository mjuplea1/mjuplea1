package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(name = "ContainerDto", description = "컨테이너 DTO")
@EqualsAndHashCode(exclude = {
        "containerId"
        , "organizedProductList"
        , "autoKeyword"
        , "regId"
        , "regDt"
        , "modId"
        , "modDt"
})
public class ContainerDto implements Serializable {

    private static final long serialVersionUID = -6974682094190897764L;

    @Schema(name = "containerId", description = "컨테이너 id")
    private Long containerId;

    //편성상품
    @Schema(name = "organizedProductList", description = "편성상품")
    private List<OrganizedProductDto> organizedProductList = new ArrayList<>();

    // 자동키워드
    @Schema(name = "autoKeyword", description = "자동키워드 Dto")
    private AutoKeywordDto autoKeyword;

    // 이름
    @NotBlank
    @Schema(name = "containerName", description = "컨테이너 이름")
    private String containerName;

    // 유형
    @NotEmpty
    @Schema(name = "type", description = "컨테이너 유형")
    private String type;

    // 편성상품갯수
    @NotNull
    @Size(min = 1, max = 17)
    @Schema(name = "organizedProductCount", description = "컨테이너 편성상품갯수")
    private Integer organizedProductCount;

    // 노출시작일시
    @NotEmpty
    @Schema(name = "openStartDt", description = "컨테이너 노출시작일시")
    private String openStartDt;

    // 노출종료일시
    @NotEmpty
    @Schema(name = "openEndDt", description = "컨테이너 노출종료일시")
    private String openEndDt;

    // 상태
    @NotEmpty
    @Schema(name = "status", description = "컨테이너 상태")
    private String status;

    // 등록자아이디
    @Schema(name = "regId", description = "컨테이너 등록자아이디")
    private String regId;

    // 등록일시
    @Schema(name = "regDt", description = "컨테이너 등록일시")
    private LocalDateTime regDt;

    // 수정자아이디
    @Schema(name = "modId", description = "컨테이너 수정자아이디")
    private String modId;

    // 수정일시
    @Schema(name = "modDt", description = "컨테이너 수정일시")
    private LocalDateTime modDt;

}
