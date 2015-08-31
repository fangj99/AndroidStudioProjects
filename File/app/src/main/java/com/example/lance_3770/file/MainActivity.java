package com.example.lance_3770.file;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=(Button)this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickListerner());
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

    private class ButtonClickListerner implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            EditText filenameText=(EditText)findViewById(R.id.filename);
            EditText contentText=(EditText)findViewById(R.id.filecontent);
            String filename= filenameText.getText().toString();
            String content= contentText.getText().toString();
            FileService service=new FileService(getApplicationContext());
            try{
                if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
                    service.save(filename, content);
                    Toast.makeText(getApplicationContext(),R.string.success, 1).show();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.sdcarderror, 1).show();

                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),R.string.failure,1).show();
                e.printStackTrace();

            }



        }
    }
}
