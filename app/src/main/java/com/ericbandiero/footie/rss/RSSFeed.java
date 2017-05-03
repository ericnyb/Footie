package com.ericbandiero.footie.rss;

import android.graphics.Color;

/**
 * Created by Eric Bandiero on 4/3/2015.
 */
public class RSSFeed {

    private String title;
    private String link;
    private String description;
    private String category;
    private String pubDate;
    private String guid;
    private String feedburnerOrigLink;
    private int backColor;


    public RSSFeed() {
    }

    public RSSFeed(String title, String link, String description, String category, String pubDate,
                   String guid, String feedburnerOrigLink, int backcolor) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.category = category;
        this.pubDate = pubDate;
        this.guid = guid;
        this.feedburnerOrigLink = feedburnerOrigLink;
        this.backColor = backcolor;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getGuid() {
        return guid;
    }

    public String getFeedburnerOrigLink() {
        return feedburnerOrigLink;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setFeedburnerOrigLink(String feedburnerOrigLink) {
        this.feedburnerOrigLink = feedburnerOrigLink;
    }

    public int getBackColor() {
        return backColor;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }
}
