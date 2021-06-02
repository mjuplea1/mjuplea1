package com.lguplus.homeshoppingmoa.common.exception;

/**
 * 중복 익셉션
 */
public class DuplicationException extends RuntimeException {

	private static final long serialVersionUID = 8656116130152123105L;

	public DuplicationException() {}
    public DuplicationException(String msg) {
        super(msg);
    }

}
