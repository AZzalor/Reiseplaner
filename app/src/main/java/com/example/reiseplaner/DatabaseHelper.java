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
    public static final String COL5 = "BEWERTUNG";



    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LAND TEXT, " +
                "STADT TEXT, " +
                "OBJEKT TEXT, " +
                "BESCHREIBUNG TEXT, " +
                "BEWERTUNG INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String mLand, String mStadt, String mObjekt, String mBeschreibung) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, mLand);
        contentValues.put(COL2, mStadt);
        contentValues.put(COL3, mObjekt);
        contentValues.put(COL4, mBeschreibung);

        Log.d(TAG, "addData: Adding " + mLand + " + " + mStadt + " + " + mObjekt + " + " + mBeschreibung + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        // wenn Daten falsch eingegeben wurder wird -1 zurückgegeben
        if (result == -1) {
            return false;
        } else {
            return true;
        }
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
}
