package com.ericbandiero.footie;


import android.graphics.Color;
import android.util.Log;

/**
 * Created by Eric on 3/27/2015.
 */
public class RssData {

    private String title;
    private String description;
    private String link;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String source;


    public RssData(String title, String description, String link, String source, int colortoset) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.source = source;
        this.color = colortoset;
        //  Log.v(this.getClass().getSimpleName() + ">","Source:"+colortoset);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RssData rssData = (RssData) o;

        if (!description.equals(rssData.description)) return false;
        if (!link.equals(rssData.link)) return false;
        if (!title.equals(rssData.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return title;
    }
}
