package com.example.reiseplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    String countryList[] = {"India", "China", "Australia", "Portugle", "America", "NewZealand"};
    Reiseziel reiseziele[] = {
            new Reiseziel("Deutschland","Berlin","","",""),
           new Reiseziel("Frankreich","Paris","","","")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleList = findViewById(R.id.geplanteReiseListe);
       CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),);
    }
}
