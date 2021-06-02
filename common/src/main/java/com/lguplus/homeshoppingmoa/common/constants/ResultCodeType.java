package com.lguplus.homeshoppingmoa.common.constants;

public enum ResultCodeType {

    SUCCESS("2S000000", "성공", "Y")

    , STB_ERROR_3A301001("3A301001", "파라메터오류 - macAddress 정보 없음", "N")
    , STB_ERROR_3A301002("3A301002", "파라메터오류 - subNo 정보 없음", "N")
    , STB_ERROR_3A301003("3A301003", "NCAS 인증 실패", "N")
    , STB_ERROR_3A301004("3A301004", "NCAS 통신 오류", "N")

    , STB_ERROR_3A100001("3A100001", "token 정보 없음", "N")
    , STB_ERROR_3A100002("3A100002", "token 세션 만료", "N")
    , STB_ERROR_3A100003("3A100003", "유효하지 않은 token", "N")

    , STB_ERROR_3A302001("3A302001", "파라메터오류 - mainProductId 정보 없음", "N")
    , STB_ERROR_3A302002("3A302002", "유효하지 않는 방송상품 예약 실패", "N")
    , STB_ERROR_3A302003("3A302003", "중복 예약 실패", "Y")
    , STB_ERROR_3A302004("3A302004", "최대건수 오버 예약 실패", "Y")
    , STB_ERROR_3A302005("3A302005", "예약 실패", "Y")

    , STB_ERROR_3A303001("3A303001", "대상없음 예약취소 실패", "Y")

    , STB_ERROR_3A304001("3A304001", "이미지 노출 설정 실패", "N")

    , STB_ERROR_3A305001("3A305001", "파라메터오류 - 수신자 정보 없음", "N")
    , STB_ERROR_3A305002("3A305002", "파라메터오류 - 컨텐츠 정보 없음", "N")
    , STB_ERROR_3A305003("3A305003", "문자 발송 요청 실패", "N")

    , STB_ERROR_3A200001("3A200001", "DB 연동실패", "N")
    , STB_ERROR_3A200002("3A200002", "서비스 내부 오류", "N")

    , STB_ERROR_3B100001("3B100001", "token 정보 없음", "N")
    , STB_ERROR_3B100002("3B100002", "token 세션 만료", "N")
    , STB_ERROR_3B100003("3B100003", "유효하지 않은 token", "N")

    , STB_ERROR_3B301001("3B100003", "튜토리얼 정보 없음", "N")

    , STB_ERROR_3B200001("3B200001", "DB 연동실패", "N")
    , STB_ERROR_3B200002("3B200002", "서비스 내부 오류", "N")

    , STB_ERROR_3C100001("3C100001", "token 정보 없음", "N")
    , STB_ERROR_3C100002("3C100002", "token 세션 만료", "N")
    , STB_ERROR_3C100003("3C100003", "유효하지 않은 token", "N")

    , STB_ERROR_3C301001("3C301001", "파라메터오류 - 메뉴ID 정보 없음", "N")
    , STB_ERROR_3C302001("3C302001", "파라메터오류 - 컨테이너ID 정보 없음", "N")
    , STB_ERROR_3C200003("3C200003", "Redis 연동실패", "N")
    , STB_ERROR_3C200002("3C200002", "서비스 내부 오류", "N")

    , SERVER_ERROR_41001001("41001001", "인증 실패", "N")
    , SERVER_ERROR_41001002("41001002", "권한 없음", "N")
    , SERVER_ERROR_41001003("41001003", "유효하지 않은 method 접근", "N")
    , SERVER_ERROR_41001004("41001004", "유효하지 않는 API 호출", "N")

    , SERVER_ERROR_42001001("42001001", "필수 파라메터 누락", "N")
    , SERVER_ERROR_42001002("41001001", "조회 데이터 없음", "Y")
    , SERVER_ERROR_42001003("41001001", "신규 데이터 등록 실패", "N")
    , SERVER_ERROR_42001004("41001001", "데이터 업데이트 실패", "N")
    , SERVER_ERROR_42001005("41001001", "데이터 삭제 실패", "N")
    , SERVER_ERROR_42001006("41001001", "FK 제약으로인한 데이터 삭제 실패", "N")
    , SERVER_ERROR_42001007("41001001", "파일정보 없음", "N")
    , SERVER_ERROR_42001008("41001001", "유효하지 않은 파일 포멧", "N")
    , SERVER_ERROR_42001009("41001001", "지원하지 않는 데이터 규격", "N")

    , SERVER_ERROR_43001001("43001001", "DB 연동실패", "N")
    , SERVER_ERROR_43001002("43001002", "Redis 연동실패", "N")
    , SERVER_ERROR_43001003("43001003", "카프카 연동 실패", "N")
    , SERVER_ERROR_43001004("43001004", "서버 내부 오류", "N")

    , INF_ERROR_51001001("51001001", "SMSGW 연동 오류", "N")
    , INF_ERROR_51001002("51001002", "MMSGW 연동 오류", "N")
    , INF_ERROR_51001003("51001003", "PUSHGW 연동 오류", "N")
    , INF_ERROR_51001004("51001004", "NCAS 연동 오류", "N")
    , INF_ERROR_51001005("51001005", "홈쇼핑연동 실패", "N")
    , INF_ERROR_51001006("51001006", "MD연동 실패", "N")
    ;

    private final String code;
    private final String desc;
    private final String successYn;

    ResultCodeType(String code, String desc, String successYn) {
        this.code = code;
        this.desc = desc;
        this.successYn = successYn;
    }

    public String code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    public String successYn() {
        return successYn;
    }

    @Override
    public String toString() {
        return String.format("code: %s, desc: %s, successYn: %s", code(), desc(), successYn());
    }

}
