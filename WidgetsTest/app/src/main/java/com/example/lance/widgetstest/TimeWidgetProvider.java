package com.example.lance.widgetstest;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lance on 7/18/2015.
 */
public class TimeWidgetProvider extends AppWidgetProvider {

    @Override
    public void onEnabled(Context context) {  //will be calle when the first widget added, will not be called if same widget added later
        context.startService(new Intent(context, TimeService.class));
    }

    @Override
    public void onDisabled(Context context) {  //when all widgets deleted, this method is called

        context.stopService(new Intent(context, TimeService.class));
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {// when user delete widget, will incur this method

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        /*
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());

        RemoteViews remoteView = new RemoteViews(context.getPackageName(),
                R.layout.time_appwidget);

        remoteView.setTextViewText(R.id.textView, time);



        appWidgetManager.updateAppWidget(appWidgetIds[0],remoteView );
*/

    }
}
