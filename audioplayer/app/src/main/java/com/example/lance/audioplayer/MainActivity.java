package com.example.lance.audioplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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


    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        editText = (EditText)this.findViewById(R.id.filename);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }



    private class MyPhoneListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state){
                case TelephonyManager.CALL_STATE_RINGING:
                    if(mediaPlayer.isPlaying()){
                        position = mediaPlayer.getCurrentPosition();
                        mediaPlayer.stop();
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    if(position>0 && path!=null){
                        try {
                            play(position);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        position=0;
                    }
                    break;


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
/*
    @Override
    protected void onResume() {

        if(position>0 && path!=null){
            try {
                play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer.seekTo(position);
            position=0;
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(mediaPlayer.isPlaying()){
            position = mediaPlayer.getCurrentPosition();
            mediaPlayer.stop();
        }
        super.onPause();
    }
*/


    public void mediaplay(View v) throws Exception {

        switch (v.getId()){

            case R.id.playbutton:
                String filename =editText.getText().toString();
                File audio = new File(Environment.getExternalStorageDirectory(),filename);

                if(audio.exists()){
                    path = audio.getAbsolutePath();
                    play(0);

                }else{
                    path = null;
                    Toast.makeText(getApplicationContext(),R.string.filenoexist,Toast.LENGTH_LONG).show();

                }

                break;

            case R.id.pausebutton:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    pause = true;
                    ((Button)v).setText(R.string.continues);
                }else{
                    if (pause == true){
                        mediaPlayer.start(); //
                        pause = false;
                        ((Button)v).setText(R.string.pausebutton);
                    }

                }


                break;


            case R.id.resetbutton:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(0);//replay music from the stat position
                }else{
                    if(path!=null){
                        play(0);
                    }

                }

                break;


            case R.id.stopbutton:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;

            default:
                break;



        }







    }

    private void play(int position) throws Exception {

        try {
            mediaPlayer.reset(); //reset every argument to original state
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();//data cache for play
            mediaPlayer.setOnPreparedListener(new PrepareListener(position));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private final class PrepareListener implements MediaPlayer.OnPreparedListener{

        private int position;

        public PrepareListener(int position) {
            this.position = position;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {

            // means data cache is done, system can start to play
            mediaPlayer.start();
            if (position > 0 ) mediaPlayer.seekTo(position);

        }
    }



}
