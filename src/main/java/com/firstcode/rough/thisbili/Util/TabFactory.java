package com.firstcode.rough.thisbili.Util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

/**
 * Created by Rough on 2016/5/26.
 */
public class TabFactory implements TabHost.TabContentFactory{
    private Context context;
    public TabFactory(Context context){
        this.context =context;
    }

    @Override
    public View createTabContent(String tag) {
        View v = new ImageView(context);
        v.setMinimumWidth(0);
        v.setMinimumHeight(0);
        return v;
    }
}
