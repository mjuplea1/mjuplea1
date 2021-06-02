package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(exclude = {
		"appMenuId"
		, "regId"
		, "regDt"
		, "modId"
		, "modDt"
})
@Schema(name = "AppMenuDto", description = "앱메뉴 DTO")
@ToString(exclude = {
		"appMenuId"
		, "regId"
		, "regDt"
		, "modId"
		, "modDt"
})
public class AppMenuDto implements Serializable {

	private static final long serialVersionUID = -6605465516954409942L;

	@Schema(name = "appMenuId", description = "앱메뉴 id")
	private long appMenuId;

	@Schema(name = "organizedAppMenuList", description = "편성앱메뉴 목록")
	private List<OrganizedAppMenuDto> organizedAppMenuList = new ArrayList<>();

	// 이름
	@Schema(name = "appMenuName", description = "앱메뉴 이름")
	private String appMenuName;

	// 상태
	@Schema(name = "status", description = "앱메뉴 상태")
	private String status;

	// 노출시작일시
	@Schema(name = "openStartDt", description = "앱메뉴 노출시작일시")
	private String openStartDt;

	// 노출종료일시
	@Schema(name = "openEndDt", description = "앱메뉴 노출종료일시")
	private String openEndDt;

	// 상품정렬방식
	@Schema(name = "productSortType", description = "앱메뉴 상품정렬방식")
	private String productSortType;

	// 채널번호
	@Schema(name = "channelNums", description = "앱메뉴 채널번호")
	private String channelNums;

	// 지금하는방송여부
	@Schema(name = "nowBroadcastingYn", description = "지금하는 방송여부")
	private boolean nowBroadcastingYn;

	// 추천채널
	@Schema(name = "rcmdChannelIds", description = "추천재널")
	private String rcmdChannelIds;

	// 정렬순서
	@Schema(name = "sortOrder", description = "정렬순서")
	private int sortOrder;

	// 등록자아이디
	@Schema(name = "regId", description = "앱메뉴 등록자아이디")
	private String regId;

	// 등록일시
	@Schema(name = "LocalDateTime", description = "앱메뉴 등록일시")
	private LocalDateTime regDt;

	// 수정자아이디
	@Schema(name = "modId", description = "앱메뉴 수정자아이디")
	private String modId;

	// 수정일시
	@Schema(name = "modDt", description = "앱메뉴 수정일시")
	private LocalDateTime modDt;

	// 이벤트 구독 서비스에서 사용
	@Schema(hidden = true)
	private List<ContainerDto> containerList = new ArrayList<>();

	public List<ContainerDto> extractContainers() {
		return this.organizedAppMenuList.stream()
				.sorted(Comparator.comparing(OrganizedAppMenuDto::getContainerSortOrder))
				.map(OrganizedAppMenuDto::getContainer)
				.collect(Collectors.toList());
	}

}
