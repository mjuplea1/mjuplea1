package com.lguplus.homeshoppingmoa.common.logging;

import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import com.lguplus.homeshoppingmoa.common.exception.ServiceException;
import com.lguplus.homeshoppingmoa.common.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j(topic = CommonConstants.LOGGER_NAME)
@Component
@Aspect
public class CustomLoggerAspect extends LoggerAspect {

    @Value("${spring.application.name}")
    private String applicationName;

    public CustomLoggerAspect(HttpServletRequest request) {
        super(request);
    }

    private CustomLogger getLoggerAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(CustomLogger.class);
    }

    @Before("@annotation(CustomLogger)")
    @Override
    public void loggingBefore(JoinPoint joinPoint) {
        super.beforeLogging();
    }

    @AfterReturning(value = "@annotation(CustomLogger)", returning = "obj")
    @Override
    public void loggingSuccess(JoinPoint joinPoint, Object obj) {
        CustomLogger cl = this.getLoggerAnnotation(joinPoint);
        String logString = super.getLogString(this.getLogDetails(ResultCodeType.SUCCESS, cl.svcType().name(), cl.svcClassType().name(), cl.subSvcClassType().name()));
        log.info(logString);
    }

    @AfterThrowing(value = "@annotation(CustomLogger)", throwing = "e")
    @Override
    public void loggingFail(JoinPoint joinPoint, Exception e) {
        ResultCodeType resultCodeType;
        if (e instanceof ServiceException) {
            resultCodeType = ((ServiceException) e).getResultCodeType();
        }
        else {
            resultCodeType = ResultCodeType.SERVER_ERROR_43001004;
        }

        CustomLogger cl = this.getLoggerAnnotation(joinPoint);
        String logString = super.getLogString(this.getLogDetails(resultCodeType, cl.svcType().name(), cl.svcClassType().name(), cl.subSvcClassType().name()));
        log.info(logString);
    }

    @Override
    public LogDetails getLogDetails(ResultCodeType resultCodeType, String svcType, String svcClass, String subSvcClass) {
        return LogDetails.builder()
                .sid(TextUtils.getRandomAlphanumeric(50))
                .resultCode(resultCodeType.code())
                .startDatetime((LocalDateTime) super.request.getAttribute("x-start-datetime"))
                .endDatetime(LocalDateTime.now())
                .clientIp((String) super.request.getAttribute("x-client-ip"))
                .devInfo(DevInfoType.ETC.name())
                .osInfo((String) super.request.getAttribute("x-os-info"))
                .applicationName(this.applicationName)
                .devModel(NULL)
                .subNo(NULL)
                .tranId((String) super.request.getAttribute("x-tran-id"))
                .svcType(svcType)
                .svcClass(svcClass)
                .subSvcClass(subSvcClass)
                .build();
    }

}
