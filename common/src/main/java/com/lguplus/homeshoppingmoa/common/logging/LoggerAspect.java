package com.lguplus.homeshoppingmoa.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.JoinPoint;
import ua_parser.Client;
import ua_parser.Parser;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.util.StringUtils.isEmpty;

public abstract class LoggerAspect {

    protected static final String SEPARATOR = "|";
    protected static final String KV_SEPARATOR = "=";

    protected static final String DFP_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    protected static final String NULL = "null";

    protected HttpServletRequest request;
    protected ObjectMapper objectMapper;

    protected LoggerAspect() {

    }

    protected LoggerAspect(HttpServletRequest request) {
        this.request = request;
    }

    protected LoggerAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected LoggerAspect(HttpServletRequest request, ObjectMapper objectMapper) {
        this.request = request;
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("deprecation")
    protected String extractClientIp() {
        String clientIp = this.request.getHeader("X-Forwarded-For");

        if (isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = this.request.getHeader("Proxy-Client-IP");
        }
        /**
         if (isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
         clientIp = request.getHeader("WL-Proxy-Client-IP");
         }
         */
        if (isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = this.request.getRemoteAddr();
        }

        if (isEmpty(clientIp)) {
            return NULL;
        }

        return clientIp;
    }

    @SuppressWarnings("deprecation")
    protected String getOsInfo() {
        String userAgent = this.request.getHeader("user-agent");
        if (isEmpty(userAgent)) {
            return NULL;
        }

        Parser parser = new Parser();
        Client client = parser.parse(userAgent);

        StringBuilder osInfo = new StringBuilder();
        osInfo.append(client.os.family).append("_").append(client.os.major);
        if (client.os.minor != null) {
            osInfo.append("_").append(client.os.minor);
        }

        return osInfo.toString();
    }

    protected String getLogString(LogDetails logDetails) {
        String logTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DFP_YYYYMMDDHHMMSSSSS));
        String logTime14 = logTime.substring(0, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("SEQ_ID").append(KV_SEPARATOR).append(logTime).append(RandomStringUtils.randomNumeric(4)).append(RandomStringUtils.randomNumeric(4)); // YYYYMMDDHHmmSSsss+{?????? 8??????}
        sb.append(SEPARATOR).append("LOG_TIME").append(KV_SEPARATOR).append(logTime14); // ????????? ????????? Write ?????? ??????, YYYYMMDDHHmmSS
        sb.append(SEPARATOR).append("LOG_TYPE").append(KV_SEPARATOR).append("SVC"); // 3??????, SVC
        sb.append(SEPARATOR).append("SID").append(KV_SEPARATOR).append(logDetails.getSid()); // "CTN : 0100xxxxxxxx(12??????), ID : alphanumeric (50?????????)"
        sb.append(SEPARATOR).append("RESULT_CODE").append(KV_SEPARATOR).append(ResultCodeType.SUCCESS.code()); // 8??????, "1xxxxxxx : ??????, 2xxxxxxx : ??????, 3xxxxxxx : ??????????????? ??????, 4xxxxxxx : ????????????(?????????), 5xxxxxxx : ????????????(??????)"
        sb.append(SEPARATOR).append("REQ_TIME").append(KV_SEPARATOR).append(logDetails.getStartDatetime().format(DateTimeFormatter.ofPattern(DFP_YYYYMMDDHHMMSSSSS))); // YYYYMMDDHHmmSSsss
        sb.append(SEPARATOR).append("RSP_TIME").append(KV_SEPARATOR).append(logDetails.getEndDatetime().format(DateTimeFormatter.ofPattern(DFP_YYYYMMDDHHMMSSSSS))); // YYYYMMDDHHmmSSsss
        sb.append(SEPARATOR).append("CLIENT_IP").append(KV_SEPARATOR).append(logDetails.getClientIp()); // ??????????????? IP
        sb.append(SEPARATOR).append("DEV_INFO").append(KV_SEPARATOR).append(logDetails.getDevInfo()); // 5??????, ?????? ?????? ??????(TV, STB)
        sb.append(SEPARATOR).append("OS_INFO").append(KV_SEPARATOR).append(logDetails.getOsInfo()); // OS ??????
        sb.append(SEPARATOR).append("NW_INFO").append(KV_SEPARATOR).append("ETC"); // ?????? ???????????? ??????
        sb.append(SEPARATOR).append("SVC_NAME").append(KV_SEPARATOR).append(logDetails.getApplicationName()); // 32??????, ??? ?????????/????????? ???
        sb.append(SEPARATOR).append("DEV_MODEL").append(KV_SEPARATOR).append(logDetails.getDevModel()); // 50??????, ?????? ?????????, ???????????? common ?????? ??????
        sb.append(SEPARATOR).append("CARRIER_TYPE").append(KV_SEPARATOR).append("L"); // 1??????, ????????? ??????, L (LGU+) E (etc)
        sb.append(SEPARATOR).append("SVC_TYPE").append(KV_SEPARATOR).append(logDetails.getSvcType());
        sb.append(SEPARATOR).append("SUB_NO").append(KV_SEPARATOR).append(logDetails.getSubNo());
        sb.append(SEPARATOR).append("TRAN_ID").append(KV_SEPARATOR).append(logDetails.getTranId());
        sb.append(SEPARATOR).append("SVC_CLASS").append(KV_SEPARATOR).append(logDetails.getSvcClass());
        sb.append(SEPARATOR).append("SUB_SVC_CLASS").append(KV_SEPARATOR).append(logDetails.getSubSvcClass());

        return sb.toString();
    }

    protected void beforeLogging() {
        this.request.setAttribute("x-start-datetime", LocalDateTime.now());
        this.request.setAttribute("x-client-ip", this.extractClientIp());
        this.request.setAttribute("x-os-info", this.getOsInfo());

        String tranId = this.request.getHeader("TRAN_ID");
        if (tranId == null) {
            tranId = NULL;
        }

        this.request.setAttribute("x-tran-id", tranId);
    }

    public abstract void loggingBefore(JoinPoint joinPoint);
    public abstract void loggingSuccess(JoinPoint joinPoint, Object obj);
    public abstract void loggingFail(JoinPoint joinPoint, Exception e);
    public abstract LogDetails getLogDetails(ResultCodeType resultCodeType, String svcType, String svcClass, String subSvcClass);

}
