package com.example.lance.picture;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {


    private View layout;
    private SurfaceView surfaceView;
    private Camera camera;
    private String TAG ="MainActivity";



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            layout.setVisibility(ViewGroup.VISIBLE);
            return true;


        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        layout = this.findViewById(R.id.buttonlayout);
        surfaceView = (SurfaceView)this.findViewById(R.id.surfaceView);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setFixedSize(320, 280);
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new SurfaceCallback() );


    }


    public void takepicture(View v){
        if(camera!=null) {


            switch (v.getId()) {

                case R.id.takepicture:
                    camera.takePicture(null, null, new MyPictureCallback());

                    break;

                case R.id.autofocus:

                    camera.autoFocus(null);
                    break;






            }
        }

    }

    public final class MyPictureCallback implements Camera.PictureCallback{


        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File jpgFile = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(jpgFile);
                fileOutputStream.write(data);
                fileOutputStream.close();
                camera.startPreview();  //start review after picture processed
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



    private final class  SurfaceCallback implements SurfaceHolder.Callback{


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();

              try {
                  parameters.setPictureSize(1920, 1080);
                  parameters.setPreviewFrameRate(30);
                  parameters.setPreviewSize(800, 480);
                  parameters.setJpegQuality(80);

                  camera.setParameters(parameters);

                  camera.setPreviewDisplay(holder);
                  camera.startPreview();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i(TAG, parameters.flatten());

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if(camera!=null){
                camera.release();
                camera = null;
            }


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
