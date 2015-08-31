package com.example.lance_3770.sms;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class sms extends Activity {

    private EditText numberText;
    private EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        numberText=(EditText)this.findViewById(R.id.number);
        contentText= (EditText)this.findViewById(R.id.content);
        Button button = (Button)this.findViewById(R.id.button) ;
        button.setOnClickListener(new ButtonClickListener());
    }

    private final class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String number = numberText.getText().toString();
            String content = contentText.getText().toString();
            SmsManager manager = SmsManager.getDefault();
            ArrayList<String> texts = manager.divideMessage(content);
            for(String text: texts) {
                manager.sendTextMessage(number, null, text,null,null );
            }
            Toast.makeText(getApplicationContext(),R.string.success,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sms, menu);
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
