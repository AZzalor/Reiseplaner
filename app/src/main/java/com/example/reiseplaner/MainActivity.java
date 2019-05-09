package com.example.reiseplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public static final List<Reiseziel> REISEZIEL_LIST;

    static {
        REISEZIEL_LIST=new ArrayList<>();
        REISEZIEL_LIST.add(new Reiseziel("Deutschland","Zweibrücken","HS-KL","09.05.2019","Hochschule"));
        }
}