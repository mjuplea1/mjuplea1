package com.lguplus.homeshoppingmoa.common.exception;

import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import lombok.Getter;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8765672124498590564L;

	@Getter
	private final ResultCodeType resultCodeType;

	public ServiceException(ResultCodeType resultCodeType, Throwable e) {
		super(e);
		this.resultCodeType = resultCodeType;
	}

	public ServiceException(ResultCodeType resultCodeType) {
		this.resultCodeType = resultCodeType;
	}

}
