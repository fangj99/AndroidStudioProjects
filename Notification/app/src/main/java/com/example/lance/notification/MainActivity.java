package com.example.lance.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private EditText shorttitleText;
    private EditText titleText;
    private EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shorttitleText = (EditText)this.findViewById(R.id.shorttitle);
        titleText = (EditText)this.findViewById(R.id.title);
        contentText = (EditText)this.findViewById(R.id.content);

    }



    public void send(View v){

        String ticker = shorttitleText.getText().toString();
        String title = titleText.getText().toString();
        String content = contentText.getText().toString();

        int icon = android.R.drawable.stat_notify_chat;
        Notification notification = new Notification(icon, ticker,System.currentTimeMillis());

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9345345345"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,10, intent, 0 );
        notification.setLatestEventInfo(this, title, content,pendingIntent);

        notification.defaults = Notification.DEFAULT_SOUND;


        notification.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(100, notification);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
