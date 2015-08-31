package com.example.lance.datapageload;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;


public class MainActivity extends Activity {

    private ListView listView;
    private static String TAG="MainActivity";
    private List<String> data = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private  View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) this.findViewById(R.id.listView);
        listView.setOnScrollListener(new SrollListener());


        footer = getLayoutInflater().inflate(R.layout.footer, null);


        data.addAll(DataService.getData(0, 30));


        adapter = new ArrayAdapter<String>(this, R.layout.listview_item,R.id.textView,data );

        listView.addFooterView(footer); // call before setAdapter;

        listView.setAdapter(adapter);

        listView.removeFooterView(footer); // remove first, when loading then add footview
    }


    private int number = 20; //the number each fetch
    private int maxpage = 5;//total pages
    private boolean loadfinish = true;

    private final class SrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            Log.i(TAG, "onScrollStateChanged"+scrollState );


        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, final int totalItemCount) {
            Log.i(TAG, "onScroll" + firstVisibleItem + visibleItemCount + totalItemCount);



            if (visibleItemCount > 0 ){


                final int loadtotal =totalItemCount;
                int lastItemId= listView.getLastVisiblePosition();
                if((lastItemId+1)==totalItemCount){ //reach the last record, need update
                    int currentpage = totalItemCount%number==0? totalItemCount/number :totalItemCount/number+1;
                    int nextpage = currentpage+1;//next page
                    if(nextpage <= maxpage && loadfinish){
                        loadfinish =false;

                        listView.addFooterView(footer);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                List<String> result = DataService.getData(loadtotal,number);
                                handler.sendMessage(handler.obtainMessage(100, result));

                            }
                        }).start();



                    }

                }
            }

        }




      }


    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            data.addAll((List<String>)msg.obj);
            adapter.notifyDataSetChanged();
            loadfinish = true;
            if(listView.getFooterViewsCount()>0)listView.removeFooterView(footer);
        }
    };

}
