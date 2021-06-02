package com.lguplus.homeshoppingmoa.config.jackson.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.lang.Nullable;

public class DateTimeConverter {

    public static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	public static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String localDateTimeToString(@Nullable LocalDateTime localDateTime, @Nullable DateTimeFormatter formatter) {
        return Optional.ofNullable(localDateTime)
                .map(m -> m.format(Optional.ofNullable(formatter).orElse(yyyyMMddHHmmss)))
                .orElse("");
    }

    public static String localDateTimeToString(@Nullable LocalDateTime localDateTime) {
        return localDateTimeToString(localDateTime, null);
    }

    public static String localDateToString(@Nullable LocalDate localDate, @Nullable DateTimeFormatter formatter) {
        return Optional.ofNullable(localDate)
                .map(m -> m.format(Optional.ofNullable(formatter).orElse(yyyyMMdd))).orElse("");
    }

    public static String localDateToString(@Nullable LocalDate localDate) {
        return localDateToString(localDate, null);
    }

}
