package com.example.reiseplaner;

import android.widget.BaseAdapter;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;


    String Land[];
    String Stadt[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] Land, String[] Stadt) {
        this.context = context;
        this.Land = Land;
        this.Stadt = Stadt;

        inflter = (LayoutInflater.from(applicationContext));
    }


    @Override
    public int getCount() {
        return Land.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView Land_view = (TextView) view.findViewById(R.id.textview_land);
        TextView Stadt_view = (TextView) view.findViewById(R.id.textView_stadt);
        Land_view.setText(Land[i]);
        Stadt_view.setText(Stadt[i]);
        return view;
    }

}
