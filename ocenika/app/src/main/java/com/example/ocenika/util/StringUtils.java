package com.example.ocenika.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtils {

    public static String fakeEmailGenerator() {
        String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder email = new StringBuilder();
        Random rnd = new Random();
        while (email.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARSET.length());
            email.append(CHARSET.charAt(index));
        }
        return email.toString() +  "@gmail.com";
    }

    public static String dateFormatted(Date date) {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(date);
        return formattedDate;
    }
}
