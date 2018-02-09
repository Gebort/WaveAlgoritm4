package com.example.gerbo.wavealgoritm4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import static com.example.gerbo.wavealgoritm4.DrawMap.buildWay;
import static com.example.gerbo.wavealgoritm4.DrawMap.setMap;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    DrawView mainDisplay;
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //https://habrahabr.ru/post/126316/ - статья про SurfaceView
        mainDisplay = new DrawView(this);  //передаем графику в объект mainDisplay
        setContentView(mainDisplay);                        //класса DrawView (SurfaceView)

        mainDisplay.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if ((System.currentTimeMillis() - startTime) > 2000) buildWay();
                else {
                int x =  (int) motionEvent.getX();
                int y =  (int) motionEvent.getY();
                setMap(x, y);}
                break;
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
                break;
        }
        return true;
    }
}
