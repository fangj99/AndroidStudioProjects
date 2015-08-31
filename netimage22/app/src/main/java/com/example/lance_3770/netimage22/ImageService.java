package com.example.lance_3770.netimage22;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lance-3770 on 7/4/2015.
 */
public class ImageService {



    //get net image's data
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();//得到基于http协议的链接对象
        conn.setConnectTimeout(3000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode()==200){ //  请求成功 回复200， 404 是错误
            InputStream inputStream = conn.getInputStream();
            return StreamTool.read(inputStream);
        }



        return null;
    }

}
