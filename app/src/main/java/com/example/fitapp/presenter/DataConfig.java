package com.example.fitapp.presenter;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

public class DataConfig {
    public static String GetCurrentDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        return dayOfTheWeek;
    }
}
