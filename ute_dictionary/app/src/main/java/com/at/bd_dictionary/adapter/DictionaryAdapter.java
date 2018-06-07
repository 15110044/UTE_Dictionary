package com.at.bd_dictionary.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.at.bd_dictionary.R;
import com.at.bd_dictionary.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-Tung on 07-Jun-18.
 */

public class DictionaryAdapter extends ArrayAdapter<Dictionary> {
    Activity context;
    int resource;
    List<Dictionary> objects;

    public DictionaryAdapter(Activity context, int resource, List<Dictionary> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView view_eng = row.findViewById(R.id.view_eng);
        TextView view_bang = row.findViewById(R.id.view_bang);

        return row;
    }

}