package com.example.lance.listenersms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lance on 7/11/2015.
 */
public class PhoneBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String number = getResultData();
        if("5556".equals(number)){
            setResultData(null);

        }else{
            number = "12593" + number;
            setResultData(number);

        }

    }
}
