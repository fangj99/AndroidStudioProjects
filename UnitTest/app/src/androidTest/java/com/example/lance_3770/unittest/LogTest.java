package com.example.lance_3770.unittest;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by lance-3770 on 7/1/2015.
 */
public class LogTest extends AndroidTestCase {
    private static final String TAG="LogTest";
    public void testOutlog() throws Throwable{
     Log.i(TAG,"www.lance.com");
    }

    public void testOutLog2() throws Throwable{
        System.out.println("www.lance.com");

    }
    public void testOutLog3() throws Throwable{
        System.err.println("www.lance.com err");

    }

}
