package com.example.vuongvanhau.ute_dictionaty;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    SearchView sv;
    ListView lv;
    ArrayList<Dictionary> words = new ArrayList<Dictionary>();
    ArrayList<Dictionary> history = new ArrayList<Dictionary>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sv = (SearchView) findViewById(R.id.txtsearch);
        lv = (ListView) findViewById(R.id.listword);
        try {
            readhistory();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (words.get(arg2).getWord() != "find not found") {
                    try {
                        Dictionary dic = new Dictionary();
                        dic.setWord(words.get(arg2).getWord());
                        dic.setNghia(words.get(arg2).getNghia());
                        dic.setDnghia(words.get(arg2).getDnghia());
                        dic.setImage(words.get(arg2).getImage());
                        history.add(dic);
                        String datahictori = new String();
                        for (int i = 0; i < history.size(); i++) {
                            datahictori += history.get(i).getWord() + "\n";
                            datahictori += history.get(i).getNghia() + "\n";
                            datahictori += history.get(i).getDnghia() + "\n";
                            datahictori += history.get(i).getImage() + "\n";
                        }
                        FileOutputStream fOutgame = openFileOutput("histories.txt", Context.MODE_PRIVATE);
                        fOutgame.write(datahictori.getBytes());
                        fOutgame.close();

                        Intent sendword = new Intent(SearchActivity.this, SearchResultActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("word", words.get(arg2).getWord());
                        extras.putString("nghia", words.get(arg2).getNghia());
                        extras.putString("dnghia", words.get(arg2).getDnghia());
                        extras.putString("image", words.get(arg2).getImage());
                        sendword.putExtras(extras);
                        startActivity(sendword);

                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                DBDictionary db = new DBDictionary(SearchActivity.this);
                words  =  db.getWord(newText);
                //Toast.makeText(getApplicationContext(), String.valueOf(words.size() +"+ &"+newText+"&"),Toast.LENGTH_LONG).show();
                if(newText.length()>0) {
                    if(words.size()==0)
                    {
                        words = new ArrayList<Dictionary>();
                        Dictionary dic = new Dictionary();
                        dic.setWord("find not found");
                        dic.setNghia("");
                        dic.setDnghia("");
                        dic.setImage("");
                        words.add(dic);
                    }
                    MyAsyncTask gandulieu = new MyAsyncTask();
                    gandulieu.execute();
                }
                else
                {
                    lv.setAdapter(null);
                }

                return false;
            }
        });
    }
    protected void readhistory(){
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
                    history.add(w);
                    w = new Dictionary();
                    dem=0;
                }
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }
    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(SearchActivity.this, "Wating", "Read Dictionary");

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
