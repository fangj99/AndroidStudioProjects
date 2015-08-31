package com.example.lance.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    private ImageView imageView;
    private SensorManager manager;
    private SensorListener sensorListener = new SensorListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)this.findViewById(R.id.imageView);

        manager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onPause() {
        manager.unregisterListener(sensorListener);
        super.onPause();
    }

    @Override
    protected void onResume() {

        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        manager.registerListener(sensorListener,sensor,SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    private final class SensorListener implements SensorEventListener{


        private float predegree = 0;
        @Override
        public void onSensorChanged(SensorEvent event) {

            float degree = event.values[0]; //store the orintation
            RotateAnimation animation = new RotateAnimation(predegree, -degree,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1000);
            imageView.startAnimation(animation);
            predegree = degree;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
