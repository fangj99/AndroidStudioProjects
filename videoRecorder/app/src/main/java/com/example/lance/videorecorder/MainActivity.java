package com.example.lance.videorecorder;

import android.app.Activity;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import static android.view.SurfaceHolder.*;


public class MainActivity extends Activity {

    private View layout;
    private SurfaceView surfaceView;
    private Camera camera;
    private String TAG ="MainActivity";
    private Button stopButton;
    private Button recordButton;
    private MediaRecorder mediaRecorder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView)this.findViewById(R.id.surfaceView);


        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setFixedSize(320, 280);
        surfaceView.getHolder().setKeepScreenOn(true);


        layout = this.findViewById(R.id.buttonlayout);
        stopButton = (Button)this.findViewById(R.id.stopbutton);
        recordButton = (Button)this.findViewById(R.id.recordbutton);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            layout.setVisibility(ViewGroup.VISIBLE);
        }


        return super.onTouchEvent(event);
    }

    public void record(View v) throws IOException {

        switch (v.getId()){
            case R.id.recordbutton:
                try {
                    File videoFile = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".3gp");

                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setVideoSize(640, 480);
                    mediaRecorder.setVideoFrameRate(5);

                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);

                    mediaRecorder.setOutputFile(videoFile.getAbsolutePath());
                    mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                recordButton.setEnabled(false);
                stopButton.setEnabled(true);

                break;
            case R.id.stopbutton:

                if(mediaRecorder != null ){
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                }

                recordButton.setEnabled(true);
                stopButton.setEnabled(false);

                break;


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
