package com.ericbandiero.footie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ericbandiero.footie.rss.AussieActivity;
import com.ericbandiero.footie.rss.NY_News_Activity;
import com.ericbandiero.footie.rss.RSSAsychActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Eric Bandiero
 * Change 1 - where are we?
 * We made change after adding tag.
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    private List<RssData> list_RssData = new ArrayList<>();
    private volatile String headerTitle;
    private volatile String headerCopyright;
    private volatile String headerBuildDate;
    private List<String> list_sites = new ArrayList<>(5);
    private List<String> list_headers = new ArrayList<>(5);
    String sources = "Sources:";

    TextView textheader;

    TextView textheader2;

    ExecutorService execService = Executors.newSingleThreadExecutor();

    public final String LINE_SEPARATOR = "\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setContentView(R.layout.activity_main);
        textheader = (TextView) findViewById(R.id.textView);
        textheader2 = (TextView) findViewById(R.id.textView2);
        textheader.setBackgroundColor(Color.rgb(20, 200, 100));
       // textheader2.setBackgroundColor(Color.rgb(237, 211, 178));


        list_sites.add("http://www.football365.com/premier-league/rss");
        list_sites.add("http://www.fifa.com/rss/index.xml");
        list_sites.add("http://www.espn.co.uk/espn/rss/football/news");
        list_sites.add("http://www.espnfc.com/major-league-soccer/19/rss");

         Map<String, Integer> mapUrls = new LinkedHashMap<>(5);

        mapUrls.clear();
        mapUrls.put("http://www.football365.com/premier-league/rss",Color.WHITE);
        mapUrls.put("http://www.fifa.com/rss/index.xml",Color.CYAN);
        mapUrls.put("http://www.espn.co.uk/espn/rss/football/news",Color.YELLOW);
        mapUrls.put("http://www.espnfc.com/major-league-soccer/19/rss",Color.GREEN);


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

        textheader2.setText(sb);

        try {

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {

                for (String list_site : list_sites) {
                    if (AppConfig.DEBUG) Log.i(this.getClass().getSimpleName() + ">", "Starting:" + list_site);
                    sources = sources + LINE_SEPARATOR + list_site;
                    readNews(list_site);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                Toast t = Toast.makeText(this, "No internet", Toast.LENGTH_LONG);
                t.show();
                return;
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Length----------------------------;" + list_RssData.size());

        final ListView listview = (ListView) findViewById(R.id.listView);
        listview.setOnItemClickListener(this);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, list_text_string);

        SpecialAdapter adapter = new SpecialAdapter(this,
                android.R.layout.simple_list_item_1, list_RssData);
        listview.setAdapter(adapter);


        textheader.setText("Soccer news from around the globe." + LINE_SEPARATOR + "Please respect copyright information!");
       // textheader2.setText(sources);

        if (AppConfig.DEBUG)  Log.v(this.getClass().getSimpleName() + ">", "Size:" + list_headers.size());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = null;


        switch (id) {

            case R.id.action_settings:
                //  intent = new Intent(this, RSSAsychActivity.class);
                break;


            case R.id.action_run_asynch:
                intent = new Intent(this, RSSAsychActivity.class);
                break;

            case R.id.action_aussie:
                intent = new Intent(this, AussieActivity.class);
                break;
            case R.id.action_us_news:
                intent = new Intent(this, RssMainActivity2.class);
                break;

            case R.id.action_ny_news:
                intent = new Intent(this, NY_News_Activity.class);
                break;

        }


        if (intent != null) {
            startActivity(intent);
        }

        if (AppConfig.DEBUG) Log.i(this.getLocalClassName() + ">", "id:" + item.getTitle());
        //   Log.i(this.getLocalClassName() + ">", "id:" +);


        return true;

    }

    public void readNews(String mxlFileLocation) throws XmlPullParserException {
        fetchXML(mxlFileLocation);
    }



    public void fetchXML(final String urlstring1) {

        Thread threadToRun = new Thread(new Runnable() {
            @Override
            public void run() {
                if (AppConfig.DEBUG)
                    Log.v(this.getClass().getSimpleName() + ">", "Count:" + Thread.activeCount());

                try {
                    //  URL url = new URL("http://www.football365.com/premier-league/rss");
                    //  URL url = new URL("http://www.fifa.com/rss/index.xml");
                    URL url = new URL(urlstring1);
                    HttpURLConnection conn = (HttpURLConnection)
                            url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();
                    // myparser.setFeature(XmlPullParser.FEATURE_VALIDATION,false);
                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                            , false);
                    myparser.setInput(stream, null);
                    parseXMLAndStoreIt(myparser);
                    stream.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //   threadToRun.start();
        execService.execute(threadToRun);
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = "";
        String title = "";
        String desc = "";
        String link = "";
        int c;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();

                if (name != null) {
                    name = name.toLowerCase();
                }

                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
//          if (AppConfig.DEBUG)              Log.v(this.getClass().getSimpleName()+">",text.toString());
                        break;
                    case XmlPullParser.END_TAG:
                        // if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Type:" + name);
                        switch (name) {
                            case "title":
                                //  if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Title:" + text);
                                title = text;

                                break;
                            case "generator":
                                // if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Title:" + text);
                                title = text;

                                break;
                            case "description":
                                //if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Desc:" + text);

                                desc = text;

                                break;
                            case "link":
                                // if (AppConfig.DEBUG)    Log.v(this.getClass().getSimpleName() + ">", "Link:" + text);
                                link = text;
                                break;
                            case "item":
                                //   if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Item:" + name);
                                // addTextView(title);

                                //Log.v(this.getClass().getSimpleName() + ">", headerTitle);
                                if (headerTitle.contains("365")) {
                                    c = Color.WHITE;
                                } else if (headerTitle.toLowerCase().contains("fifa")) {
                                    c = Color.CYAN;
                                } else if (headerTitle.toLowerCase().contains("espn inc")) {
                                    c = Color.YELLOW;
                                } else if (headerTitle.toLowerCase().contains("major league")) {
                                    c = Color.GREEN;
                                } else {
                                    c = Color.DKGRAY;
                                }
                                list_RssData.add(new RssData(title, desc, link, headerTitle, c));
                                break;

                            case "lastbuilddate":

                                if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "build:" + text);
                                headerBuildDate = text;
                                headerTitle = title;
                                break;
                            case "copyright":
                                // Log.v(this.getClass().getSimpleName()+">","Copy:"+text);
                                // Log.v(this.getClass().getSimpleName()+">","Copy:"+headerCopyright);
                                headerTitle = title;
                                headerCopyright = text;
                                list_headers.add(headerTitle + LINE_SEPARATOR + headerCopyright);

                                if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Header title:" + headerTitle);
                                if (headerTitle.contains("365")) {
                                    c = Color.WHITE;
                                } else if (headerTitle.toLowerCase().contains("fifa")) {
                                    c = Color.CYAN;
                                } else if (headerTitle.toLowerCase().contains("espn inc")) {
                                    c = Color.YELLOW;
                                } else if (headerTitle.toLowerCase().contains("major league")) {
                                    c = Color.GREEN;
                                } else {
                                    c = Color.DKGRAY;
                                }
                                if (AppConfig.DEBUG)
                                    if (AppConfig.DEBUG)  Log.v(this.getClass().getSimpleName() + ">", "Logging data" + headerTitle);
                                list_RssData.add(new RssData(headerTitle + LINE_SEPARATOR + headerCopyright, desc, link, headerTitle, c));
                                break;
                            default:
                                break;
                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (AppConfig.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Click!");
        if (AppConfig.DEBUG)
            Log.v(this.getClass().getSimpleName() + ">", list_RssData.get(position).toString());
        if (AppConfig.DEBUG)
            if (AppConfig.DEBUG)   Log.v(this.getClass().getSimpleName() + ">", list_RssData.get(position).getLink());
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list_RssData.get(position).getLink()));
        startActivity(browserIntent);
    }

}
