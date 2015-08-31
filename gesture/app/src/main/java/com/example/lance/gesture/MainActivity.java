package com.example.lance.gesture;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    private String TAG ="MainActivity";
    private GestureLibrary library;
    private  Gesture gesture;
    private GestureOverlayView gestureOverlayView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureOverlayView = (GestureOverlayView)this.findViewById(R.id.gesture);
        //gestureOverlayView.addOnGesturePerformedListener(new GesturePerformedListener()); // only work on single gesture, not owrkk on multiple

        gestureOverlayView.addOnGestureListener(new GestureListener());

        library = GestureLibraries.fromRawResource(this, R.raw.gestures);
        library.load();



    }

    public void find(View v){

        recognize(gesture);
        gestureOverlayView.clear(true);



    }


    private final class GestureListener implements GestureOverlayView.OnGestureListener{

        @Override
        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            Log.i(TAG, "onGestureStarted");

        }

        @Override
        public void onGesture(GestureOverlayView overlay, MotionEvent event) {
            Log.i(TAG, "onGesture");

        }

        @Override
        public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {

            gesture = overlay.getGesture();

            Log.i(TAG, "onGestureEnded");

        }

        @Override
        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
            Log.i(TAG, "onGestureCancelled");

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public final class GesturePerformedListener implements GestureOverlayView.OnGesturePerformedListener {


        @Override
        public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

            recognize(gesture);


        }

      }


    private void recognize(Gesture gesture) {

        ArrayList<Prediction> predictions = library.recognize(gesture);
        if(!predictions.isEmpty()){
            Prediction prediction = predictions.get(0);
            if (prediction.score>=6){
                if ("zhangxx".equals(prediction.name )){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1350505050"));
                    startActivity(intent);
                }else if ("close".equals(prediction.name)){
                    finish();

                }

            }else{
                Toast.makeText(getApplicationContext(), R.string.lowmatch, Toast.LENGTH_LONG).show();

            }


        }else{
            Toast.makeText(getApplicationContext(), R.string.nomatch, Toast.LENGTH_LONG).show();
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
