package com.example.lance_3770.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance-3770 on 7/3/2015.
 */
public class PersonService {

    private DBOpenHelper dbOpenHelper;

    public PersonService(Context context) {
        this.dbOpenHelper = new DBOpenHelper(context);
    }


    public void payment(){

        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("update person set amount = amount-10 where personid = 2");
            db.execSQL("update person set amount = amount+10 where personid = 4");
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }



    }





    public void save(Person person) {
        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        db.execSQL("INSERT INTO person(name, phone, amount) values(?,?,?)", new Object[]{person.getName(), person.getPhone(), person.getAmount()});

    }

    public void delete(Integer id) {

        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        db.execSQL("DELETE from person where personid=?",new Object[]{id});

    }

    public void update(Person person) {

        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        db.execSQL("UPDATE person set name=?, phone=? , amount = ? where personid=?",new Object[]{person.getName(),person.getPhone(),person.getAmount(), person.getId()});

    }

    public Person find(Integer id) {
        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * from person where personid=?", new String[]{id.toString()});
        if(cursor.moveToFirst()){
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            Integer amount = cursor.getInt(cursor.getColumnIndex("amount"));

            return new Person(personid, name, phone, amount);
        }

        cursor.close();
        return null;

    }

    public List<Person> getScrollData(int offset, int maxResult) {
        List<Person> persons = new ArrayList<Person>();

        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * from  person order by personid asc limit ?,? ", new String[]{String.valueOf(offset),String.valueOf(maxResult)});

        while(cursor.moveToNext()){
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            Integer amount = cursor.getInt(cursor.getColumnIndex("amount"));
            persons.add(new Person(personid, name, phone, amount));

        }

        cursor.close();
        return persons;

    }

    public Cursor getCursorScrollData(int offset, int maxResult) {
        List<Person> persons = new ArrayList<Person>();

        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT personid as _id, name, phone, amount from  person order by personid asc limit ?,? ", new String[]{String.valueOf(offset),String.valueOf(maxResult)});

        return cursor;

    }


    public long getCount() {

        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT COUNT (*)  from person", null);
        cursor.moveToFirst();
        long result =cursor.getLong(0);
        cursor.close();

        return result;

    }

}
