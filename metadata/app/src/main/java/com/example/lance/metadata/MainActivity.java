package com.example.lance.metadata;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ActivityInfo activityInfo =  this.getPackageManager().getActivityInfo(new ComponentName(this, MainActivity.class),
                    PackageManager.GET_META_DATA );

            Bundle bundle = activityInfo.metaData;
            String name = bundle.getString("com.example.lance.metadata");
            Integer age = bundle.getInt("com.example.lance.metadataage");
            String app = bundle.getString("com.example.lance.metadataapp");
            int resid = bundle.getInt("com.example.lance.metadataid");


            Toast.makeText(getApplicationContext(), name + age + app + resid, Toast.LENGTH_LONG ).show();

        } catch (PackageManager.NameNotFoundException e) {
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
