/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author asus
 */
public class CurrentDate {

    private CurrentDate() {
    }

    public static Date getCurrentDate() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        return date;

    }

    public static String Date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public String Time() {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        //get current date time with Date()
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }
}
