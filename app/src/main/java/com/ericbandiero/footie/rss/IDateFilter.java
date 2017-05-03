package com.ericbandiero.footie.rss;

/**
 * Created by ${"Eric Bandiero"} on 4/11/2015.
 */
public interface IDateFilter {
    boolean isDateOk(String pub_date, int daysOld);
}
