package com.lguplus.homeshoppingmoa.config.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.lguplus.homeshoppingmoa.config.jackson.module.CustomModule;
import com.lguplus.homeshoppingmoa.config.jackson.module.LocalDateTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonMapperConfig {

    @Primary
    @Bean(name = "jacksonObjectMapper")
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
                .json()
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules
                        (
                                new ParameterNamesModule(),
                                new Jdk8Module(),
                                new JavaTimeModule(),
                                new LocalDateTimeModule(),
                                // 여기부터 커스텀 모듈을 만들어 넣으면 자동 변환이 가능함.
                                new CustomModule(),
                                // 성능향상
                                new AfterburnerModule()
                        )
                .indentOutput(true)
                .failOnEmptyBeans(false)
                .failOnUnknownProperties(false)
                .build();

        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        // objectMapper.getSerializerProvider().setNullValueSerializer(new NullToEmptyStringSerializer());
		// objectMapper.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        return objectMapper;
    }

}
