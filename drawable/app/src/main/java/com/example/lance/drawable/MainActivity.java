package com.example.lance.drawable;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) this.findViewById(R.id.imageView);
            }

    public void changeImage(View v){

        ClipDrawable clipDrawable = (ClipDrawable)imageView.getDrawable();
        clipDrawable.setLevel(clipDrawable.getLevel()+1000);



        //TransitionDrawable transitionDrawable = (TransitionDrawable)((Button) v).getBackground();
        //transitionDrawable.startTransition(500);

       // LevelListDrawable levelListDrawable =(LevelListDrawable)imageView.getDrawable();

        //levelListDrawable.setLevel(12);



       /* //LayerDrawable  layerDrawable = (LayerDrawable) imageView.getDrawable();

        LayerDrawable  layerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.layerlist);
        Drawable drawable = getResources().getDrawable(R.drawable.icon);

        layerDrawable.setDrawableByLayerId(R.id.userimage, drawable);
        imageView.setImageDrawable(layerDrawable);
*/

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
