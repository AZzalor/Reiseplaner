package com.example.reiseplaner;

import java.text.DateFormat;
import java.util.Date;

public class Reiseziel {

    // Attribute
    String Land;
    String Stadt;
    String Objekt;
    String Datum;
    String Beschreibung;
    int Bewertung;

    // Getter
    public String getLand() {
        return Land;
    }

    public String getStadt() {
        return Stadt;
    }

    public String getObjekt() {
        return Objekt;
    }

    public String getDatum() {
        return Datum;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public int getBewertung() {
        return Bewertung;
    }


    // Setter
    public void setLand(String land) {
        Land = land;
    }

    public void setStadt(String stadt) {
        Stadt = stadt;
    }

    public void setObjekt(String objekt) {
        Objekt = objekt;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }

    public void setBewertung(int bewertung) {
        Bewertung = bewertung;
    }


    //Konstruktor
    public Reiseziel() {
    }
    public Reiseziel(String Land, String Stadt, String Objekt, String Datum, String Beschreibung){
        this.Land = Land;
        this.Stadt = Stadt;
        this.Objekt = Objekt;
        this.Datum = Datum;
        this.Beschreibung = Beschreibung;
    }

}
