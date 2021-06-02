package com.lguplus.homeshoppingmoa.common.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 스트링 유틸
 */
public class TextUtils extends org.springframework.util.StringUtils {

    public static final String EMPTY_STRING = "";
    public static final String BLANK = " ";

    private TextUtils() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("deprecation")
    public static boolean isNotEmpty(String param) {
        return !org.springframework.util.StringUtils.isEmpty(param);
    }

    @SuppressWarnings("deprecation")
    public static String leftPad(String s, int n) {
        if (isEmpty(s)) {
            s = EMPTY_STRING;
        }
        return String.format("%" + n + "s", s);
    }

    @SuppressWarnings("deprecation")
    public static String rightPad(String s, int n) {
        if (isEmpty(s)) {
            s = EMPTY_STRING;
        }
        return String.format("%-" + n + "s", s);
    }

    /**
     * size만큼 랜덤 문자열 생성
     *
     * @param size
     * @return
     */
    public static String getRandomAlphabetic(int size) {
        if (size < 1) {
            size = 1;
        }
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = size;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * size만큼 랜덤 문자열+숫자 생성
     *
     * @param size
     * @return
     */
    public static String getRandomAlphanumeric(int size) {
        if (size < 1) {
            size = 1;
        }
        int leftLimit = 48; // letter '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = size;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * 문자열 인코딩을 고려해서 문자열 자르기
     * - 잘리는 캐릭터는 버림
     *
     * @param str
     * @param byteLength
     * @return
     */
    public static String getByteSubstr(String str, int byteLength) {
        if (str == null || str.length() < byteLength) {
            return str;
        }

        Charset utf8Charset = StandardCharsets.UTF_8;
        CharsetDecoder cd = utf8Charset.newDecoder();

        byte[] sba = str.getBytes(StandardCharsets.UTF_8);
        ByteBuffer bb = ByteBuffer.wrap(sba, 0, byteLength); // len in [B]
        CharBuffer cb = CharBuffer.allocate(byteLength);
        cd.onMalformedInput(CodingErrorAction.IGNORE);
        cd.decode(bb, cb, true);
        cd.flush(cb);

        return new String(cb.array(), 0, cb.position());
    }

    /**
     * 문자열 인코딩에 따라서 글자수 체크
     *
     * @param cs
     * @return
     */
    public static int getByteLength(CharSequence cs) {
        int count = 0;

        for (int i = 0, len = cs.length(); i < len; i++) {
            char ch = cs.charAt(i);

            if (ch <= 0x7F) {
                count++;
            } else if (ch <= 0x7FF) {
                count += 2;
            } else if (Character.isHighSurrogate(ch)) {
                count += 4;
                ++i;
            } else {
                count += 3;
            }
        }

        return count;
    }

}
