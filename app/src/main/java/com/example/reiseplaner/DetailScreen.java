package com.example.reiseplaner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        mDatabaseHelper = new DatabaseHelper(this);

        id = getIntent().getIntExtra("ID", 0);

        fillData();

        Button okbutton = (Button) findViewById(R.id.detailOkButton);
        Button abbrechenButton = (Button) findViewById(R.id.detailAbbrechenButton);
        Button abgeschlossenButton = (Button) findViewById(R.id.abgeschlossenButton);
        Button deleteButton = (Button) findViewById(R.id.loeschenButton);

        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DetailScreen","OK onClick aufegrufen");

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
                EditText bewertungEditText = findViewById(R.id.edittextbewertung);
                bewertungEditText.getText().toString();


                String ID = Integer.toString(id);

                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);

                Intent intent = new Intent(DetailScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        abbrechenButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d("DetailScreen","ABBRECHEN onClick aufegrufen");
               Intent intent = new Intent(DetailScreen.this, MainActivity.class);
               startActivity(intent);
           }
        });

        abgeschlossenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abgeschlossen = 1;
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteReise(id);
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
        EditText bewertungEditText = findViewById(R.id.edittextbewertung);
        bewertungEditText.setText(""+bewertung);



    }

    private void fillData() {
        Cursor data = mDatabaseHelper.getData(id);
        land = data.getString(1);
        stadt = data.getString(2);
        objekt = data.getString(3);
        beschreibung = data.getString(4);
        anreise = data.getString(5);
        abreise = data.getString(6);
        bewertung = data.getInt(7);
        abgeschlossen = data.getInt(8);
    }

}
