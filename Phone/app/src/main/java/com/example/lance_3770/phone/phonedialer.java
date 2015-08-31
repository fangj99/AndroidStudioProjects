package com.example.lance_3770.phone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class phonedialer extends Activity {

    /*lance changed*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonedialer);
        Button button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickListener());
    }


     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phonedialer, menu);
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
    /*lance changed*/
    private final class ButtonClickListener implements View.OnClickListener {
        public void onClick(View v){
            EditText mobileText = (EditText) findViewById(R.id.mobile);
            String number = mobileText.getText().toString();
            Intent intent= new Intent();
            intent.setAction("android.intent.action.CALL");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData (Uri.parse("tel:" + number));
            startActivity (intent);// 可以不用intent.addCategory("android.intent.category.DEFAULT"); 自动添加
        }


    }
}
