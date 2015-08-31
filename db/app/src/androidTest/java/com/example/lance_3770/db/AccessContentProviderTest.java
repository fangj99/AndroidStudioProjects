package com.example.lance_3770.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by lance-3770 on 7/4/2015.
 */
public class AccessContentProviderTest extends AndroidTestCase {

    private static final String TAG = "AccessContentProvider";

    public void testInsert() throws Exception{
        Uri uri = Uri.parse("content://com.example.lance_3770.providers.personprovider/person");
        ContentResolver contentResolver = this.getContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "laoli2");
        contentValues.put("phone", "1860102");
        contentValues.put("amount", "5000000002");

        contentResolver.insert(uri,contentValues);

    }

    public void testDelete() throws Exception{
        Uri uri = Uri.parse("content://com.example.lance_3770.providers.personprovider/person/5");
        ContentResolver contentResolver = this.getContext().getContentResolver();
        contentResolver.delete(uri, null, null);

    }

    public void testUpdate() throws Exception{
        Uri uri = Uri.parse("content://com.example.lance_3770.providers.personprovider/person/6");
        ContentResolver contentResolver = this.getContext().getContentResolver();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "zhangxiaoxiao");
        contentValues.put("phone", "9176918033");
        contentValues.put("amount", "5000002");


        contentResolver.update(uri, contentValues, null, null);

    }

    public void testQuery() throws Exception{
        Uri uri = Uri.parse("content://com.example.lance_3770.providers.personprovider/person");
        ContentResolver contentResolver = this.getContext().getContentResolver();

        Cursor cursor = contentResolver.query(uri, null, null, null, "personid asc");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            Log.i(TAG,name);
        }
        cursor.close();
     }

}
