package com.example.lance.multhreaddownloader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lance.download.DownloadProgressListener;
import com.example.lance.download.FileDownloader;

import java.io.File;
import android.os.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends Activity {

    private EditText pathText;
    private TextView resultView;
    private Button downloadButton;
    private Button stopButton;
    private ProgressBar progressBar;
    private Handler handler = new UIHandler();


    private final class UIHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 1:
                    int size = msg.getData().getInt("size");

                    progressBar.setProgress(size);
                    float num = ((float) progressBar.getProgress() / (float) progressBar.getMax());
                    int result = (int) (num * 100);
                    resultView.setText(result + "%");
                    if(progressBar.getProgress()== progressBar.getMax()){
                        Toast.makeText(getApplicationContext(),R.string.success,Toast.LENGTH_LONG).show();
                    }
                    break;

                case -1:
                    Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_LONG).show();
                    break;




            }        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pathText = (EditText)this.findViewById(R.id.path);
        resultView = (TextView)this.findViewById(R.id.resultView);
        downloadButton = (Button) this.findViewById(R.id.downloadbutton);
        stopButton = (Button) this.findViewById(R.id.stopbutton);
        progressBar = (ProgressBar)this.findViewById(R.id.progressbar);

        ButtonClickListener listener = new ButtonClickListener();
        downloadButton.setOnClickListener(listener);
        stopButton.setOnClickListener(listener);


    }

    private final class ButtonClickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {


            switch (v.getId()){
                case R.id.downloadbutton:
                    String path = pathText.getText().toString();
                    File saveDir = null;
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        saveDir = Environment.getExternalStorageDirectory();
                        download(path, saveDir);


                    }else{
                        saveDir= Environment.getRootDirectory();
                        download(path, saveDir);
                        //Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_LONG).show();
                    }
                    stopButton.setEnabled(true);
                    downloadButton.setEnabled(false);

                    break;
                case R.id.stopbutton:
                    exit();
                    stopButton.setEnabled(false);
                    downloadButton.setEnabled(true);
                    break;

            }
        }


        private DownloadTask downloadTask;
        public void exit(){
            if(downloadTask != null){
                downloadTask.exit();
            }
        }
        private void download(String path, File saveDir) {  //running on the main thread, should running on sub thread
            downloadTask = new DownloadTask(path, saveDir);

            new Thread(downloadTask).start();


        }

    }

//controls in ui should be repaint by main thread, sub thread update will not flash teh ui controls
    private final class DownloadTask implements  Runnable{

        private String path;
        private File saveDir;
        private FileDownloader loader;

        public DownloadTask(String path, File saveDir) {
            this.path = path;
            this.saveDir = saveDir;

        }

       public void exit(){
           if (loader != null){
               loader.exit();
           }

       }

        @Override
        public void run() {
            try {
                loader = new FileDownloader(getApplicationContext(), path,saveDir, 3);
                progressBar.setMax(loader.getFileSize());


                loader.download(new DownloadProgressListener() {
                    @Override
                    public void onDownloadSize(int size) {

                        Message msg = new Message();
                        msg.what = 1;
                        msg.getData().putInt("size", size);
                        handler.sendMessage(msg);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                handler.sendMessage(handler.obtainMessage(-1));
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
