package com.example.reiseplaner;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DetailScreen extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    public int id;
    private String land;
    private String stadt;
    private String objekt;
    private String anreise;
    private String abreise;
    private int bewertung = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        mDatabaseHelper = new DatabaseHelper(this);

        id = getIntent().getIntExtra("ID", 1);

        fillData();

        EditText landEditText = findViewById(R.id.detaillandEditText);
        landEditText.setText(land);
        EditText stadtEditText = findViewById(R.id.detailstadtEditText);
        stadtEditText.setText(stadt);
        EditText objektEditText = findViewById(R.id.detailobjektEditText);
        objektEditText.setText(objekt);




    }

    private void fillData() {
        Cursor data = mDatabaseHelper.getData("Zweibrücken");
        while (data.moveToNext())
            land = data.getString(1);
            stadt = data.getString(2);
            objekt = data.getString(3);
        }

}
