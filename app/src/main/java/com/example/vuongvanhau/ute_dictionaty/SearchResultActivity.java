package com.example.vuongvanhau.ute_dictionaty;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends TabActivity {

    TabHost mTabHost;
    TextView txtcontent;
    String word = null;
    String nghia = null;
    String dnghia= null;
    String image= null;
    ImageView imv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);

        try {
            setupTab(new TextView(this), "Nghĩa");
            setupTab(new TextView(this), "Đồng Nghĩa");
            setupTab(new TextView(this), "Phát Âm");
            setupTab(new TextView(this), "Ảnh");
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        Bundle data = getIntent().getExtras();
        word = data.getString("word");
        nghia = data.getString("nghia");
        dnghia = data.getString("dnghia");
        image = data.getString("image");
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendword = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(sendword);
            }
        });
        imv = (ImageView) findViewById(R.id.phatam);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Phatam();
                }
                catch (Exception ex)
                {

                }
            }
        });
        txtcontent = (TextView) findViewById(R.id.textcontent);
        txtcontent.setText(nghia.toString());
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId)
                {
                    case "Nghĩa":
                        imv.setBackground(null);
                        txtcontent.setText(nghia.toString());
                        break;
                    case "Đồng Nghĩa":
                        imv.setBackground(null);
                        txtcontent.setText(dnghia.toString());
                        break;
                    case "Phát Âm":
                        txtcontent.setText(word.toString()+".mp3");
                        imv.setBackgroundResource(R.drawable.phatam);
                        break;
                    case "Ảnh":
                        imv.setBackground(null);
                        txtcontent.setText(image.toString());
                        break;
                }
                //Toast.makeText(getApplicationContext(),tabId +"-> "+ word.toString() +", "+ nghia.toString() +", "+ dnghia.toString() +", "+ tnghia.toString() +", "+ image.toString() +".",Toast.LENGTH_LONG).show();
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
    private int getRawResIdByName(String resName, String folder) {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        // Trả về 0 nếu không tìm thấy.
        int resID = this.getResources().getIdentifier(resName, folder, pkgName);
        return resID;
    }
    protected void Phatam()
    {
        try {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, this.getRawResIdByName(word.toString(), "raw"));
            mediaPlayer.start();
        }
        catch (Exception ex)
        {

        }
    }
}
