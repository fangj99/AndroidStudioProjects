package com.example.lance_3770.newsmanager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.FormFile;
import utils.SocketHttpRequester;


/**
 * Created by lance-3770 on 7/5/2015.
 */
public class NewsService {
    public static boolean save(String title, String length) throws Exception {
        String path = "http://192.168.1.2:8080/web/ManageServlet";
        Map<String, String> params = new HashMap<String, String>();
        params.put("title", title);
        params.put("timelength", length);

        try{
            return sendPostRequest(path, params, "UTF-8");


        }catch (Exception e){
            e.getStackTrace();
        }

        return false;
     }


    //using HttpClient
    private static boolean sendHttpClientPOSTRequest(String path, Map<String, String> params, String encoding) throws IOException {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();

        if(params != null && !params.isEmpty()){
            for(Map.Entry<String, String> entry : params.entrySet()){
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

        }
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs,encoding);

        HttpPost httpPost = new HttpPost(path);
        httpPost.setEntity(urlEncodedFormEntity);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute((httpPost));
        if(response.getStatusLine().getStatusCode()==200){

            return true;
        }


        return false;
    }





    private static boolean sendPostRequest(String path, Map<String, String> params, String encoding) throws Exception {

        //title=liming&timelenght=90
        StringBuilder data = new StringBuilder();
        if(params != null){
            for (Map.Entry<String, String> entry:params.entrySet() ){

                data.append(entry.getKey()).append("=");
                data.append(URLEncoder.encode(entry.getValue(),encoding)); //encoding make chinese readable step 1, step 2 is to make tomcat in utf-8
                data.append("&");

            }
            data.deleteCharAt(data.length() - 1);// remove last &

        }
        byte[] entity = data.toString().getBytes();


        HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");

        conn.setDoOutput(true); //allow to output data

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(entity.length));

        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(entity);// put in memory, not send to application yet

       if(conn.getResponseCode()==200){  //at this time, request will send to application
            return true;

        }
        return false;
    }

    private static boolean sendGetRequest(String path, Map<String, String> params, String encoding) throws IOException {

        //"http://192.168.1.100:8080/web/ManageService?title=xxx&timelength=90
        StringBuilder url = new StringBuilder(path);
        url.append("?");
        for (Map.Entry<String, String> entry:params.entrySet() ){

            url.append(entry.getKey()).append("=");
            url.append(URLEncoder.encode(entry.getValue(),encoding)); //encoding make chinese readable step 1, step 2 is to make tomcat in utf-8
            url.append("&");

        }
        url.deleteCharAt(url.length() - 1); // remove last &

        HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");

        if(conn.getResponseCode() == 200){
            return true;

        }

        return false;
    }

    public static boolean save(String title, String length, File uploadFile) {
        String path = "http://192.168.1.2:8080/web/ManageServlet";
        Map<String, String> params = new HashMap<String, String>();
        params.put("title", title);
        params.put("timelength", length);
        FormFile formFile = new FormFile(uploadFile, "videofile", "image/pjpeg");

        try{
            return SocketHttpRequester.post(path, params, formFile);


        }catch (Exception e){
            e.getStackTrace();
        }

        return false;    }
}
