package com.ericbandiero.footie.rss;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ericbandiero.footie.AppConstant;
import com.ericbandiero.footie.R;
import com.ericbandiero.librarymain.UtilsShared;
import com.ericbandiero.librarymain.interfaces.IReturnDialogInt;
import com.ericbandiero.rssreader.SourceRSS;
import com.ericbandiero.rssreader.activity.RSSAsynchActivity;

/**
 * Simple subclass of our rss feed
 * Made a change in development.
 * Created by ${"Eric Bandiero"} on 5/5/2017.
 */

public class MainFootieActivity extends RSSAsynchActivity {
	int whichToRun=1;
	int defaultDaysBackToGo = 30;
	Context context=this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		fetchMessages=true;
		super.onCreate(savedInstanceState);
		//We override oncreate to customize rss base toolbar.
		Toolbar toolbar= (Toolbar) findViewById(R.id.rss_toolbar);
		//toolbar.setTitleTextColor(ContextCompat.getColor(context,R.color.Blue));
		//toolbar.setBackgroundColor(ContextCompat.getColor(context,R.color.Bisque));
	}

	@Override
	public void loadRssSources() {
		//Need to fix this.
		prefFilterKeyNameRssSuffix="National";
		list_sources.add(new SourceRSS("http://feeds.feedburner.com/soccernewsfeed", "Soccer news", ContextCompat.getColor(context, R.color.APP_COLOR_SOCCER_NEWS), null, defaultDaysBackToGo, "http://www.soccernews.com"));
		list_sources.add(new SourceRSS("http://www.espnfc.com/rss", "ESPN Soccer", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN), null, defaultDaysBackToGo, "http://www.espnfc.us/"));
		list_sources.add(new SourceRSS("http://www.espnfc.com/major-league-soccer/19/rss", "ESPN MSL", ContextCompat.getColor(context, R.color.APP_COLOR_ESPN_MLS), null, defaultDaysBackToGo, "http://www.espnfc.us/major-league-soccer/19/index"));
		list_sources.add(new SourceRSS("https://api.foxsports.com/v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU&tag=soccer", "Fox Soccer", ContextCompat.getColor(context, R.color.APP_COLOR_FOX), null, defaultDaysBackToGo, "http://www.foxsports.com/soccer"));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (AppConstant.DEBUG) Log.d(this.getClass().getSimpleName()+">","item..."+item.toString());
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (AppConstant.DEBUG) Log.d(this.getClass().getSimpleName()+">","Back pressed!");
		UtilsShared.AlertMessageSimpleYesNo(this, "Confirm Exit App", "Do you want to exit the app?", new IReturnDialogInt() {
			@Override
			public void execute(int i) {
				if (i== AlertDialog.BUTTON_POSITIVE){
					finish();
				}
			}
		});
//		Intent intent = new Intent(Intent.ACTION_MAIN);
//		intent.addCategory(Intent.CATEGORY_HOME);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(intent);
	}
}
