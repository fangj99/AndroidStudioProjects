package com.example.lance_3770.htmlviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lance-3770 on 7/4/2015.
 */
public class PageService {

    public static String getHtml(String path) throws Exception {
        URL url= new URL(path);
        HttpURLConnection conn = (HttpURLConnection ) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");

        if(conn.getResponseCode()==200){
            InputStream inputStream = conn.getInputStream();
           byte[] data =  StreamTool.read(inputStream);
            String html = new String(data,"UTF-8" );
            return html;
        }


        return null;
    }
}
