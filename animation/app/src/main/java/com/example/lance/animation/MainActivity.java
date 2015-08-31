package com.example.lance.animation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;


public class MainActivity extends Activity {

    private float startX;
    private ViewFlipper viewFlipper;
    private Animation out_lefttoright;
    private Animation in_lefttoright;
    private Animation out_righttoleft;
    private Animation in_righttoleft;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            startX= event.getX();

        }else if(event.getAction()==MotionEvent.ACTION_UP){
            float endX= event.getX();
            if(endX > startX){

                viewFlipper.setInAnimation(in_lefttoright);
                viewFlipper.setOutAnimation(out_lefttoright);
                viewFlipper.showNext();


            }else if(endX<startX){
                viewFlipper.setInAnimation(in_righttoleft);
                viewFlipper.setOutAnimation(out_righttoleft);
                viewFlipper.showPrevious();
            }
            return true;

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper)this.findViewById(R.id.viewFlipper);
        in_lefttoright = AnimationUtils.loadAnimation(this,R.anim.enter_lefttoright);
        out_lefttoright = AnimationUtils.loadAnimation(this,R.anim.exit_lefttoright);
        in_righttoleft = AnimationUtils.loadAnimation(this,R.anim.enter_righttoleft);
        out_righttoleft = AnimationUtils.loadAnimation(this,R.anim.exit_righttoleft);


    }



    public void openActivity(View v){

        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);

        this.overridePendingTransition(R.anim.enteralpha,R.anim.exitalpha);

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
