package com.lguplus.homeshoppingmoa.common.model.stb.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

/**
 * 셋탑박스 공통 응답 모델
 */
@AllArgsConstructor
@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StbCommonModel<T> implements Serializable {

	private static final long serialVersionUID = -4498168998405018053L;

	private final Common common;

	@JsonInclude(JsonInclude.Include.NON_NULL)
    private T resultData;

    public StbCommonModel() {
		this.common = new Common(ResultCodeType.SUCCESS.code(), ResultCodeType.SUCCESS.desc());
    }

	public StbCommonModel(String code, String message) {
		this.common = new Common(code, message);
    }

	public StbCommonModel(HttpStatus httpStatus) {
		this(String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase());
	}

    public StbCommonModel(ResultCodeType resultCodeType) {
		this(resultCodeType.code(), resultCodeType.desc());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public StbCommonModel(T result) {
        this();

		if (Objects.nonNull(result)) {
			if (result instanceof Page) {
				Page page = (Page) result;
				this.resultData = (T) page.getContent();
			}
			else {
				this.resultData = result;
			}
		}
    }

}
