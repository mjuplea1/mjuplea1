package com.lguplus.homeshoppingmoa.personalization.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.constants.ResultCodeType;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.response.StbCommonModel;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ApiExceptionHandler 등에서 처리하지 않는 엑셉션에 대해 처리
 */
@Hidden
@Slf4j
@RequiredArgsConstructor
@RestController
public class SettopboxErrorController implements ErrorController {

    private final ObjectMapper objectMapper;

    @Deprecated
    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
	 * 에러 API 반환
	 *
	 * @param request
     * @param response
	 */
    @RequestMapping("${server.error.path:${error.path:/error}}")
    public void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errMsg = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

		log.info("statusCode: {}, errMsg: {}, requestUri: {}", statusCode, errMsg, requestUri);

        ResultCodeType resultCodeType;
		if (HttpStatus.FORBIDDEN.value() == statusCode) {
            resultCodeType = ResultCodeType.SERVER_ERROR_41001002;
        }
		else {
		    resultCodeType = ResultCodeType.STB_ERROR_3C200002;
        }

		String responseJson = this.objectMapper.writeValueAsString(new StbCommonModel<>(resultCodeType));
		response.setStatus(statusCode);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(responseJson);
        response.getWriter().flush();
    }

}
