package com.example.lance.remoteserviceclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lance.remoteservice.StudentQuery;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    private EditText studentNo;
    private ServiceConnection conn = new StudentServiceConnection();
    private TextView textView;
    private StudentQuery studentQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentNo= (EditText) this.findViewById(R.id.studentno);
        textView = (TextView)this.findViewById(R.id.resultView);
        Button button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonOnclickListener());

        Intent service = new Intent("com.example.lance.query");
        bindService(service, conn, BIND_AUTO_CREATE);

    }



    private class StudentServiceConnection implements ServiceConnection{


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            studentQuery = StudentQuery.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            studentQuery = null;
        }
    }


    public void queryStudent(View v) throws RemoteException {
        String number = studentNo.getText().toString();
        int num =Integer.valueOf(number);

        try {
            String name = studentQuery.queryStudent(num);
            textView.setText(name);
        } catch (RemoteException e) {
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

    private class ButtonOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                String number = studentNo.getText().toString();
                int num =Integer.valueOf(number);

                try {
                    String name = studentQuery.queryStudent(num);
                    textView.setText(name);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}
