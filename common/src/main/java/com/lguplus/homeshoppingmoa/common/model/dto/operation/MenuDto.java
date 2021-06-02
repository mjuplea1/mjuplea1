package com.lguplus.homeshoppingmoa.common.model.dto.operation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {
		"menuId"
})
@Schema(name = "MenuDto", description = "메뉴 DTO")
@ToString(exclude = {
		"menuId"
})
public class MenuDto implements Serializable {

	private static final long serialVersionUID = -6605465516954409942L;

	@Schema(name = "menuId", description = "계정아이디")
	private long menuId;

	// 메뉴아이디
	@Schema(name = "parentMenuId", description = "부모 메뉴")
	private Long parentMenuId;

	@Schema(name = "menuList", description = "자식 메뉴")
	List<MenuDto> menuList;

	// Depth
	@Schema(name = "depth", description = "Depth")
	private Integer depth;

	// 메뉴명
	@Schema(name = "menuName", description = "메뉴명")
	private String menuName;

	// 정렬순서
	@Schema(name = "sortOrder", description = "정렬순서")
	private Integer sortOrder;
}
