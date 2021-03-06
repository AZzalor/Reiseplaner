package com.example.reiseplaner;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateScreen extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    String stadt;
    String land;
    String objekt;
    String beschreibung;
    String anreise;
    String abreise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen);
        mDatabaseHelper = new DatabaseHelper(this);


        Button okbutton = (Button) findViewById(R.id.createOkButton);
        Button abbrechenButton = (Button) findViewById(R.id.createAbbrechenButton);

        okbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("CreateScreen","onClick aufegrufen");

                EditText landText = findViewById(R.id.createlandEditText);
                land = landText.getText().toString();
                EditText stadtText = findViewById(R.id.createstadtEditText);
                stadt = stadtText.getText().toString();
                EditText objektText = findViewById(R.id.createobjektEditText);
                objekt = objektText.getText().toString();
                EditText beschreibungText = findViewById(R.id.createbeschreibungEditText);
                beschreibung = beschreibungText.getText().toString();
                EditText anreiseText = findViewById(R.id.createanreiseEditText);
                anreise = anreiseText.getText().toString();
                EditText abreiseText = findViewById(R.id.createabreiseEditText);
                abreise = abreiseText.getText().toString();

                AddData(land, stadt, objekt, beschreibung, anreise, abreise);

                Intent intent = new Intent(CreateScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        abbrechenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("CreateScreen","ABBRECHEN onClick aufegrufen");
               Intent intent = new Intent(CreateScreen.this, MainActivity.class);
               startActivity(intent);
           }
        });

    }
    //legt ein neues Reiseziel mit den eingegebenen Daten an und speichert es in der Datenbank
    public void AddData(String Land, String Stadt, String Objekt, String Beschreibung,String Anreise, String Abreise){
        mDatabaseHelper.addData(Land, Stadt,Objekt, Beschreibung, Anreise, Abreise);
    }

    /**
     * legt das Menü in der Actionbar an
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_createscreen, menu);
        return true;
    }

    /**
     * legt fest welche Symbol des Menüs in der Actionbar sichtbar sind
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.reiseziele:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            case R.id.abg_reiseziele:
                startActivity(new Intent(this, ReisezieleAbgeschlossen.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
