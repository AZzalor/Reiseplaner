package com.example.reiseplaner;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Dialog myDialog;


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
        TextView bewertungtxt = (TextView) findViewById(R.id.bewertungtxtview);
        EditText bewertungedttxt = (EditText) findViewById(R.id.edittextbewertung);

        if (abgeschlossen == 1){
            abgeschlossenButton.setVisibility(View.GONE);
        }

        if (abgeschlossen == 0){
            bewertungtxt.setVisibility(View.GONE);
            bewertungedttxt.setVisibility(View.GONE);
        }


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

        myDialog = new Dialog(this);

        abgeschlossenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, 1);

                // showPopup();

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteReise(id);
                Intent intent = new Intent(DetailScreen.this, MainActivity.class);
                Intent intent2 = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);

                if (abgeschlossen == 1) {
                    startActivity(intent2);
                } else {
                    startActivity(intent);
                }
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


    public void showPopup() {
        TextView txtclose;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;

        myDialog.setContentView(R.layout.bewertung_popup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtviewclose);
        star1 = (ImageView) myDialog.findViewById(R.id.star1);
        star2 = (ImageView) myDialog.findViewById(R.id.star2);
        star3 = (ImageView) myDialog.findViewById(R.id.star3);
        star4 = (ImageView) myDialog.findViewById(R.id.star4);
        star5 = (ImageView) myDialog.findViewById(R.id.star5);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 1;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 2;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 3;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 4;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 5;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });


        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}
