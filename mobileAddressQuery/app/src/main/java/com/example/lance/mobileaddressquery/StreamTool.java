package com.example.lance.mobileaddressquery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lance-3770 on 7/4/2015.
 */
public class StreamTool {
    public static byte[] read(InputStream inputStream) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len =0;
        try {
            while ((len = inputStream.read(buffer))!=-1){
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
         }
        inputStream.close();
        return outputStream.toByteArray();



    }


}
