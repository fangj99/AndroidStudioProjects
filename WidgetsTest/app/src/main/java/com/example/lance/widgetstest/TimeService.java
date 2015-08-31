package com.example.lance.widgetstest;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lance on 7/18/2015.
 */
public class TimeService extends Service {

    private Timer timer;

    @Override
    public void onDestroy() {
        timer.cancel();
        timer = null;
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new TimeUpdateTask(),0,1000);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final class TimeUpdateTask extends TimerTask{


        @Override
        public void run() {

            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = simpleDateFormat.format(new Date());

            RemoteViews remoteView = new RemoteViews(getPackageName(),
                    R.layout.time_appwidget);

            remoteView.setTextViewText(R.id.textView, time);


            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),100,
                    new Intent(Intent.ACTION_CALL, Uri.parse("tel:2342312335345")), 0);

            remoteView.setOnClickPendingIntent(R.id.textView,pendingIntent );



            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
            appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(),
                    TimeWidgetProvider.class),remoteView );


        }
    }
}
