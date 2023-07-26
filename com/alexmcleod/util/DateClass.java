package com.alexmcleod.util;

import java.util.Calendar;

public class DateClass {
    private int day;
    private int month;
    private int year;

    public DateClass(String newDate) {
        String[] tokens = newDate.split("/");
        day = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        year = Integer.parseInt(tokens[2]);

/*         if(isInThePast())
        {
            throw new IllegalArgumentException("Food is old!");
        }*/
    }

    public String toString() {
        String dateString = new String(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        return dateString;
    }

    public void setDate(String newDate) {
        String[] tokens = newDate.split("/");
        day = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        year = Integer.parseInt(tokens[2]);
    }

    public boolean isInThePast() {
        boolean isOld = false;
        Calendar currentDate = Calendar.getInstance();
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(year, month - 1, day);
        if(expiryDate.before(currentDate) == true) {
            isOld = true;
        }
        return isOld;
    }
    
}
