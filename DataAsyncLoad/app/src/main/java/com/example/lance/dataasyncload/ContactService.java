package com.example.lance.dataasyncload;

import android.net.Uri;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 7/19/2015.
 */
public class ContactService {
    public static List<Contact> getContacts() throws Exception {
        String path = "http://192.168.1.2:8080/web/list.xml";

        HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();

        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");


        if(connection.getResponseCode() ==200){
            return parseXml(connection.getInputStream());
        }


        return null;
    }

    private static List<Contact> parseXml(InputStream inputStream) throws Exception {

        List<Contact> contacts = new ArrayList<Contact>();

        Contact contact = null;
        XmlPullParser pullParser= Xml.newPullParser();
        pullParser.setInput(inputStream, "UTF-8");
        int event = pullParser.getEventType();

        while(event!=XmlPullParser.END_DOCUMENT){
            switch (event){
                case XmlPullParser.START_TAG:
                    if("contact".equals(pullParser.getName())){
                        contact = new Contact();
                        contact.id = new Integer(pullParser.getAttributeValue(0));
                    }else if ("name".equals(pullParser.getName())){

                        contact.name = pullParser.nextText();

                    }else if("image".equals(pullParser.getName())){
                        contact.image = pullParser.getAttributeValue(0);

                    }

                    break;
                case XmlPullParser.END_TAG:
                    if("contact".equals(pullParser.getName())){
                        contacts.add(contact);
                        contact=null;

                    }
                    break;



            }


            event =  pullParser.next();
        }


        return contacts;
    }


//if image in cache, then return the image or will load from network and cache it
    public static Uri getImage(String path, File cache) throws Exception {  // path -> md5->32 string.jpg
        File localFile = new File(cache, MD5.getMD5(path)+ path.substring(path.lastIndexOf(".")));

        if(localFile.exists()){
            return Uri.fromFile(localFile);

        }else{
            HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();

            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if(connection.getResponseCode() ==200){
                FileOutputStream fileOutputStream = new FileOutputStream(localFile);
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int len =0;
                while ((len =inputStream.read(buffer))!= -1){
                    fileOutputStream.write(buffer, 0 , len);


                }
                fileOutputStream.close();
                inputStream.close();
                return Uri.fromFile(localFile);
            }

        }

        return null;

    }







}
