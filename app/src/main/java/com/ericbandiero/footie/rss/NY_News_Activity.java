package com.ericbandiero.footie.rss;

import android.graphics.Color;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ericbandiero.footie.R;

import static android.net.nsd.NsdManager.*;

public class NY_News_Activity extends RSSAsychActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void loadRssSources() {
        mapUrls.clear();
        mapUrls.put("http://feeds.nydailynews.com/nydnrss/new-york", Color.WHITE);
        mapUrls.put("http://nypost.com/metro/feed/", Color.CYAN);
        mapUrls.put("http://www.dnainfo.com/new-york/index/all", Color.YELLOW);
        mapUrls.put("http://7online.com/feed/", Color.MAGENTA);
        mapUrls.put("http://www.nbcnewyork.com/news/local/?rss=y&embedThumb=y&summary=y", Color.LTGRAY);
        mapUrls.put("http://www.westsiderag.com/feed",getResources().getColor(R.color.LightPink));
        mapUrls.put("http://rss.nytimes.com/services/xml/rss/nyt/Dance.xml",getResources().getColor(R.color.Azure));
    }
}
