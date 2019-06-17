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


        //AddData("test", "test", "test", "test", "test", "test");
        Button button = (Button) findViewById(R.id.createOkButton);

        button.setOnClickListener(new View.OnClickListener(){
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
            }
        });

    }

    public void AddData(String Land, String Stadt, String Objekt, String Beschreibung,String Anreise, String Abreise){
        mDatabaseHelper.addData(Land, Stadt,Objekt, Beschreibung, Anreise, Abreise);
    }

    // Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_createscreen, menu);
        return true;
    }

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
