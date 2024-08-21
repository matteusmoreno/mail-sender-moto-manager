package com.matteusmoreno.mail_sender_moto_manager.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AppUtils {

    public static String formatBirthDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthDate).format(formatter);
    }

    public static String defaultIfNull(String value) {
        return value != null ? value : "";
    }
}
