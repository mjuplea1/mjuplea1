package com.lguplus.homeshoppingmoa.config.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lguplus.homeshoppingmoa.config.jackson.converter.DateTimeConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToFormatSerializer extends JsonSerializer<LocalDate>  {

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DateTimeConverter.localDateToString(value, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}
