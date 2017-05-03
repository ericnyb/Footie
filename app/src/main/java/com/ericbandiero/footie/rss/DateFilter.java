package com.ericbandiero.footie.rss;

import android.util.Log;

import com.ericbandiero.footie.AppConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFilter implements IDateFilter {

    public DateFilter() {

    }

    @Override
    public boolean isDateOk(String pub_date, int days_old) {
        boolean isOk = false;

        //Temp - always run this code no matter if it has gmt
        //We need to guess at format before trying to parse.
        if (true || pub_date.toLowerCase().contains("gmt")) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
            Date date = null;
            try {
                date = formatter.parse(pub_date);
                Calendar calNow = GregorianCalendar.getInstance();
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(date);

                long mill1 = calNow.getTimeInMillis();
                long mill2 = cal.getTimeInMillis();

                long diff = mill1 - mill2;

                int days = (int) (diff / (1000 * 60 * 60 * 24));
                if (AppConfig.DEBUG)  Log.v(this.getClass().getSimpleName() + ">", pub_date);
                if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Days:" + days);
                if (days <= days_old) {
                    if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", pub_date);

                    return true;
                }

            } catch (ParseException e) {
                if (AppConfig.DEBUG) Log.i(this.getClass().getSimpleName()+">","Could not parse date:"+pub_date);
                e.printStackTrace();
                return true;
            }
        }
        return isOk;
    }
}