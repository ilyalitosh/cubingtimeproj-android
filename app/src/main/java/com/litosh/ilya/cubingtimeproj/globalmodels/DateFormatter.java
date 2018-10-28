package com.litosh.ilya.cubingtimeproj.globalmodels;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * DateFormatter
 * класс для форматирование даты,
 * а также получение текущего времени
 *
 * @author Ilya Litosh
 */
public class DateFormatter {

    /**
     * Возвращает текущую дату
     * Пример: 19961025
     *
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(c.getTime());
    }

    /**
     * Возвращает текущую дату
     * по заданному паттерну
     *
     * @param pattern петтерн
     */
    public static String getCurrentDate(String pattern) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    /**
     * Возвращает текущую дату с временем до секунды
     * Пример: 19961025 11:30:30
     *
     */
    public static String getCurrentDateWithTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(c.getTime());
    }

    /**
     * Возвращает текущее время
     * Пример: 11:30:30
     *
     */
    public static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(c.getTime()).replace(":", "");
    }

    /**
     * Возвращает текущее время
     * по заданному паттерну
     *
     * @param pattern петтерн
     */
    public static String getCurrentTime(String pattern) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime()).replace(":", "");
    }


}
