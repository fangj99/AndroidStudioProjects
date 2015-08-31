package com.example.lance_3770.contacts;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lance-3770 on 7/4/2015.
 */
public class ContactsTest extends AndroidTestCase {

    private  static final String TAG = "ContactsTest";
    //get all contacts

    public void testContacts() throws Exception {
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(uri, new String[]{"_id"}, null, null, null);

        while (cursor.moveToNext()) {
            int contactid = cursor.getInt(0);

            StringBuilder sb = new StringBuilder("contactid=");
            sb.append(contactid);

            uri = Uri.parse("content://com.android.contacts/contacts/" + contactid + "/data");
            Cursor dataCursor = contentResolver.query(uri, new String[]{"mimetype", "data1", "data2"}, null, null, null);
            while (dataCursor.moveToNext()) {
                String data = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                String type = dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
                if ("vnd.android.cursor.item/name".equals(type)) {  //data of name

                    sb.append(", name =" + data);

                } else if ("vnd.android.cursor.item/email_v2".equals(type)) {  //data of email
                    sb.append(", email =" + data);

                } else if ("vnd.android.cursor.item/phone_v2".equals(type)) {  // data of phone
                    sb.append(", phone =" + data);

                }
            }

            Log.i(TAG, sb.toString());


        }
    }


    public void testContactNameByNumber() throws Exception{
        String number = "6462294862";
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(uri, new String[]{"display_name"}, null, null, null);
        if(cursor.moveToFirst()){
            String name = cursor.getString(0);
            Log.i(TAG, name);

        }
        cursor.close();


    }


    public void testAddContact() throws Exception{


        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver contentResolver = getContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        long  contactid = ContentUris.parseId(contentResolver.insert(uri, contentValues));

        uri = Uri.parse("content://com.android.contacts/data");
        contentValues.put("raw_contact_id", contactid);
        contentValues.put("mimetype", "vnd.android.cursor.item/name");
        contentValues.put("data2", "方剑");

        contentResolver.insert(uri, contentValues);

        contentValues.clear();
        contentValues.put("raw_contact_id", contactid);
        contentValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
        contentValues.put("data1", "9176918036");
        contentValues.put("data2", "2");

        contentResolver.insert(uri, contentValues);

        contentValues.clear();
        contentValues.put("raw_contact_id", contactid);
        contentValues.put("mimetype", "vnd.android.cursor.item/email_v2");
        contentValues.put("data1", "lancefang@gmail.com");
        contentValues.put("data2", "2");

        contentResolver.insert(uri, contentValues);






    }

//在同一事务中完成联系人的所有数据添加
    public void testContact2() throws Exception{  // this method is better than 1 for transaction will make operation and record in full

        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver contentResolver = getContext().getContentResolver();

        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();

        ContentProviderOperation op1 =  ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)
                .build(); // can bind to google account

        operations.add(op1);

        uri = Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation op2 =  ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id", 0)  // use number "0" operation's return id as value "0" means op1 return value
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", "李洪伟")
                .build(); // can bind to google account

        operations.add(op2);

        uri = Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation op3 =  ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id", 0)  // use number "0" operation's return id as value "0" means op1 return value
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", "6462690798")
                .withValue("data2", "2")
                .build(); // can bind to google account

        operations.add(op3);


        uri = Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation op4 =  ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id", 0)  // use number "0" operation's return id as value "0" means op1 return value
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data1", "hongfox99@gmail.com")
                .withValue("data2", "2")
                .build(); // can bind to google account

        operations.add(op4);

        contentResolver.applyBatch("com.android.contacts", operations);


    }













}
