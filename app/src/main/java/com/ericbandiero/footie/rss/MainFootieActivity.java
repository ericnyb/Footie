package com.ericbandiero.footie.rss;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.ericbandiero.footie.R;
import com.ericbandiero.rssreader.SourceRSS;
import com.ericbandiero.rssreader.activity.RSSAsynchActivity;

/**
 * Simple subclass of our rss feed
 * Created by ${"Eric Bandiero"} on 5/5/2017.
 */

public class MainFootieActivity extends RSSAsynchActivity {
	int whichToRun=1;
	int defaultDaysBackToGo = 30;
	Context context=this;
	@Override
	public void loadRssSources() {
		prefFilterKeyNameRssSuffix="National";
		list_sources.add(new SourceRSS("http://feeds.feedburner.com/soccernewsfeed", "Soccer news", ContextCompat.getColor(context, R.color.APP_COLOR_SOCCER_NEWS), null, defaultDaysBackToGo, "http://www.soccernews.com"));
		list_sources.add(new SourceRSS("http://www.espnfc.com/rss", "ESPN Soccer", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN), null, defaultDaysBackToGo, "http://www.espnfc.us/"));
		list_sources.add(new SourceRSS("http://www.espnfc.com/major-league-soccer/19/rss", "ESPN MSL", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN_MLS), null, defaultDaysBackToGo, "http://www.espnfc.us/major-league-soccer/19/index"));
		list_sources.add(new SourceRSS("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=soccer", "Fox Soccer", ContextCompat.getColor(context, R.color.APP_COLOR_FOX), null, defaultDaysBackToGo, "http://www.foxsports.com/soccer"));
	}
}
