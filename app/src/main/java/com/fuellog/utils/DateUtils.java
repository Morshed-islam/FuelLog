package com.fuellog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String dateFormat_DB = "yyyy-MM-dd";

    public static String formatDate(Date date) {
        return new SimpleDateFormat(Config.getDateFormat(), Locale.getDefault()).format(date);
    }

    public static String formatDateDB(Date date) {
        try {
            return new SimpleDateFormat(dateFormat_DB, Locale.getDefault()).format(date);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getTodayDateFormatted() {
        return formatDate(Calendar.getInstance().getTime());
    }

    public static String getTodayDateDbFormatted() {
        return formatDateDB(Calendar.getInstance().getTime());
    }

    public static Date parseDateFormatted(String str) {
        return parseDate(str, Config.getDateFormat());
    }

    public static Date parseDateDB(String str) {
        return parseDate(str, dateFormat_DB);
    }

    private static Date parseDate(String str, String str2) {
        try {
            return new SimpleDateFormat(str2, Locale.getDefault()).parse(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String dbDateToFormatted(String str) {
        return formatDate(parseDateDB(str));
    }

    public static String formattedToDbDate(String str) {
        return formatDateDB(parseDateFormatted(str));
    }
}
