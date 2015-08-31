package com.example.lance.mulactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class OtherActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_activity2);

        Intent intent =getIntent();// the intent used to activate this activity with extra info packed in

        /*String company = intent.getStringExtra("company");
        int age =intent.getIntExtra("age", 0);
        */

        Bundle bundle = intent.getExtras();
        String company = bundle.getString("company");
        int age =bundle.getInt("age");

        TextView textView = (TextView) this.findViewById(R.id.textView);
        textView.setText("Company Name: "+ company + " for " + age + " years");


    }


    public void CloseActivity(View v){
        Intent intent = new Intent();
        intent.putExtra("result", "good night");

        setResult(20,intent );
        this.finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other_activity2, menu);
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
