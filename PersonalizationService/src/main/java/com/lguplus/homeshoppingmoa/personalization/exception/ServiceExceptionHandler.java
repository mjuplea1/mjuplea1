package com.lguplus.homeshoppingmoa.personalization.exception;

import javax.servlet.http.HttpServletRequest;

import com.lguplus.homeshoppingmoa.common.exception.ServiceException;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.response.StbCommonModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lguplus.homeshoppingmoa.config.exception.ApiExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ServiceExceptionHandler extends ApiExceptionHandler {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ ServiceException.class })
	public ResponseEntity handleServiceException(HttpServletRequest req, ServiceException e) {
		log.error(req.getRequestURL() + " ServiceException", e);

		StbCommonModel scm = new StbCommonModel(e.getResultCodeType().code(), e.getResultCodeType().desc());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scm);
	}

}
