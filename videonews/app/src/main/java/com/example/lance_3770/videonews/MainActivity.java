package com.example.lance_3770.videonews;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());

        ListView listView = (ListView) this.findViewById(R.id.listview);
        try {
            List<News> videos = VideoNewsService.getJSONLastNews();
            List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
            for(News news : videos){

                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("id", news.getId());
                item.put("title ", news.getTitle());
                item.put("timelenght", getResources().getString(R.string.timelength) + news.getTimelength() + getResources().getString(R.string.min) );
                data.add(item);
            }

            SimpleAdapter adapter = new SimpleAdapter(this,data, R.layout.item,new String[]{"title","timelength"}, new int[]{R.id.title,R.id.timelength});
            listView.setAdapter(adapter);



        } catch (Exception e) {
            e.printStackTrace();
        }


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
