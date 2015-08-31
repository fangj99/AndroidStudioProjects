package com.example.lance.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class SingleTopActivity extends Activity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);

        textView = (TextView) this.findViewById(R.id.singletopView);
        textView.setText(this.toString());

    }

    @Override //lance
    protected void onNewIntent(Intent intent) {
        Toast.makeText(getApplicationContext(), new Date().toString(),Toast.LENGTH_LONG).show();
        super.onNewIntent(intent);
    }

    public void openSingleTopActivity(View v){
        startActivity(new Intent(this, SingleTopActivity.class));

    }



    public void openSingleTaskActivity(View v){

        startActivity(new Intent(this, SingleTask.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_top, menu);
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
