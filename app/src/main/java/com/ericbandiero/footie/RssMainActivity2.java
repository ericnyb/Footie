package com.ericbandiero.footie;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ericbandiero.librarymain.AppConstant;
import com.ericbandiero.rssreader.SourceRSS;
import com.ericbandiero.rssreader.activity.RSSAsynchActivity;

public class RssMainActivity2 extends RSSAsynchActivity{
	int whichToRun=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		fetchMessages=true;
		super.onCreate(savedInstanceState);
		//We need to commit this so it doesn't show in other branch.
		setContentView(R.layout.activity_rss_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		//We will stash this change
		setTitle("Hello!");
		ActionBar actionBar=getSupportActionBar();
			if (AppConstant.DEBUG) Log.d(this.getClass().getSimpleName()+">",actionBar==null?"Actionbar is null":"Action is not null");

	////	toolbar.setTitle("Hello");
	//	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	//	getSupportActionBar().setHomeButtonEnabled(true);


//		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//		fab.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//						.setAction("Action", null).show();
//			}
//		});

	//	getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState != null) {
			// Restore value of members from saved state
			whichToRun = savedInstanceState.getInt("WHICH_TO_RUN");
			//setSourceMenuItem();
			if (AppConstant.DEBUG) Log.d(this.getClass().getSimpleName()+">","Which resource to run:"+whichToRun);
			//mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
		}
	}

	@Override
	public void loadRssSources() {
	if (AppConstant.DEBUG) Log.d(this.getClass().getSimpleName()+">","Loading resources!");

		int defaultDaysBackToGo = 30;
		Context context=getApplicationContext();
		switch (whichToRun) {
			case 1:
				prefFilterKeyNameRssSuffix = "local";
				//list_sources.add(new SourceRSS("http://www.football365.com/premier-league/rss", "Football 365", ContextCompat.getColor(context, R.color.APP_COLOR_365), null, defaultDaysBackToGo, "http://www.nydailynews.com"));
				//list_sources.add(new SourceRSS("http://www.fifa.com/rss/index.xml", "FIFA", ContextCompat.getColor(context, R.color.APP_COLOR_FIFA), null, defaultDaysBackToGo, "http://nypost.com"));
				list_sources.add(new SourceRSS("http://feeds.feedburner.com/soccernewsfeed", "Soccer news", ContextCompat.getColor(context, R.color.APP_COLOR_SOCCER_NEWS), null, defaultDaysBackToGo, "http://www.soccernews.com"));
				list_sources.add(new SourceRSS("http://www.espnfc.com/rss", "ESPN Soccer", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN), null, defaultDaysBackToGo, "http://www.espnfc.us/"));
				list_sources.add(new SourceRSS("http://www.espnfc.com/major-league-soccer/19/rss", "ESPN MSL", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN_MLS), null, defaultDaysBackToGo, "http://www.espnfc.us/major-league-soccer/19/index"));
				list_sources.add(new SourceRSS("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=soccer", "Fox Soccer", ContextCompat.getColor(context, R.color.APP_COLOR_FOX), null, defaultDaysBackToGo, "http://www.foxsports.com/soccer"));
				//https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=soccer
				break;
		}
	}
}
