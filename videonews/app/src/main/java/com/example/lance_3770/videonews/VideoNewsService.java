package com.example.lance_3770.videonews;

import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance-3770 on 7/5/2015.
 */
public class VideoNewsService  {

    public static List<News> getLastNews() throws Exception{
        String path="htpp://192.168.1.2/ListServlet";
        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inputStream= conn.getInputStream();
            return parseXML(inputStream);
        }
        return null;

    }

    public static List<News> getJSONLastNews() throws Exception{
        String path="htpp://192.168.1.2/ListServlet?format=json";
        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inputStream= conn.getInputStream();
            return parseJSON(inputStream);
        }
        return null;

    }

    private static List<News> parseJSON(InputStream inputStream) throws IOException, JSONException {
        List<News> newses = new ArrayList<News>();

        byte[] data= StreamTool.read(inputStream);
        String json = new String(data);

        JSONArray array = new JSONArray(json);
        for(int i = 0; i< array.length();i++){
            JSONObject jsonObject = array.getJSONObject(i);
            News news = new News(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getInt("timelength"));

            newses.add(news);
        }
        return newses;
    }

    //
    private  static List<News> parseXML(InputStream inputStream) throws XmlPullParserException, IOException {
        List<News> newses = new ArrayList<News>();
        News news = null;
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(inputStream, "UTF-8");
            int event = parser.getEventType();
            while(event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        if("news".equals(parser.getName()) ){
                           int id = new Integer( parser.getAttributeValue(0));
                            news = new News();
                            news.setId(id);
                        }else if("title".equals(parser.getName()) ) {
                            news.setTitle(parser.nextText());
                        }else if("timelenght".equals(parser.getName()) ) {
                            news.setTimelength(new Integer(parser.nextText()));
                        }


                        break;
                    case XmlPullParser.END_TAG:
                        if("news".equals(parser.getName()) ){
                            newses.add(news);
                            news=null;

                        }

                        break;


                }


                event =  parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return newses;
    }


}



