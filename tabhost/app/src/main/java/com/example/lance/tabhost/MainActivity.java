package com.example.lance.tabhost;

import android.graphics.Color;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onDestroy() {

        Debug.stopMethodTracing();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private  TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Debug.startMethodTracing("lancedebug");


        tabHost = (TabHost)this.findViewById(R.id.tabhost);
        tabHost.setup(); // system will find tabwidget and tabcontent controls's id

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("page1");
        //tabSpec.setIndicator("Home", getResources().getDrawable(R.drawable.i1));
        tabSpec.setIndicator(createTabView("Home"));

        tabSpec.setContent(R.id.page1);
        tabHost.addTab(tabSpec);

        //tabSpec.setIndicator("Page2", getResources().getDrawable(R.drawable.i2));
        tabSpec.setIndicator(createTabView("Page2"));
        tabSpec.setContent(R.id.page2);
        tabHost.addTab(tabSpec);

        //tabSpec.setIndicator("Page3", getResources().getDrawable(R.drawable.i7));
        tabSpec.setIndicator(createTabView("Page3"));
        tabSpec.setContent(R.id.page3);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);


    }

    private View createTabView(String home) {

        //View tabView = getLayoutInflater().inflate(R.layout.tab,null);
        //TextView textView = (TextView)tabView.findViewById(R.id.name);
       //textView.setText(home);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.WHITE);
        TextView textView= new TextView(this);
        textView.setText(home);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(18.0f);
        textView.setBackgroundResource(R.drawable.tab_bg);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        linearLayout.addView(textView, params);

        return linearLayout;
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
