package com.example.vuongvanhau.ute_dictionaty;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity {
    TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);

        try {
            View vsearch = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_search, null);
            View tabviews = createTabView(vsearch.getContext(), "Search");
            TabHost.TabSpec setContents = mTabHost.newTabSpec("Search").setIndicator(tabviews).setContent(new Intent(this,SearchActivity.class));
            mTabHost.addTab(setContents);

            View vresult = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_search_result, null);
            View tabviewr = createTabView(vresult.getContext(), "Result");
            TabHost.TabSpec setContentr = mTabHost.newTabSpec("Result").setIndicator(tabviewr).setContent(new Intent(this,SearchResultActivity.class));
            mTabHost.addTab(setContentr);

            View vhistory = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_history_search, null);
            View tabviewh = createTabView(vhistory.getContext(), "History");
            TabHost.TabSpec setContenth = mTabHost.newTabSpec("History").setIndicator(tabviewh).setContent(new Intent(this,HistorySearchActivity.class));
            mTabHost.addTab(setContenth);

            View vnews = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_news, null);
            View tabviewn = createTabView(vnews.getContext(), "News");
            TabHost.TabSpec setContentn = mTabHost.newTabSpec("News").setIndicator(tabviewn).setContent(new Intent(this,NewsActivity.class));
            mTabHost.addTab(setContentn);


        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }

    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
    }
}
