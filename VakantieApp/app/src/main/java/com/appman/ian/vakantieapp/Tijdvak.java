package com.appman.ian.vakantieapp;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ian on 31-5-2017.
 */

class Tijdvak implements Serializable{
    private String region;
    private Date startdate;
    private Date enddate;

    public Tijdvak(String region, Date startdate, Date enddate) {
        this.region = region;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public static String dateString(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String result = "";
        result += calendar.get(Calendar.DAY_OF_MONTH)+ "-";
        result += (calendar.get(Calendar.MONTH) + 1) + "-";
        result += calendar.get(Calendar.YEAR);
        return result;
    }
}