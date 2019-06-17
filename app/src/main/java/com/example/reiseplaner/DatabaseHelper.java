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

    public boolean addData(String mLand, String mStadt, String mObjekt, String mBeschreibung, String mAnreise, String mAbreise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, mLand);
        contentValues.put(COL2, mStadt);
        contentValues.put(COL3, mObjekt);
        contentValues.put(COL4, mBeschreibung);
        contentValues.put(COL5, mAnreise);
        contentValues.put(COL6, mAbreise);

        Log.d(TAG, "addData: Adding " + mLand + " + " + mStadt + " + " + mObjekt + " + " + mBeschreibung + " + " + mAnreise + " + " + mAbreise + " to " + TABLE_NAME);

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
     * @param mLand
     * @param mStadt
     * @param mObjekt
     * @param mBeschreibung
     * @param mAnreise
     * @param mAbreise
     * @param mBewertung
     * @param mAbgeschlossen
     * @return
     */
    public boolean updateData(String id, String mLand, String mStadt, String mObjekt, String mBeschreibung, String mAnreise, String mAbreise, int mBewertung, int mAbgeschlossen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, mLand);
        contentValues.put(COL2, mStadt);
        contentValues.put(COL3, mObjekt);
        contentValues.put(COL4, mBeschreibung);
        contentValues.put(COL5, mAnreise);
        contentValues.put(COL6, mAbreise);
        contentValues.put(COL7, mBewertung);
        contentValues.put(COL8, mAbgeschlossen);
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
