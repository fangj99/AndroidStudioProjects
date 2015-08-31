package com.example.lance.mulactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    protected  void onActivityResult(int requestCode, int resultCode, Intent data){

        Toast.makeText(getApplicationContext(),data.getStringExtra("result"),Toast.LENGTH_LONG).show();

    }

    public void openActivity(View v){

        Intent intent = new Intent();
        intent.setClass(this,OtherActivity2.class);
       /*
        intent.putExtra("company", "all nature vitamin center");
        intent.putExtra("age", 5);
        */

        Bundle bundle = new Bundle();
        bundle.putString("company", "all nature vitamin center");
        bundle.putInt("age", 5);
        intent.putExtras(bundle);

        //startActivity(intent); //startActivityForResult(intent); when second activity closed, send back resuly to main activity

        startActivityForResult(intent,200);

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
