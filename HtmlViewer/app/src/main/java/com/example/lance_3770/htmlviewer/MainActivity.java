package com.example.lance_3770.htmlviewer;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText pathText;
    private TextView codeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pathText = (EditText) this.findViewById(R.id.pagepath);
        codeView = (TextView) this.findViewById(R.id.codeView);
        Button button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickListener());

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedSqlLiteObjects()
                .penaltyLog().penaltyDeath().build());


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

    private final class ButtonClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {

            String path = pathText.getText().toString();
            try{
                String html = PageService.getHtml(path);
                codeView.setText(html);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), R.string.error,Toast.LENGTH_LONG).show();

            }
        }



    }
}
