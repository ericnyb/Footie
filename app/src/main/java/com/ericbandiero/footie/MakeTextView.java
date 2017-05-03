package com.ericbandiero.footie;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.TextView;

/**
 * Created by Eric on 3/27/2015.
 */
public class MakeTextView {

    public static TextView getTextView(Context ctx) {

        TextView tv = new TextView(ctx);
        tv.setTextColor(Color.BLUE);
        tv.setBackgroundColor(Color.WHITE);

        tv.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                ActionMenuView.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
        return tv;
    }

}
