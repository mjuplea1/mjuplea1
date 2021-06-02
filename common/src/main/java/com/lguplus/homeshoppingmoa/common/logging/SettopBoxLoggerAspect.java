package com.lguplus.homeshoppingmoa.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import com.lguplus.homeshoppingmoa.common.exception.ServiceException;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.Payload;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.StbInfo;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j(topic = CommonConstants.LOGGER_NAME)
@Component
@Aspect
public class SettopBoxLoggerAspect extends LoggerAspect {

    @Value("${spring.application.name}")
    private String applicationName;

    public SettopBoxLoggerAspect(HttpServletRequest request, ObjectMapper objectMapper) {
        super(request, objectMapper);
    }

    private SettopBoxLogger getLoggerAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(SettopBoxLogger.class);
    }

    @Before("@annotation(SettopBoxLogger)")
    @Override
    public void loggingBefore(JoinPoint joinPoint) {
        super.beforeLogging();
    }

    @AfterReturning(value = "@annotation(SettopBoxLogger)", returning = "obj")
    @Override
    public void loggingSuccess(JoinPoint joinPoint, Object obj) {
        SettopBoxLogger sbl = this.getLoggerAnnotation(joinPoint);
        String logString = super.getLogString(this.getLogDetails(ResultCodeType.SUCCESS, sbl.svcType().name(), sbl.svcClassType().name(), sbl.subSvcClassType().name()));
        log.info(logString);
    }

    @AfterThrowing(value = "@annotation(SettopBoxLogger)", throwing = "e")
    @Override
    public void loggingFail(JoinPoint joinPoint, Exception e) {
        ResultCodeType resultCodeType;
        if (e instanceof ServiceException) {
            resultCodeType = ((ServiceException) e).getResultCodeType();
        }
        else {
            resultCodeType = ResultCodeType.STB_ERROR_3A200002;
        }

        SettopBoxLogger sbl = this.getLoggerAnnotation(joinPoint);
        String logString = super.getLogString(this.getLogDetails(resultCodeType, sbl.svcType().name(), sbl.svcClassType().name(), sbl.subSvcClassType().name()));
        log.info(logString);
    }

    @SneakyThrows
    @Override
    public LogDetails getLogDetails(ResultCodeType resultCodeType, String svcType, String svcClass, String subSvcClass) {
        Payload payload = null;
        String devModel = NULL;
        String subNo = NULL;

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            // CustomHttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(this.request);
            // Payload payLoad = this.objectMapper.readValue(requestWrapper.getInputStream(), Payload.class);

            payload = this.objectMapper.readValue(this.request.getInputStream(), Payload.class);
        }
        else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof NcasUserDetails) {
                NcasUserDetails ncasUser = (NcasUserDetails) principal;
                payload = ncasUser.getPayload();
            }
        }

        if (payload != null) {
            devModel = payload.getCommon().getStbModel();
            subNo = ((StbInfo) payload.getParam()).getSubNo();
        }

        return LogDetails.builder()
                .sid(NULL)
                .resultCode(resultCodeType.code())
                .startDatetime((LocalDateTime) super.request.getAttribute("x-start-datetime"))
                .endDatetime(LocalDateTime.now())
                .clientIp((String) super.request.getAttribute("x-client-ip"))
                .devInfo(DevInfoType.STB.name())
                .osInfo((String) super.request.getAttribute("x-os-info"))
                .applicationName(this.applicationName)
                .devModel(devModel)
                .subNo(subNo)
                .tranId((String) super.request.getAttribute("x-tran-id"))
                .svcType(svcType)
                .svcClass(svcClass)
                .subSvcClass(subSvcClass)
                .build();
    }

}
