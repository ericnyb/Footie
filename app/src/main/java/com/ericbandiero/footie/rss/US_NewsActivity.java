package com.ericbandiero.footie.rss;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.ericbandiero.footie.AppConfig;
import com.ericbandiero.footie.BuildConfig;

public class US_NewsActivity extends RSSAsychActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void loadRssSources() {
        mapUrls.clear();
        mapUrls.put("http://rss.cnn.com/rss/cnn_topstories.rss", Color.WHITE);
        mapUrls.put("http://feeds.reuters.com/Reuters/domesticNews", Color.CYAN);
        mapUrls.put("http://feeds.foxnews.com/foxnews/latest", Color.YELLOW);
        mapUrls.put("http://www.usnews.com/rss/news", Color.MAGENTA);
        mapUrls.put("http://feeds.washingtonpost.com/rss/national", Color.LTGRAY);
        mapUrls.put("http://www.washingtontimes.com/rss/headlines/news/national/", Color.RED);
    }
}
