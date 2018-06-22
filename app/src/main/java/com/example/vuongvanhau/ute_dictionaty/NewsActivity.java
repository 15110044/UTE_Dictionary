package com.example.vuongvanhau.ute_dictionaty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {

    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        wv = (WebView) findViewById(R.id.news);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.google.com/");
        Toast.makeText(getApplicationContext(),"Load Web",Toast.LENGTH_LONG).show();
    }
}
