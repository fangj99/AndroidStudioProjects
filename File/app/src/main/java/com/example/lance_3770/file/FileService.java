package com.example.lance_3770.file;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lance-3770 on 7/1/2015.
 */
public class FileService {

    private Context context;

    public FileService(Context context) {
        this.context = context;
    }

    public void save(String filename, String content) throws Exception{
        /*using io in java*/

        FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
        write(content, outStream);
    }

    public void saveToSCard(String filename, String content) throws Exception{
        /*using io in java*/
       File file= new File(new File(Environment.getExternalStorageDirectory()),filename);
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(content.getBytes());
        outStream.close();
    }

    private void write(String content, FileOutputStream outStream) throws IOException {
        outStream.write(content.getBytes());
        outStream.close();
    }

    public void saveAppend(String filename, String content) throws Exception{
        /*using io in java*/

        FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_APPEND);
        write(content, outStream);
    }

    public void saveReadable(String filename, String content) throws Exception{
        /*using io in java*/

        FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_WORLD_READABLE);
        write(content, outStream);
    }


    public String read(String filename) throws Exception{

        FileInputStream inStream=context.openFileInput(filename);
        ByteArrayOutputStream outStream =  new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;

        while((len = inStream.read(buffer)) != -1){
            outStream.write(buffer,0,len);

        }
        byte[] data=outStream.toByteArray();
        return new String(data);
    }
}
