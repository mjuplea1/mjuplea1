package com.lguplus.homeshoppingmoa.common.exception;

/**
 * 권한 익셉션
 */
public class PermissionException extends RuntimeException {

	private static final long serialVersionUID = 5612128965694895301L;

	public PermissionException() {}
    public PermissionException(String message) {
        super(message);
    }

}
