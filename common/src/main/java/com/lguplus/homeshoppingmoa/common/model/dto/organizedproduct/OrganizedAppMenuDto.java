package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Schema(name = "OrganizedAppMenuDto", description = "편성앱메뉴 DTO")
public class OrganizedAppMenuDto implements Serializable {

	private static final long serialVersionUID = 6263036439789234390L;

	@Schema(name = "mappingId", description = "편성앱메뉴 일련번호")
	private long mappingId;

	@Schema(name = "AppMenuId", description = "앱메뉴")
	private Long appMenuId;

	@Schema(name = "ContainerDto", description = "컨테이너")
	private ContainerDto container;

	@Schema(name = "containerSortOrder", description = "컨테이너 정렬순서")
	private Integer containerSortOrder;

}
