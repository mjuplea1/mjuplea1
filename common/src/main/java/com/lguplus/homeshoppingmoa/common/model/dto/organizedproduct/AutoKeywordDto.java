package com.lguplus.homeshoppingmoa.common.model.dto.organizedproduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Schema(name = "AutoKeywordDto", description = "자동키워드검색조건 DTO")
@EqualsAndHashCode
public class AutoKeywordDto implements Serializable {

	private static final long serialVersionUID = 2389323026496370460L;

	// 코드
	@NotEmpty(message = "코드 값은 필수입니다.")
	@Schema(name = "code", description = "자동키워드검색조건 코드")
	private String code;

	// 검색어
	@Schema(name = "keyword", description = "자동키워드검색조건 검색어")
	private String keyword;

	// 값
	@Schema(name = "value", description = "자동키워드검색조건 값")
	private String value;

	// 최소값
	@Schema(name = "minValue", description = "자동키워드검색조건 최소값")
	private Integer minValue;

	// 최대값
	@Schema(name = "maxValue", description = "자동키워드검색조건 최대값")
	private Integer maxValue;

	// 카테고리아이디
	@Schema(name = "categoryId", description = "자동키워드검색조건 카테고리아이디")
	private Integer categoryId;

}
