package com.lguplus.homeshoppingmoa.common.constants;

/**
 * 공통 API 응답코드
 */
public enum ResponseCode {

	// @formatter:off
    SUCCESS(200, "success"),
    FAIL_5000(5000, "Auth failed."),
    FAIL_5010(5010, "Invaild JWT."),
    FAIL_5020(5020, "Expired JWT."),
    FAIL_5030(5030, "비밀번호 변경 90일 초과."),
    FAIL_5040(5040, "Invaild field."),
    FAIL_5050(5050, "Business logic error."),
    FAIL_5060(5060, "접근 권한이 없습니다."),
    FAIL_5070(5070, "리소스가 존재하지 않습니다."),
    FAIL_5080(5080, "리소스가 중복되었습니다."),
    FAIL_5090(5090, "한도가 초과되었습니다."),
    FAIL_5100(5100, "저장 공간 한도가 초과되었습니다."),
    FAIL_5110(5110, "파일 사이즈 한도가 초과되었습니다."),
    FAIL_5120(5120, "파일 허용 개수가 초과되었습니다."),
    FAIL_5130(5130, "허용하지 않는 파일 확장자입니다.")
    ;
    // @formatter:on

	ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

	private final int code;

    private final String message;

	public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("Code:%s, Message:%s", code(), message());
    }

}
