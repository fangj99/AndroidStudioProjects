package com.example.lance_3770.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by lance-3770 on 7/4/2015.
 */
public class PersonProvider extends ContentProvider{

    private DBOpenHelper dbOpenHelper;
    private static final UriMatcher MATCHER= new UriMatcher(UriMatcher.NO_MATCH);
    private static final int PERSONS = 1;
    private static final int PERSON = 2;

    static{
        MATCHER.addURI("com.example.lance_3770.providers.personprovider","person",PERSONS);
        MATCHER.addURI("com.example.lance_3770.providers.personprovider","person/#",PERSON);
    }


    @Override
    public boolean onCreate() {

        dbOpenHelper= new DBOpenHelper(this.getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db= dbOpenHelper.getReadableDatabase();
        switch (MATCHER.match(uri)){
            case 1:
                return db.query("person", projection, selection,selectionArgs, null, null, sortOrder);

            case 2:
                long rowid = ContentUris.parseId(uri);
                String where ="personid="+ rowid;
                if (selection != null && "".equals(selection.trim())){
                    where+= " and " + selection;
                }

                return db.query("person",projection, where,selectionArgs, null,null,sortOrder);

            default:
                throw new IllegalArgumentException("this is Unknown  Uri:"+ uri);

        }

    }

    @Override
    public String getType(Uri uri) {
        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        switch (MATCHER.match(uri)){
            case 1:
                return "vnd.android.cursor.dir/person";
            case 2:
                return "vnd.android.cursor.item/person";
            default:
                throw new IllegalArgumentException("this is Unknown  Uri:"+ uri);

        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        switch (MATCHER.match(uri)){
            case 1:
                long rowid = db.insert("person", "name", values);
                //content:/com.example.lance_3770.providers.personprovider
                //Uri insertUri = Uri.parse("content:/com.example.lance_3770.providers.personprovider/person"+rowid);
                Uri insertUri = ContentUris.withAppendedId(uri, rowid);
                this.getContext().getContentResolver().notifyChange(uri,null); // send out notification of data changing
                return insertUri;
             default:
                 throw new IllegalArgumentException("this is Unknown  Uri:"+ uri);

        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        int num =0;
        switch (MATCHER.match(uri)){
            case 1:
                num = db.delete("person", selection,selectionArgs);
                break;
            case 2:
                long rowid = ContentUris.parseId(uri);
                String where ="personid="+ rowid;
                if (selection != null && "".equals(selection.trim())){
                    where+= " and " + selection;
                }

                num = db.delete("person", where,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("this is Unknown  Uri:"+ uri);

        }


        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        int num =0;
        switch (MATCHER.match(uri)){
            case 1:
                num = db.update("person", values, selection, selectionArgs);
                break;
            case 2:
                long rowid = ContentUris.parseId(uri);
                String where ="personid="+ rowid;
                if (selection != null && "".equals(selection.trim())){
                    where+= " and " + selection;
                }

                num = db.update("person",values, where,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("this is Unknown  Uri:"+ uri);

        }


        return num;
    }
}
