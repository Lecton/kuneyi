package org.lecton.rest.rest.utils;

public class GenerateDate {
    public static String getCurrentTimeStamp(){
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime()).toString();
    }
}
