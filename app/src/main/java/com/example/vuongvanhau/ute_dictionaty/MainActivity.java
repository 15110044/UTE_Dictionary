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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends TabActivity {
    TabHost mTabHost;
    int load = 1;
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

            View vhistory = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_history_search, null);
            View tabviewh = createTabView(vhistory.getContext(), "History");
            TabHost.TabSpec setContenth = mTabHost.newTabSpec("History").setIndicator(tabviewh).setContent(new Intent(this,HistorySearchActivity.class));
            mTabHost.addTab(setContenth);

            View vnews = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_news, null);
            View tabviewn = createTabView(vnews.getContext(), "News");
            TabHost.TabSpec setContentn = mTabHost.newTabSpec("News").setIndicator(tabviewn).setContent(new Intent(this,NewsActivity.class));
            mTabHost.addTab(setContentn);


            try{
                FileInputStream fin = openFileInput("loadtu.txt");
                BufferedReader brfin = new BufferedReader(new InputStreamReader(fin));
                String cfin = null;
                while ((cfin = brfin.readLine()) != null) {
                    load = Integer.parseInt(cfin.toString());
                }
            }
            catch (Exception ex)
            {

            }
            if(load==1)
            {
                LoadData();
                load = 0;
                String datascore = String.valueOf(load);
                FileOutputStream fOutgame = openFileOutput("loadtu.txt", Context.MODE_PRIVATE);
                fOutgame.write(datascore.getBytes());
                fOutgame.close();
            }

        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

    }

    protected void  LoadData()
    {
        try {
            DBDictionary db = new DBDictionary(this);
            InputStream fin = getResources().openRawResource(R.raw.dictionaryfile);
            BufferedReader brfin = new BufferedReader(new InputStreamReader(fin));
            String cfin = null;
            int dem = 0;
            Dictionary w = new Dictionary();
            while ((cfin = brfin.readLine()) != null) {
                switch (dem) {
                    case 0: {
                        w.setWord(cfin.toString());
                        dem++;
                        break;
                    }
                    case 1:
                    {
                        w.setNghia(cfin.toString());
                        dem++;
                        break;
                    }
                    case 2:
                    {
                        w.setDnghia(cfin.toString());
                        dem++;
                        break;
                    }
                    case 3:
                    {
                        w.setImage(cfin.toString());
                        dem++;
                        break;
                    }
                }
                if(dem==4)
                {
                    db.them_word(w);
                    w = new Dictionary();
                    dem=0;
                }
            }
        }
        catch (Exception ex)
        {

        }
    }
    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
    }
}
