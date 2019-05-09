package com.example.reiseplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String Land[];
    String Stadt[];

    ListView simpleList;
    String countryList[] = {"India", "China", "Australia", "Portugle", "America", "NewZealand"};
    Reiseziel reiseziele[];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reiseziele[0] = new Reiseziel("Deutschland","Berlin","","","");
        reiseziele[1] = new Reiseziel("Frankreich","Paris","","","");


        for (int i = 0; i < reiseziele.length; i++){
            Land[i] = reiseziele[i].Land;
            Stadt[i] = reiseziele[i].Stadt;
        }

        simpleList = findViewById(R.id.geplanteReiseListe);
       CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), Land, Stadt);
       simpleList.setAdapter(customAdapter);


    }

}
