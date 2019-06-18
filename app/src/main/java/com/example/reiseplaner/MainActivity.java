package com.example.reiseplaner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private ArrayList<Integer> idArrayList = new ArrayList<>();
    private ArrayList<Integer> abgeschlossenArrayList = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);


        fillData();
        for (int i = 0; i < abgeschlossenArrayList.size(); i++){
            Log.d("Main", abgeschlossenArrayList.get(i).toString());
        }

        ListView listView = (ListView) findViewById(R.id.geplanteReiseListe);

        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailScreen.class);
                int currentID = idArrayList.get(position);
                intent.putExtra("ID", currentID);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
        customAdapter.notifyDataSetChanged();


    }

    public void updateData() {
        land.clear();
        stadt.clear();
        idArrayList.clear();

        fillData();
    }

    private void fillData(){
        Cursor data = mDatabaseHelper.getDataOffen();
        while(data.moveToNext()){
            land.add(data.getString(1));
            stadt.add(data.getString(2));
            idArrayList.add(data.getInt(0));
            abgeschlossenArrayList.add(data.getInt(8));
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.abg_reiseziele:
                startActivity(new Intent(this, ReisezieleAbgeschlossen.class));
                return true;

            case R.id.add:
                startActivity(new Intent(this, CreateScreen.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}