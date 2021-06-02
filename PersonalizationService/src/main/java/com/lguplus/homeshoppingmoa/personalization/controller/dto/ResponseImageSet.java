package com.lguplus.homeshoppingmoa.personalization.controller.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "ResponseImageSet", description = "이미지노출설정여부")
@ToString
public class ResponseImageSet {

    private boolean display;

    private ResponseImageSet(boolean display) {
        this.display = display;
    }

    public static ResponseImageSet create(boolean display) {
        return new ResponseImageSet(display);
    }
}
