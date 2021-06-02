package com.lguplus.homeshoppingmoa.config.jackson.module;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lguplus.homeshoppingmoa.config.jackson.deserializer.LocalDateDeserializer;
import com.lguplus.homeshoppingmoa.config.jackson.deserializer.LocalDateTimeDeserializer;
import com.lguplus.homeshoppingmoa.config.jackson.serializer.LocalDateTimeToFormatSerializer;
import com.lguplus.homeshoppingmoa.config.jackson.serializer.LocalDateToFormatSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateTimeModule extends SimpleModule {

	private static final long serialVersionUID = 4594821473585999416L;

	public LocalDateTimeModule() {
        super();
		addSerializer(LocalDateTime.class, new LocalDateTimeToFormatSerializer());
		addSerializer(LocalDate.class, new LocalDateToFormatSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
		addDeserializer(LocalDate.class, new LocalDateDeserializer());
    }

}
