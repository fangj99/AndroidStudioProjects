package com.example.lance.dataasyncload;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import java.io.File;
import java.util.List;

import java.util.logging.LogRecord;

/**
 * Created by lance on 7/19/2015.
 */
public class ContactAdapter extends BaseAdapter {

    private List<Contact>  data;
    private int listviewItem;
    private File cache;
    private LayoutInflater layoutInflater;





    public ContactAdapter(Context context, List<Contact> data, int listview_item, File cache) {

        this.data =data;
        this.listviewItem = listview_item;
        this.cache =cache;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {


        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // important, everytime diaply on row, will call this method
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = null;
        TextView textView = null;

        if(convertView == null){ //first display, convertView is null
           convertView =  layoutInflater.inflate(listviewItem,null); //get listview item class object
           imageView = (ImageView)convertView.findViewById(R.id.imageView);
           textView = (TextView)convertView.findViewById(R.id.textView);
           convertView.setTag(new DataWrapper( imageView, textView));

        }else {
            DataWrapper dataWrapper = (DataWrapper)convertView.getTag();
            imageView = dataWrapper.imageView;
            textView = dataWrapper.textView;

        }

        Contact contact = data.get(position);
        textView.setText(contact.name);
        asyncImageLoad(imageView,contact.image);


        return convertView;
    }


    private void asyncImageLoad( ImageView imageView,  String imagepath) {

        AsyncImageTask asyncImageTask = new AsyncImageTask(imageView);
        asyncImageTask.execute(imagepath);

    }


    private final class AsyncImageTask extends AsyncTask<String, Integer, Uri>{

        private ImageView imageView;

        public AsyncImageTask(ImageView imageView) {
            this.imageView = imageView;

        }

        @Override
        protected void onPostExecute(Uri uri) { //running on main thread, so we can update control

             if(uri!=null && imageView!= null) {
                imageView.setImageURI(uri);
            }
        }

        @Override
        protected Uri doInBackground(String... params) {
            try {
                return  ContactService.getImage(params[0],cache);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

/*
    private void asyncImageLoad(final ImageView imageView, final String imagepath) {

        final Handler handler = new Handler() {//still in main thread, send msg to msg queue
            @Override
            public void handleMessage(Message msg) {//also still in main thread, send msg to msg queue
                Uri uri = (Uri)msg.obj;
                if(uri!=null && imageView!= null) {
                    imageView.setImageURI(uri);
                }
            }


        };



        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Uri uri = ContactService.getImage(imagepath,cache);
                    handler.sendMessage(handler.obtainMessage(10,uri));


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        new Thread(runnable).start();

    }

    */

    private final class DataWrapper{
        public ImageView imageView;
        public TextView textView;

        public DataWrapper(ImageView imageView, TextView textView) {
            this.imageView = imageView;
            this.textView = textView;
        }
    }
}
