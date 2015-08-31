package com.example.lance.dataasyncload;

import android.app.Activity;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {

    private ListView listView;
    private ImageView imageView;
    private TextView textView;
    File cache;
    Handler handler = new Handler(){

        public void handleMessage(Message msg){

            listView.setAdapter(new ContactAdapter(MainActivity.this, (List<Contact>)msg.obj,
                    R.layout.listview_item, cache));

        }


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.listView);


        cache = new File(Environment.getExternalStorageDirectory(), "cache");
        if(!cache.exists()) cache.mkdirs();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contact> data = null;
                try {
                    data = ContactService.getContacts();
                    handler.sendMessage(handler.obtainMessage(22,data));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    @Override
    protected void onDestroy() {
        for(File file:cache.listFiles()){
            file.delete();
        }
        cache.delete();

        super.onDestroy();
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
