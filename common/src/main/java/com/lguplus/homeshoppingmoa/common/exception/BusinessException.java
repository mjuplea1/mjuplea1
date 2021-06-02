package com.lguplus.homeshoppingmoa.common.exception;

/**
 * BusinessException
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1757926319705592158L;

	public BusinessException() {}
    public BusinessException(String msg) {
        super(msg);
    }

}
