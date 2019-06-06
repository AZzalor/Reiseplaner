package com.example.reiseplaner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    DatabaseHelper mDatabaseHelper;
    private ArrayList<String> land = new ArrayList<>();
    private ArrayList<String> stadt = new ArrayList<>();
    private Button switchActivityButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);

        //boolean b = mDatabaseHelper.addData("Deutschland", "Saarbrücken", "", "");
        fillData();

        ListView listView = (ListView) findViewById(R.id.geplanteReiseListe);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        switchActivityButton = (Button) findViewById(R.id.list1);

        switchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchActivity();
            }
        });
    }

    private void fillData(){
        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext()){
            land.add(data.getString(1));
            stadt.add(data.getString(2));
        }
    }

    public void AddData(String Land, String Stadt, String Objekt, String Beschreibung){
        mDatabaseHelper.addData(Land, Stadt,Objekt, Beschreibung);
    }

    private void switchActivity() {

        Intent intent = new Intent(this, ReisezieleAbgeschlossen.class);
        startActivity(intent);
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return land.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout, null);
            TextView textView_stadt = (TextView)convertView.findViewById(R.id.textView_stadt);
            TextView textView_land = (TextView)convertView.findViewById(R.id.textView_land);

            textView_land.setText(land.get(position));
            textView_stadt.setText(stadt.get(position));

            return convertView;
        }
    }
}