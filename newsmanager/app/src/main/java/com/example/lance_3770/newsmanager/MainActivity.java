package com.example.lance_3770.newsmanager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Enumeration;


public class MainActivity extends Activity {

    private EditText titleText;
    private EditText lengthText;
    private EditText nameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());


        titleText = (EditText)this.findViewById(R.id.title);
        lengthText = (EditText)this.findViewById(R.id.timelength);
        nameText = (EditText) this.findViewById(R.id.filename);
    }

    public void save(View V) throws Exception {

        String title = titleText.getText().toString();
        String length = lengthText.getText().toString();
        String filename = nameText.getText().toString();


        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

            File uploadFile = new File(Environment.getExternalStorageDirectory(),filename);
            if(uploadFile.exists()){
                boolean result = NewsService.save(title, length, uploadFile);
                if (result) {
                    Toast.makeText(getApplicationContext(), R.string.suceess, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();

                }



            }else{

                Toast.makeText(getApplicationContext(),R.string.filenotexist,Toast.LENGTH_LONG).show();
            }


        }else{
            boolean result = NewsService.save(title, length);


            if (result) {
                Toast.makeText(getApplicationContext(), R.string.suceess, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();

            }

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
