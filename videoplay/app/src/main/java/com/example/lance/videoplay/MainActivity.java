package com.example.lance.videoplay;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {


    private EditText editText;
    private String path;
    private MediaPlayer mediaPlayer;
    private boolean pause;
    private int position;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        editText = (EditText)this.findViewById(R.id.filename);
        surfaceView =(SurfaceView)this.findViewById(R.id.surfaceView);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setFixedSize(176, 144);
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new SurfaceCallback());



    }

    private final class SurfaceCallback implements SurfaceHolder.Callback {


        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if(position>0 && path!=null){
                try {
                    play(position);
                    position = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if(mediaPlayer.isPlaying()){
                position = mediaPlayer.getCurrentPosition();
                mediaPlayer.stop();
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


    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }

    public void mediaplay(View v) throws Exception {

        switch (v.getId()){

            case R.id.playbutton:
                String filename = editText.getText().toString();

                if(filename.startsWith("http")){
                    path = filename;
                    play(0);



                }else{

                    File file = new File(Environment.getExternalStorageDirectory(),filename);

                    if(file.exists()){
                        path = file.getAbsolutePath();
                        play(0);
                    }else{
                        path =null;
                        Toast.makeText(getApplicationContext(),R.string.filenoexist,Toast.LENGTH_LONG).show();
                    }
                }


                break;

            case R.id.pausebutton:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    pause =true;
                }else{
                    if(pause){
                        mediaPlayer.start();
                        pause= false;
                    }
                }


                break;


            case R.id.resetbutton:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(0);

                }else{
                    if(path!=null){
                       play(0);
                    }
                }



                break;


            case R.id.stopbutton:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();

                }



                break;

            default:
                break;



        }







    }

    private void play( int position) throws Exception {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.setDisplay(surfaceView.getHolder());
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new PreparedListener(position));



        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private final class PreparedListener implements MediaPlayer.OnPreparedListener {

        private  int position;

        public PreparedListener(int position) {
            this.position = position;
        }


        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();
            if (position>0)mediaPlayer.seekTo(position);

        }
    }


}
