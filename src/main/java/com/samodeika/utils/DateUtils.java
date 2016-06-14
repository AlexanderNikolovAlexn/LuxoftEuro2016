package com.samodeika.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class DateUtils {

    public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Date getDateFromString(String date, String mask) {
        DateFormat formatter = new SimpleDateFormat(mask);
        Date resultDate = null;
        try {
            resultDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    public static Date generateRandomDate() {
        long beginTime = Timestamp.valueOf("1980-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2000-12-31 00:00:00").getTime();
        long diff = endTime - beginTime + 1;
        Date date = new Date(beginTime + (long) (Math.random() * diff));
        return new Date(date.getYear(), date.getMonth(), date.getDay());
    }

    public static String printDate(Date date) {
        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        return formattedDate;
    }

    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
