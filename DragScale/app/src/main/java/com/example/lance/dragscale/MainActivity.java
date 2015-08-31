package com.example.lance.dragscale;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class MainActivity extends Activity {


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = (ImageView)this.findViewById(R.id.imageView);
        imageView.setOnTouchListener(new TouchListener());
    }


    private final class TouchListener implements View.OnTouchListener {

        private PointF startPoint = new PointF();
        private Matrix matrix = new Matrix();
        private Matrix currentMatrix = new Matrix();

        private int mode = 0;
        private static final int DRAG =1;
        private static final int ZOOM =2;

        private float startDis;
        private PointF midPoint = new PointF();


        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction() & MotionEvent.ACTION_MASK){ //get the last 8 bits
                case MotionEvent.ACTION_DOWN:

                    mode = DRAG;
                    currentMatrix.set(imageView.getImageMatrix()); //get current position before finger down
                    startPoint.set(event.getX(),event.getY());
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        float dx = event.getX() - startPoint.x;
                        float dy = event.getY() - startPoint.y;

                        matrix.set(currentMatrix); //move from the last position since last move
                        matrix.postTranslate(dx, dy);
                    }else if(mode == ZOOM){
                        float endDis = distance(event);

                        if( endDis > 10f) {
                            float scale = endDis / startDis;
                            matrix.set(currentMatrix);
                            matrix.postScale(scale, scale,midPoint.x,midPoint.y );
                        }

                    }


                    break;

                case MotionEvent.ACTION_UP:


                    break;

                case MotionEvent.ACTION_POINTER_DOWN: //second finger action_down
                    mode =ZOOM;
                    startDis = distance(event);
                    if(startDis > 10f) {
                        midPoint = mid(event);
                        currentMatrix.set(imageView.getImageMatrix()); //get current position before finger down

                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP: //second finger action_up

                    mode= 0;
                    break;


            }
            imageView.setImageMatrix(matrix);

            return true;
        }
    }

    public static PointF mid(MotionEvent event){

        float midx= (event.getX(0)+event.getX(1))/2;
        float midy= (event.getY(0)+event.getY(1))/2;

        return new PointF(midx, midy);
    }



    public static float distance(MotionEvent event){

        float dx = event.getX(1)-event.getX(0);
        float dy = event.getY(1)-event.getY(0);

        return FloatMath.sqrt(dx * dx + dy * dy);


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
