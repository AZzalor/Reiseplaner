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
    String Objekt[];
    String Datum[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] Land, String[] Stadt, String[] Objekt, String[] Datum) {
        this.context = context;
        this.Land = Land;
        this.Stadt = Stadt;
        this.Objekt = Objekt;
        this.Datum = Datum;
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
}