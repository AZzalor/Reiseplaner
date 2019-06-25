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
        TextView bewertungtxt = (TextView) findViewById(R.id.bewertungtexttview);
        ImageView detailstar1 = (ImageView) findViewById(R.id.detailstar1);
        ImageView detailstar2 = (ImageView) findViewById(R.id.detailstar2);
        ImageView detailstar3 = (ImageView) findViewById(R.id.detailstar3);
        ImageView detailstar4 = (ImageView) findViewById(R.id.detailstar4);
        ImageView detailstar5 = (ImageView) findViewById(R.id.detailstar5);

        /**
         * Entfernt den abgeschlossen Button wenn Reise bereits abgeschlossen ist.
         * verbietet das Bearbeiten von abgeschlossenen Reise (außer Beschreibung)
         */
        if (abgeschlossen == 1){
            abgeschlossenButton.setVisibility(View.GONE);
            EditText landEditText = findViewById(R.id.detaillandEditText);
            EditText stadtEditText = findViewById(R.id.detailstadtEditText);
            EditText objektEditText = findViewById(R.id.detailobjektEditText);
            EditText anreiseEditText = findViewById(R.id.detailanreiseEditText);
            EditText abreiseEditText = findViewById(R.id.detailabreiseEditText);

            disableEditText(landEditText);
            disableEditText(stadtEditText);
            disableEditText(objektEditText);
            disableEditText(anreiseEditText);
            disableEditText(abreiseEditText);

        }

        /**
         * Entfernt die Views der Sterne wenn die Reise noch nicht abgeschlossen ist.
         */
        if (abgeschlossen == 0){
            bewertungtxt.setVisibility(View.GONE);
            detailstar1.setVisibility(View.GONE);
            detailstar2.setVisibility(View.GONE);
            detailstar3.setVisibility(View.GONE);
            detailstar4.setVisibility(View.GONE);
            detailstar5.setVisibility(View.GONE);
        }

        /**
         * Anzeige der Bewertung in Sternen
         */
        switch (bewertung) {

            case 1:
                detailstar2.setVisibility(View.INVISIBLE);
                detailstar3.setVisibility(View.INVISIBLE);
                detailstar4.setVisibility(View.INVISIBLE);
                detailstar5.setVisibility(View.INVISIBLE);
                break;

            case 2:
                detailstar3.setVisibility(View.INVISIBLE);
                detailstar4.setVisibility(View.INVISIBLE);
                detailstar5.setVisibility(View.INVISIBLE);
                break;

            case 3:
                detailstar4.setVisibility(View.INVISIBLE);
                detailstar5.setVisibility(View.INVISIBLE);
                break;

            case 4:
                detailstar5.setVisibility(View.INVISIBLE);
                break;

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


                String ID = Integer.toString(id);

                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, abgeschlossen);

                Intent intent = new Intent(DetailScreen.this, MainActivity.class);
                Intent intent2 = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);

                if(abgeschlossen == 1) {
                    startActivity(intent2);
                } else {
                    startActivity(intent);
                }
            }
        });

        abbrechenButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d("DetailScreen","ABBRECHEN onClick aufegrufen");

               Intent intent = new Intent(DetailScreen.this, MainActivity.class);
               Intent intent2 = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);

               if(abgeschlossen == 1) {
                   startActivity(intent2);
               } else {
                   startActivity(intent);
               }
           }
        });

        myDialog = new Dialog(this);

        abgeschlossenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, 1);

                showPopup();

                //Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                //startActivity(intent);
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

    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
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


    /** Methode um das PopUp Fenster für die Bewertung darzustellen
    In dieser Methode werden auch alle onClickListener für das PopUp festgelegt.
     **/
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

        // PopUp schließen
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        // Bewertung der Reise
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 1;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, 1);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 2;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, 1);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 3;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, 1);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 4;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, 1);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bewertung = 5;
                String ID = Integer.toString(id);
                mDatabaseHelper.updateData(ID, land, stadt, objekt, beschreibung, anreise, abreise, bewertung, 1);

                Intent intent = new Intent(DetailScreen.this, ReisezieleAbgeschlossen.class);
                startActivity(intent);
            }
        });

        // Anzeige des PopUps
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}
