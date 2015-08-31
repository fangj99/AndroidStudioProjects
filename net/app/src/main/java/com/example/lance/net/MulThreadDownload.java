package com.example.lance.net;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lance on 7/10/2015.
 */
public class MulThreadDownload {


    private String fileName;

    public static void main(String[] args) throws IOException {
        String path= "http://192.168.1.2:8080/web/npp.6.7.9.2.Installer.exe";
        new MulThreadDownload().download(path, 3);

    }



    private void download(String path, int threadsize) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() ==200){
            int length = conn.getContentLength();
            File file = new File(getFileName(path));
            RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
            accessFile.setLength(length);
            accessFile.close();

            int block = length/threadsize==0 ?  length/threadsize:  length/threadsize+1;

            for(int threadid = 0; threadid < threadsize; threadid++){
                new DownloadThread(threadid, block,url,file).start();
            }

        }else{
            System.out.println("Download fail");
        }




    }



    private class DownloadThread extends  Thread{

        private int threadid;
        private int block;
        private URL url;
        private File file;



        public void run() {
            int start = threadid*block;
            int end = (threadid+1) * block - 1;
            RandomAccessFile accessFile = null;
            try {
                accessFile = new RandomAccessFile(file, "rwd");
                accessFile.seek(start);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range", "bytes=" + start + "-" + end);
                if(conn.getResponseCode() ==206){
                    InputStream inputStream = conn.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0 ;
                    while((len = inputStream.read(buffer))!=-1){
                        accessFile.write(buffer, 0, len);

                    }
                    accessFile.close();
                }

                System.out.println("the" + (threadid+1) + "Thread download finished");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        public DownloadThread(int threadid, int block, URL url, File file) {
            this.threadid = threadid;
            this.block = block;
            this.url = url;
            this.file=file;

        }



    }



    public String getFileName(String path) {

        return path.substring(path.lastIndexOf("/")+1);

    }
}
