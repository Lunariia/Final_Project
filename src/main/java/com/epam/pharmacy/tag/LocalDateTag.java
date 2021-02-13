package com.epam.pharmacy.tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTag {

    private final static String PATTERN = "dd/MM/yyyy";

    public LocalDateTag() {}

    public static String formatLocalDate(LocalDateTime localDateTime){
        return formatLocalDate(localDateTime, PATTERN);
    }

    public static String formatLocalDate(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
