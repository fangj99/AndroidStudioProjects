package com.example.lance.tween;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation animation;
       // animation = AnimationUtils.loadAnimation(this,R.anim.alpha);//using alpha.xml to creat animation object
       // animation = AnimationUtils.loadAnimation(this,R.anim.translate);//using alpha.xml to creat animation object
        //animation = AnimationUtils.loadAnimation(this,R.anim.scale);//using alpha.xml to creat animation object
        //animation = AnimationUtils.loadAnimation(this,R.anim.rotate);//using alpha.xml to creat animation object
        animation = AnimationUtils.loadAnimation(this,R.anim.lance);//using alpha.xml to creat animation object


       // Animation animation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,
        //        Animation.RELATIVE_TO_SELF,0.5f);

        //animation.setDuration(5000);

        animation.setFillAfter(true);//stay the end point
        imageView = (ImageView) this.findViewById( R.id.imageView);
        imageView.startAnimation(animation);
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
