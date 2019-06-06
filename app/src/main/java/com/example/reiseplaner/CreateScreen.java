package com.example.reiseplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateScreen extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    String stadt;
    String land;
    String objekt;
    String beschreibung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen);

        Button button = (Button) findViewById(R.id.createOkButton);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText landText = findViewById(R.id.LandInput);
                land = landText.getText().toString();
                EditText stadtText = findViewById(R.id.StadtInput);
                stadt = stadtText.getText().toString();
                EditText objektText = findViewById(R.id.ObjektInput);
                objekt = objektText.getText().toString();
                EditText beschreibungText = findViewById(R.id.BeschreibungInput);
                beschreibung = beschreibungText.getText().toString();

                createNewReiseziel(land, stadt, objekt, beschreibung);
            }
        });

    }

    private void createNewReiseziel (String Land, String Stadt, String Objekt, String Beschreibung){
        mDatabaseHelper.addData(Land, Stadt,Objekt, Beschreibung);
    }
}
