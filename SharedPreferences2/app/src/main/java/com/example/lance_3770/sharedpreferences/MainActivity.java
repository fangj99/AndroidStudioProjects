package com.example.lance_3770.sharedpreferences;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;


public class MainActivity extends Activity {

    private EditText nameText;
    private EditText ageText;
    private PreferencesService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameText = (EditText) this.findViewById(R.id.name);
        ageText = (EditText) this.findViewById(R.id.age);
        service = new PreferencesService(this);

        Map<String, String> params = service.getPrferences();
        nameText.setText(params.get("name"));
        ageText.setText(params.get("age"));


    }



    public void save(View v){

        String name = nameText.getText().toString();
        String age = ageText.getText().toString();

        service.save(name,Integer.valueOf(age));
        Toast.makeText(getApplicationContext(),R.string.success,Toast.LENGTH_LONG ).show();



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
