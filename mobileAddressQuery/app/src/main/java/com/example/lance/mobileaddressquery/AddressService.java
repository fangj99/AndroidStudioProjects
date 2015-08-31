package com.example.lance.mobileaddressquery;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by lance on 7/9/2015.
 */
public class AddressService {
    public static String getAddress(String mobile) throws IOException, XmlPullParserException {

        String soap = readSoap();
        soap = soap.replaceAll("\\$mobile",mobile);
        byte[] entity = soap.getBytes();

        String path ="http://www.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        HttpURLConnection conn = (HttpURLConnection ) new URL(path).openConnection();
        conn.setConnectTimeout(5000);
        try {
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
        conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
        conn.getOutputStream().write(entity);
        if (conn.getResponseCode()==200){
            return parseSOAP(conn.getInputStream());



        }else{
            return null;


        }


    }

    private static String parseSOAP(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inputStream,"UTF-8");
        int event = pullParser.getEventType();
        while(event!=XmlPullParser.END_DOCUMENT){
            switch(event){
                case XmlPullParser.START_TAG:
                    if("getMobileCodeInfoResult".equals(pullParser.getName())){
                        return pullParser.nextText();
                    }
                    break;



            }
            event = pullParser.next();

        }
        return null;
    }

    private static String readSoap() throws IOException {

        InputStream inputStream = AddressService.class.getResourceAsStream("/assets/soap12.xml");
        byte[] data = StreamTool.read(inputStream);

        return new String(data);

    }
}
