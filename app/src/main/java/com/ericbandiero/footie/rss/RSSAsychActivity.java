package com.ericbandiero.footie.rss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ericbandiero.footie.AppConfig;
import com.ericbandiero.footie.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Base rrs runner
 */

public class RSSAsychActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private static final String TOPSTORIES =
            "http://feeds.news.com.au/public/rss/2.0/news_national_3354.xml";
    protected ListView mRssListView;
    protected NewsFeedParser mNewsFeeder;
    protected List<RSSFeed> mRssFeedList;
    protected RssAdapter mRssAdap;
    protected List<RSSFeed> mRssAllFeedList = new ArrayList<>(100);
    protected static Map<String, Integer> mapUrls = new LinkedHashMap<>(5);
    protected EditText editsearch;
    protected int days_old = 5;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_asych_local);
        if (AppConfig.DEBUG)
            Log.v(this.getClass().getSimpleName() + ">", "Version:" + Build.VERSION.RELEASE);
        if (AppConfig.DEBUG)
            Log.v(this.getClass().getSimpleName() + ">", "Version:" + Build.VERSION.SDK_INT);
        textview = (TextView) findViewById(R.id.textView1);
        textview.requestFocus();
        if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Set content");
        mRssListView = (ListView) findViewById(R.id.rss_list_view);
        mRssFeedList = new ArrayList<RSSFeed>();
        Spannable wordtoSpan;

        //textview.setText("Sources"+LINE_SEPARATOR);

        //This should be overridden in sub-classes.
        loadRssSources();

        colorTextView();


        for (String source : mapUrls.keySet()) {
            if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Source:" + source);
            new DoRssFeedTask().execute(source);
        }

        mRssListView.setOnItemClickListener(this);
// Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);
// Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                if (text != null & mRssAdap != null) {
                    mRssAdap.filter(text);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void colorTextView() {
        //        String normalBefore = "First Part Not Bold ";
//        String normalBOLD = "BOLD ";
//        String normalAfter = "rest not bold";
//        String finalString = normalBefore + normalBOLD + normalAfter;
//        SpannableStringBuilder sb = new SpannableStringBuilder(finalString);
        SpannableStringBuilder sb = new SpannableStringBuilder();
//        sb.setSpan(new BackgroundColorSpan(Color.CYAN), 0, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        sb.setSpan(new BackgroundColorSpan(Color.GREEN), 20, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        int lengthOfText = sb.length();
//        sb.append("Hey ho!");
//        sb.setSpan(new BackgroundColorSpan(Color.YELLOW), sb.length() - 8, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//resize size
//        textview.setText(sb);

        sb.clear();
        int startPosition = 0;
        int endPosition = 0;


        for (String source : mapUrls.keySet()) {
            sb.append(source + LINE_SEPARATOR);
            endPosition = endPosition + source.length();
            sb.setSpan(new BackgroundColorSpan(mapUrls.get(source)), startPosition, endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            startPosition = endPosition++;
            if (AppConfig.DEBUG)
                Log.v(this.getClass().getSimpleName() + ">", "Value:" + mapUrls.get(source).toString());
        }

        textview.setText(sb);
    }

    /**
     * This should be overridden in base classes.
     */
    public void loadRssSources() {
        mapUrls.clear();
        mapUrls.put("http://feeds.news.com.au/rss/newslocal/dt_nlocal_centralcoast_news_3173.xml", Color.GREEN);
        mapUrls.put("http://www.abc.net.au/local/rss/centralcoast/all.xml", Color.YELLOW);
        mapUrls.put("http://newslocal.newspaperdirect.com/epaper/services/rss.ashx?cid=8745&type=full", Color.CYAN);
        //mapUrls.put("http://rss.cnn.com/rss/cnn_topstories.rss",Color.parseColor("red"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asych, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Click!");
        if (AppConfig.DEBUG)
            Log.v(this.getClass().getSimpleName() + ">", mRssAllFeedList.get(position).toString());
        if (AppConfig.DEBUG)
            Log.v(this.getClass().getSimpleName() + ">", mRssAllFeedList.get(position).getLink());
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRssAllFeedList.get(position).getLink()));
        startActivity(browserIntent);
    }


    public static int getColorFromMap(String url) {

        int color;
        if (mapUrls.get(url) == null) {
            color = Color.WHITE;
        } else {
            color = mapUrls.get(url);
        }
            return color;
    }

    private class RssAdapter extends ArrayAdapter<RSSFeed> {
        private List<RSSFeed> rssFeedLst;
        private ArrayList<RSSFeed> arraylist;

        public RssAdapter(Context context, int textViewResourceId, List<RSSFeed> rssFeedLst) {
            super(context, textViewResourceId, rssFeedLst);
            this.rssFeedLst = rssFeedLst;
            this.arraylist = new ArrayList<RSSFeed>();
            this.arraylist.addAll(rssFeedLst);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            RssHolder rssHolder = null;
            if (convertView == null) {
                view = View.inflate(RSSAsychActivity.this, R.layout.rss_list_item, null);
                rssHolder = new RssHolder();
                rssHolder.rssTitleView = (TextView) view.findViewById(R.id.rss_title_view);
                view.setTag(rssHolder);
            } else {
                rssHolder = (RssHolder) view.getTag();
                if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Ru-using view");
            }
            RSSFeed rssFeed = rssFeedLst.get(position);
            rssHolder.rssTitleView.setText(rssFeed.getTitle());
            rssHolder.rssTitleView.setBackgroundColor(rssFeed.getBackColor());

            return view;
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "In Filter");
            rssFeedLst.clear();
            if (charText.length() == 0) {
                rssFeedLst.addAll(arraylist);
            } else {
                for (RSSFeed rs : arraylist) {
                    if (rs.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                        rssFeedLst.add(rs);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    static class RssHolder {
        public TextView rssTitleView;
    }

    public class DoRssFeedTask extends AsyncTask<String, Void, List<RSSFeed>> {
        ProgressDialog prog;
        String jsonStr = null;
        Handler innerHandler;


        @Override
        protected void onPreExecute() {
            prog = new ProgressDialog(RSSAsychActivity.this);
            prog.setMessage("Loading....");
            prog.show();
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
        if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName()+">","Running");
            for (String urlVal : params) {
                mNewsFeeder = new NewsFeedParser(urlVal, days_old);

            }
            mRssFeedList = mNewsFeeder.parse();
            return mRssFeedList;
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
            prog.dismiss();
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mRssAllFeedList.addAll(mRssFeedList);
                    mRssAdap = new RssAdapter(RSSAsychActivity.this, R.layout.rss_list_item,
                            mRssAllFeedList);
                    int count = mRssAdap.getCount();

                    if (count != 0 && mRssAdap != null) {
                        mRssListView.setAdapter(mRssAdap);
                    }
                }
            });
            if (AppConfig.DEBUG)
                Log.i(this.getClass().getSimpleName() + ">", "Items from current source:" + mRssFeedList.size());
            if (AppConfig.DEBUG)
                Log.i(this.getClass().getSimpleName() + ">", "Total items:" + mRssAllFeedList.size());


        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }


    }
}

