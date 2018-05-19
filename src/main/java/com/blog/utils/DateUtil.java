package com.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(Date date, String formater){
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }
}
