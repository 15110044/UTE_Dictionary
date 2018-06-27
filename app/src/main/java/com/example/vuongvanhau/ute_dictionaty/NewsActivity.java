package com.example.vuongvanhau.ute_dictionaty;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class NewsActivity extends TabActivity {

    TabHost mTabHost;
    WebView webcontext;
    String pagelink;
    ProgressDialog progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);

        try {
            setupTab(new TextView(this), "Chanel");
            setupTab(new TextView(this), "Adidas");
            setupTab(new TextView(this), "Gucci");
            setupTab(new TextView(this), "Versace");
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

        webcontext = (WebView)findViewById(R.id.webnews);
        webcontext.getSettings().setJavaScriptEnabled(true);
        pagelink = "https://www.chanel.com/en_GB/fashion.html";
        Loadpage();
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId)
                {
                    case "Chanel":
                        //webcontext.stopLoading();
                        pagelink = "https://www.chanel.com/en_GB/fashion.html";
                        Loadpage();
                        break;
                    case "Adidas":
                        //webcontext.stopLoading();
                        pagelink = "https://www.adidas.com.vn/";
                        Loadpage();
                        break;
                    case "Gucci":
                        //webcontext.stopLoading();
                        pagelink = "https://www.gucci.com/int/en/";
                        Loadpage();
                       break;
                    case "Versace":
                        //webcontext.stopLoading();
                        pagelink = "https://www.versace.com/international/en/home/";
                        Loadpage();
                        break;
                }
            }
        });
    }

    private void setupTab(final View view, final String tag) {
        View tabview = createTabView(view.getContext(), tag);
        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return view;
            }
        });
        mTabHost.addTab(setContent);
    }

    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
    }
    protected  void Loadpage()
    {
        progressbar=ProgressDialog.show(this, "Waiting", "Loading.....");
        webcontext.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                if(progressbar.isShowing())
                    progressbar.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                webcontext.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webcontext.loadUrl(pagelink);
    }
}
