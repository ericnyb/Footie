package com.ericbandiero.footie.rss;

import android.graphics.Color;
import android.os.Bundle;

public class AussieActivity extends RSSAsychActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void loadRssSources() {
        mapUrls.clear();
        mapUrls.put("http://www.smh.com.au/rssheadlines/nsw/article/rss.xml", Color.CYAN);
        mapUrls.put("http://www.smh.com.au/rssheadlines/political-news/article/rss.xml", Color.CYAN);
        mapUrls.put("http://feeds.feedburner.com/TheAustralianNewsNDM", Color.GREEN);
        mapUrls.put("http://www.abc.net.au/news/feed/46182/rss.xml", Color.YELLOW);
        mapUrls.put("http://www.abc.net.au/news/feed/52498/rss.xml", Color.YELLOW);
        mapUrls.put("http://www.theguardian.com/australia-news/rss", Color.parseColor("#ff9c9f"));
    }
}
