package com.example.vuongvanhau.ute_dictionaty;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    SearchView sv;
    ListView lv;
    ArrayList<Dictionary> words = new ArrayList<Dictionary>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sv = (SearchView) findViewById(R.id.txtsearch);
        lv = (ListView) findViewById(R.id.listword);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //tìm từ cho vào words
                Dictionary dc = new Dictionary();
                dc.setWord("abc");
                words.add(dc);
                MyAsyncTask gandulieu = new MyAsyncTask();
                gandulieu.execute();
                return false;
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(SearchActivity.this, "Wating", "Read Name Music");

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
                MyAdapter adapter = new MyAdapter(getApplicationContext(), R.layout.activity_word_view, words);
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
