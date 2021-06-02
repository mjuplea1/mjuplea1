package com.lguplus.homeshoppingmoa.config.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter formatter1 = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .toFormatter();

    public static DateTimeFormatter formatter2 = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .toFormatter();

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return convert(p.getText());
    }

    private LocalDateTime convert(@Nullable String text) {
		if (text == null || text.isEmpty()) {
			return null;
		}

        try {
			if (text.contains("T")) {
				return LocalDateTime.parse(text, formatter1);
            }
            else {
				return LocalDateTime.parse(text, formatter2);
            }
        }
        catch (Exception e) {
            return null;
        }
    }

}
