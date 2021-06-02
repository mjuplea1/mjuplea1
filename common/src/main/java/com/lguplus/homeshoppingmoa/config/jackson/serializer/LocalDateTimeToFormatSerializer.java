package com.lguplus.homeshoppingmoa.config.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lguplus.homeshoppingmoa.config.jackson.converter.DateTimeConverter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToFormatSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(DateTimeConverter.localDateTimeToString(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
