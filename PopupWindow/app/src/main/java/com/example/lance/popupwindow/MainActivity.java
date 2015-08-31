package com.example.lance.popupwindow;

import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private PopupWindow popupWindow;
    private View parent;
    private GridView gridView;
    private int[] images ={
            R.drawable.i1,
    R.drawable.i2,
    R.drawable.i3,
    R.drawable.i4,
    R.drawable.i5,
    R.drawable.i6,
    R.drawable.i7,
    R.drawable.i8};



    private String[] names ={"Search", "FileManager","Download Manager", "Full Screen",
            "Web Address" , "Bookmark", "Add Bookmark","Share Page"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View contentView = getLayoutInflater().inflate(R.layout.popwindow, null);

        gridView = (GridView)contentView.findViewById(R.id.gridView);
        gridView.setAdapter(getAdapter());
        gridView.setOnItemClickListener(new ItemClickListener());


        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setFocusable(true);//get focus
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setAnimationStyle(R.style.animation);

        parent = this.findViewById(R.id.main);
    }



    private final class ItemClickListener implements AdapterView.OnItemClickListener{


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(popupWindow.isShowing()){
                popupWindow.dismiss();
            }



        }
    }





    private ListAdapter getAdapter(){

        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for(int i= 0;i<images.length;i++){

            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("image", images[i]);
            hashMap.put("name", names[i]);
            data.add(hashMap);

        }


        SimpleAdapter simpleAdapters = new SimpleAdapter(this,data, R.layout.grid_item,
                new String[]{"image", "name"}, new int[]{R.id.imageView,R.id.textView});
        return simpleAdapters;

    }


    public void OpenPopWindow(View v) {


        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0,0);



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
