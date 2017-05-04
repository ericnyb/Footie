package com.ericbandiero.footie;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.ericbandiero.librarymain.AppConstant;
import com.ericbandiero.rssreader.SourceRSS;
import com.ericbandiero.rssreader.activity.RSSAsynchActivity;

public class RssMainActivity2 extends RSSAsynchActivity{
	int whichToRun=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (AppConstant.DEBUG) Log.d(this.getClass().getSimpleName()+">","Here!");
		super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_rss_main);
		//Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		//setSupportActionBar(toolbar);

		setTitle("Hello!");
		ActionBar actionBar=getSupportActionBar();
		if (actionBar==null){
			if (AppConstant.DEBUG) Log.d(this.getClass().getSimpleName()+">","Actionbar is null");
		}

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

		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
				list_sources.add(new SourceRSS("http://feeds.feedburner.com/soccernewsfeed", "Soccer news", ContextCompat.getColor(context, R.color.APP_COLOR_FIFA), null, defaultDaysBackToGo, "http://www.soccernews.com"));
				list_sources.add(new SourceRSS("http://www.espnfc.com/rss", "ESPN Soccer", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN), null, defaultDaysBackToGo, "http://www.espnfc.us/"));
				list_sources.add(new SourceRSS("http://www.espnfc.com/major-league-soccer/19/rss", "ESPN MSL", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN_MLS), null, defaultDaysBackToGo, "http://www.espnfc.us/major-league-soccer/19/index"));
				break;
		}
	}
}
