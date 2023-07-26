//purpose: A date class that allows us to store dates and perform
//         checks to see if the date is in the past.

package com.alexmcleod.util;

import java.util.Calendar;

public class DateClass {
    private int day;
    private int month;
    private int year;

    //constructor
    public DateClass(String newDate) {
        String[] tokens = newDate.split("/");
        day = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        year = Integer.parseInt(tokens[2]);
    }
    
    //toString
    public String toString() {
        String dateString = new String(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        return dateString;
    }

    //setter
    public void setDate(String newDate) {
        String[] tokens = newDate.split("/");
        day = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        year = Integer.parseInt(tokens[2]);
    }

    //purpose checks if this date is before the current date
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
