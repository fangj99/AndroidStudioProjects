package com.example.lance_3770.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lance-3770 on 7/3/2015.
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    public DBOpenHelper(Context context) {
        super(context, "lance.db", null,4);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE person(personid integer primary key autoincrement, name varchar(20)), phone VARCHAR(12) NULL");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("ALTER TABLE person ADD amount integer");

    }
}
