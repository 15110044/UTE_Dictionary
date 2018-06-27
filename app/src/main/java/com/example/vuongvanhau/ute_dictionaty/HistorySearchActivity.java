package com.example.vuongvanhau.ute_dictionaty;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HistorySearchActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Dictionary> words = new ArrayList<Dictionary>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_search);
        try{
            DBDictionary db = new DBDictionary(this);
            FileInputStream fin = openFileInput("histories.txt");
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
                    words.add(w);
                    w = new Dictionary();
                    dem=0;
                }
            }
        }
        catch (Exception ex)
        {

        }
        try {
            MyAsyncTask gandulieu = new MyAsyncTask();
            gandulieu.execute();
            lv = (ListView) findViewById(R.id.wordhistorysearch);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Intent sendword = new Intent(HistorySearchActivity.this, SearchResultActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("word", words.get(arg2).getWord());
                    extras.putString("nghia", words.get(arg2).getNghia());
                    extras.putString("dnghia", words.get(arg2).getDnghia());
                    extras.putString("image", words.get(arg2).getImage());
                    sendword.putExtras(extras);
                    startActivity(sendword);
                }
            });
        }
        catch (Exception ex)
        {

        }
    }
    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(HistorySearchActivity.this, "Wating", "Read History");

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            try {

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        //Khi thực thi
        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            try {
                dialog.dismiss();
                HistorySearchActivity.MyAdapter adapter = new HistorySearchActivity.MyAdapter(getApplicationContext(), R.layout.activity_word_view, words);
                lv.setAdapter(adapter);
            } catch (Exception ex) {

            }
        }
    }

    class MyAdapter extends ArrayAdapter<Dictionary> {

        Context context;
        ArrayList<Dictionary> items;

        public MyAdapter(Context context, int textViewResourceId, ArrayList<Dictionary> objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
            this.context = context;
            this.items = objects;
        }

        //set thông tin
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            LayoutInflater inf = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowview = inf.inflate(R.layout.activity_word_view, parent, false);
            TextView tv_title = (TextView) rowview.findViewById(R.id.textview);

            tv_title.setText(items.get(position).getWord().toString());

            return rowview;

        }
    }
}
