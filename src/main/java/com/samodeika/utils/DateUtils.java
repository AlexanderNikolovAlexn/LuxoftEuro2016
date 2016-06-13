package com.samodeika.utils;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class DateUtils {

    public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}
