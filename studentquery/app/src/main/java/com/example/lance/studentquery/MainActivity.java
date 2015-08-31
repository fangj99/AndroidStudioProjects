package com.example.lance.studentquery;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    private EditText studentNo;
    private ServiceConnection conn = new StudentServiceConnection();
    private IStudent iStudent ;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentNo= (EditText) this.findViewById(R.id.studentno);
        textView = (TextView)this.findViewById(R.id.resultView);
        Button button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonOnclickListener());

        Intent service = new Intent(this, StudentService.class);
        bindService(service,conn,BIND_AUTO_CREATE);

    }



    private class StudentServiceConnection implements ServiceConnection{


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iStudent=(IStudent) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iStudent = null;
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

    private class ButtonOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                String no = studentNo.getText().toString();
                String name = iStudent.queryStudent(Integer.valueOf(no));
                textView.setText(name);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
            }

        }
    }
}
