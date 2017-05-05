package com.ericbandiero.footie;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
  * This is our own adapter.
  * We use this in our list.
  * Created by Eric on 3/30/2015.
 */
public class SpecialAdapter extends ArrayAdapter {

    Context context;
    int layoutResourceId;
    List<RssData> data = new ArrayList<>();

    public SpecialAdapter(Context context, int resource) {
        super(context, resource);
    }


    public SpecialAdapter(Context context, int layoutResourceId, List list_objects) {
        super(context, layoutResourceId, list_objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = list_objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.v(this.getClass().getSimpleName()+">","convert:"+convertView==null?"null":"not null");


        View row = convertView;

        RssHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RssHolder();
            //holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView) row.findViewById(android.R.id.text1);

            row.setTag(holder);
        } else {
            if (AppConstant.DEBUG) Log.v(this.getClass().getSimpleName() + ">", "Re-using holder");
            holder = (RssHolder) row.getTag();
        }

        RssData rss = (RssData) data.get(position);
        holder.txtTitle.setText(rss.getTitle());
        holder.txtTitle.setBackgroundColor(rss.getColor());

        return row;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }


    static class RssHolder {
        TextView txtTitle;
    }

}
