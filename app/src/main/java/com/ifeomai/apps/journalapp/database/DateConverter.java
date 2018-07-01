package com.ifeomai.apps.journalapp.database;

import android.arch.persistence.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String toCustomFormat(String string){
        Date date = toDate(Long.valueOf(string));
        return new SimpleDateFormat("MMM", Locale.getDefault()).format(date) + " " + new SimpleDateFormat("dd", Locale.getDefault()).format(date);
    }
    public static String toString(String string){
        Date date = toDate(Long.valueOf(string));
        return new SimpleDateFormat("E, dd MMM yyyy hh:mm a", Locale.getDefault()).format(date);
    }
}

