package com.lguplus.homeshoppingmoa.config.jackson.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.response.StbCommonModel;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.config.jackson.JacksonMapperConfig;

public class CommonModelConverter {

    private final ObjectMapper objectMapper;

    public CommonModelConverter() {
        this.objectMapper = new JacksonMapperConfig().objectMapper();
    }

    public String convertString(CommonModel model) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(model);
    }

    public String convertString(StbCommonModel model) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(model);
    }

}
