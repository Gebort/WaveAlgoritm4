package com.example.gerbo.wavealgoritm4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import static com.example.gerbo.wavealgoritm4.DrawMap.setMap;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //https://habrahabr.ru/post/126316/ - статья про SurfaceView
        DrawView mainDisplay = new DrawView(this);  //передаем графику в объект mainDisplay
        setContentView(mainDisplay);                        //класса DrawView (SurfaceView)
        //mainDisplay.surfaceCreated(mainDisplay.getHolder());
        mainDisplay.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x =  motionEvent.getX(); // где совершен клик
        float y =  motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: // проверка нажатия
                break;
        };
        int qubex = (int) x/10; // координаты куба
        int qubey = (int) y/10;

        setMap(qubex, qubey, 2);
        return true;
    }
}
