package com.example.reiseplaner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "reiseziele.db";
    public static final String TABLE_NAME = "reiseziele_table";
    public static final String COL0 = "ID";
    public static final String COL1 = "LAND";
    public static final String COL2 = "STADT";
    public static final String COL3 = "OBJEKT";
    public static final String COL4 = "BESCHREIBUNG";
    public static final String COL5 = "ANREISE";
    public static final String COL6 = "ABREISE";
    public static final String COL7 = "BEWERTUNG";
    public static final String COL8 = "ABGESCHLOSSEN";



    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, " +
                "LAND TEXT NOT NULL, " +
                "STADT TEXT, " +
                "OBJEKT TEXT, " +
                "BESCHREIBUNG TEXT, " +
                "ANREISE TEXT," +
                "ABREISE TEXT," +
                "BEWERTUNG INTEGER," +
                "ABGESCHLOSSEN INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Daten zur Datenbank hinzufügen
     * @param land
     * @param stadt
     * @param objekt
     * @param beschreibung
     * @param anreise
     * @param abreise
     * @return
     */
    public boolean addData(String land, String stadt, String objekt, String beschreibung, String anreise, String abreise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, land);
        contentValues.put(COL2, stadt);
        contentValues.put(COL3, objekt);
        contentValues.put(COL4, beschreibung);
        contentValues.put(COL5, anreise);
        contentValues.put(COL6, abreise);

        Log.d(TAG, "addData: Adding " + land + " + " + stadt + " + " + objekt + " + " + beschreibung + " + " + anreise + " + " + abreise + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        // wenn Daten falsch eingegeben wurder wird -1 zurückgegeben
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Update des Reiseziels
     * @param newReise
     * @param id
     * @param oldReise
     */
    public void updateReise(String newReise, int id, String oldReise){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newReise + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + oldReise + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newReise);
        db.execSQL(query);
    }

    /**
     * Update der Datenbank über die ID
     * @param id
     * @param land
     * @param stadt
     * @param objekt
     * @param beschreibung
     * @param anreise
     * @param abreise
     * @param bewertung
     * @param abgeschlossen
     * @return
     */
    public boolean updateData(String id, String land, String stadt, String objekt, String beschreibung, String anreise, String abreise, int bewertung, int abgeschlossen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, land);
        contentValues.put(COL2, stadt);
        contentValues.put(COL3, objekt);
        contentValues.put(COL4, beschreibung);
        contentValues.put(COL5, anreise);
        contentValues.put(COL6, abreise);
        contentValues.put(COL7, bewertung);
        contentValues.put(COL8, abgeschlossen);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }


    /**
     * löscht einen Eintrag aus der Datenbank
     * @param id
     * @param reise
     */
    public void deleteReise(int id, String reise) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + reise + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + reise + " from database.");
        db.execSQL(query);

    }

    /**
     * Gibt alle Daten der Datenbank aus
     * @return
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getData(String stadt) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_NAME + " where STADT = '" + stadt + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


}
