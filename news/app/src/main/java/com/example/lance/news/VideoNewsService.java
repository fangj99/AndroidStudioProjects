package com.example.lance.news;

import android.util.Log;
import android.util.Xml;

import com.example.lance.news.News;

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
 * Created by lance on 7/7/2015.
 */
public class VideoNewsService {

    public static List<News> getJSONLastNews() throws Exception {

        String path = "http://192.168.1.2:8080/web/ListServlet?format=json";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            return parseJSON(inputStream);


        }
        return null;

    }

    private static List<News> parseJSON(InputStream inputStream) throws IOException, JSONException {

        List<News> newses = new ArrayList<News>();
        byte[] data = StreamTool.read(inputStream);
        String json = new String(data);
        JSONArray array = new JSONArray(json);

         for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            News news = new News(jsonObject.getInt("id"), jsonObject.getString("title"),
                    jsonObject.getInt("timelength"));

            newses.add(news);

        }


        return newses;


    }


    private static List<News> parseXML(InputStream inputStream) throws XmlPullParserException, IOException {
        List<News> newses = new ArrayList<News>();
        News news = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, "UTF-8");
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_TAG:
                    if ("news".equals(parser.getName())) {
                        int id = new Integer(parser.getAttributeValue(0));
                        news = new News();
                        news.setId(id);
                    } else if ("title".equals(parser.getName())) {
                        news.setTitle(parser.nextText());
                    } else if ("timelength".equals(parser.getName())) {
                        news.setTimelength(new Integer(parser.nextText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("news".equals(parser.getName())) {
                        newses.add(news);
                        news = null;
                    }
                    break;


            }
            event = parser.next();

        }
        return newses;

    }


}