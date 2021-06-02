package com.lguplus.homeshoppingmoa.common.logging;

import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import com.lguplus.homeshoppingmoa.common.exception.ServiceException;
import com.lguplus.homeshoppingmoa.common.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j(topic = CommonConstants.LOGGER_NAME)
@Component
@Aspect
public class BatchLoggerAspect extends LoggerAspect {

    @Value("${spring.application.name}")
    private String applicationName;

    AtomicReference<LocalDateTime> arStartDatetime = new AtomicReference<>();

    private BatchLogger getLoggerAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(BatchLogger.class);
    }

    @Before("@annotation(BatchLogger)")
    @Override
    public void loggingBefore(JoinPoint joinPoint) {
        arStartDatetime.set(LocalDateTime.now());
    }

    @AfterReturning(value = "@annotation(BatchLogger)", returning = "obj")
    @Override
    public void loggingSuccess(JoinPoint joinPoint, Object obj) {
        BatchLogger bl = this.getLoggerAnnotation(joinPoint);
        String logString = super.getLogString(this.getLogDetails(ResultCodeType.SUCCESS, bl.svcType().name(), bl.svcClassType().name(), bl.subSvcClassType().name()));
        log.info(logString);
    }

    @AfterThrowing(value = "@annotation(BatchLogger)", throwing = "e")
    @Override
    public void loggingFail(JoinPoint joinPoint, Exception e) {
        ResultCodeType resultCodeType;
        if (e instanceof ServiceException) {
            resultCodeType = ((ServiceException) e).getResultCodeType();
        }
        else {
            resultCodeType = ResultCodeType.SERVER_ERROR_43001004;
        }

        BatchLogger bl = this.getLoggerAnnotation(joinPoint);
        String logString = super.getLogString(this.getLogDetails(resultCodeType, bl.svcType().name(), bl.svcClassType().name(), bl.subSvcClassType().name()));
        log.info(logString);
    }

    @Override
    public LogDetails getLogDetails(ResultCodeType resultCodeType, String svcType, String svcClass, String subSvcClass) {
        return LogDetails.builder()
                .sid(TextUtils.getRandomAlphanumeric(50))
                .resultCode(resultCodeType.code())
                .startDatetime(Optional.ofNullable(arStartDatetime.getAndSet(null)).orElse(LocalDateTime.now()))
                .endDatetime(LocalDateTime.now())
                .clientIp(NULL)
                .devInfo(DevInfoType.ETC.name())
                .osInfo(NULL)
                .applicationName(this.applicationName)
                .devModel(NULL)
                .subNo(NULL)
                .tranId(NULL)
                .svcType(svcType)
                .svcClass(svcClass)
                .subSvcClass(subSvcClass)
                .build();
    }

}
