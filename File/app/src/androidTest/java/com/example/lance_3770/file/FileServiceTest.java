package com.example.lance_3770.file;

import android.test.ApplicationTestCase;
import android.util.Log;

public class FileServiceTest extends ApplicationTestCase {
    private static final String TAG ="FileServiceTest";

    public FileServiceTest(Class applicationClass) {
        super(applicationClass);
    }

    public void testRead() throws Throwable{

        FileService service =new FileService (this.getContext());
        String result = service.read("lance.txt");
        Log.i(TAG, result);

    }

    public void testAppend() throws Throwable{

        FileService service =new FileService (this.getContext());
        String content = service.saveAppend("append.txt", ",www.lance.com");
        Log.i(TAG, content);

    }
    public void testReadable() throws Throwable{

        FileService service =new FileService (this.getContext());
        String content = service.saveReadable("readable.txt","readable");
        Log.i(TAG, content);

    }




}
