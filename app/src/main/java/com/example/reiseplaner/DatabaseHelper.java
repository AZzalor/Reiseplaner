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
                "ABGESCHLOSSEN INTEGER DEFAULT 0)";
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
        contentValues.put(COL8, 0);

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

        Log.d(TAG, "Updated Data with ID = " + id);

        return true;
    }


    /**
     * löscht einen Eintrag aus der Datenbank
     * @param id
     */
    public void deleteReise(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = COL0 + "=?";
        String[] whereArgs = new String[]{Integer.toString(id)};

        db.delete(TABLE_NAME, where, whereArgs);

        Log.d(TAG, "Deleted Data with ID = " + id);

    }

    /**
     * Gibt alle Daten der Datenbank aus
     * @return
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        return data;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_NAME + " where ID = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        return data;
    }

    public Cursor getDataAbgeschlossen() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_NAME + " where ABGESCHLOSSEN = '1'";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        return data;
    }

    public Cursor getDataOffen() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_NAME + " where ABGESCHLOSSEN = '0'";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        return data;
    }


}