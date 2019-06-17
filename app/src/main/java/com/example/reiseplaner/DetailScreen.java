package com.example.reiseplaner;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailScreen extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    public int id;
    private String land;
    private String stadt;
    private String objekt;
    private String beschreibung;
    private String anreise;
    private String abreise;
    private int bewertung;
    private int abgeschlossen;

    private Cursor data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        mDatabaseHelper = new DatabaseHelper(this);

        id = getIntent().getIntExtra("ID", 0);

        data = fillData();

        Button button = findViewById(R.id.detailOkButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText landEditText = findViewById(R.id.detaillandEditText);
                land = landEditText.getText().toString();
                EditText stadtEditText = findViewById(R.id.detailstadtEditText);
                stadt = stadtEditText.getText().toString();
                EditText objektEditText = findViewById(R.id.detailobjektEditText);
                objekt = objektEditText.getText().toString();
                EditText beschreibungEditText = findViewById(R.id.detailbeschreibungEditText);
                beschreibung = beschreibungEditText.getText().toString();
                EditText anreiseEditText = findViewById(R.id.detailanreiseEditText);
                anreise = anreiseEditText.getText().toString();
                EditText abreiseEditText = findViewById(R.id.detailabreiseEditText);
                abreise = abreiseEditText.getText().toString();

                String ID = Integer.toString(id);

                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);
            }
        });



        EditText landEditText = findViewById(R.id.detaillandEditText);
        landEditText.setText(land);
        EditText stadtEditText = findViewById(R.id.detailstadtEditText);
        stadtEditText.setText(stadt);
        EditText objektEditText = findViewById(R.id.detailobjektEditText);
        objektEditText.setText(objekt);
        EditText beschreibungEditText = findViewById(R.id.detailbeschreibungEditText);
        beschreibungEditText.setText(beschreibung);
        EditText anreiseEditText = findViewById(R.id.detailanreiseEditText);
        anreiseEditText.setText(anreise);
        EditText abreiseEditText = findViewById(R.id.detailabreiseEditText);
        abreiseEditText.setText(abreise);





    }

    private Cursor fillData() {
        Cursor data = mDatabaseHelper.getData(id);
        land = data.getString(1);
        stadt = data.getString(2);
        objekt = data.getString(3);
        beschreibung = data.getString(4);
        anreise = data.getString(5);
        abreise = data.getString(6);
        return  data;
    }

}
