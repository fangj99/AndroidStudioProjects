package com.example.lance.mobileaddressquery;

import android.test.AndroidTestCase;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lance on 7/9/2015.
 */
public class XMLTest extends AndroidTestCase {

    public void testSendXML() throws Exception{

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("assets/person.xml");
        byte[] data = StreamTool.read(inputStream);
        String path = "http://192.168.1.2:8080/web/XmlServlet";
        HttpURLConnection conn =  (HttpURLConnection) new URL(path).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.getOutputStream().write(data);
        if (conn.getResponseCode()==200){
            System.out.println("sent suceess");

        }else{
            System.out.println("sent failure");

        }

    }
}
