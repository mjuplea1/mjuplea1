package com.lguplus.homeshoppingmoa.personalization.config;

import com.lguplus.homeshoppingmoa.config.servlet.StbResponseSuccessAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @formatter: off
@RestControllerAdvice(
        basePackages = {
				"com.lguplus.homeshoppingmoa.personalization.controller",
        }
)
public class SuccessBodyAdvice extends StbResponseSuccessAdvice {

}
