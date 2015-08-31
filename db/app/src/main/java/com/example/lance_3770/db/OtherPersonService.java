package com.example.lance_3770.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance-3770 on 7/3/2015.
 */
public class OtherPersonService {

    private DBOpenHelper dbOpenHelper;

    public OtherPersonService(Context context) {
        this.dbOpenHelper = new DBOpenHelper(context);
    }

    public void save(Person person) {
        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",person.getName());
        values.put("phone", person.getPhone());

        db.insert("person", null, values);


    }

    public void delete(Integer id) {

        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();

        db.delete("person", "personid=?", new String[]{id.toString()});

    }

    public void update(Person person) {

        SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",person.getName());
        values.put("phone", person.getPhone());

        db.update("person", values, "personid=?", new String[]{person.getId().toString()});


    }

    public Person find(Integer id) {
        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();

        Cursor cursor = db.query("person", null, "personid=?", new String[]{id.toString()}, null, null, null);



        if(cursor.moveToFirst()){
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));

            return new Person(personid, name, phone);
        }

        cursor.close();
        return null;

    }

    public List<Person> getScrollData(int offset, int maxResult) {
        List<Person> persons = new ArrayList<Person>();

        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();

        Cursor cursor=db.query("person", null,null,null,null,null,"personid asc", offset + "," + maxResult);


        while(cursor.moveToNext()){
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            persons.add(new Person(personid, name, phone));

        }

        cursor.close();
        return persons;

    }


    public long getCount() {

        SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
        Cursor cursor= db.query("person", new String[]{"count(*)"},null,null,null,null,null);
        cursor.moveToFirst();
        long result =cursor.getLong(0);
        cursor.close();

        return result;

    }

}
