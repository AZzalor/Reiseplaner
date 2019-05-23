package com.example.reiseplaner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "reiseziele.db";
    public static final String TABLE_NAME = "reiseziele_table";
    public static final String COL_ID = "ID";
    public static final String COL_LAND = "LAND";
    public static final String COL_STADT = "STADT";
    public static final String COL_OBJEKT = "OBJEKT";
    public static final String COL_BESCHREIBUNG = "BESCHREIBUNG";
    public static final String COL_BEWERTUNG = "BEWERTUNG";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, LAND TEXT, STADT TEXT, OBJEKT TEXT, BESCHREIBUNG TEXT, BEWERTUNG INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
