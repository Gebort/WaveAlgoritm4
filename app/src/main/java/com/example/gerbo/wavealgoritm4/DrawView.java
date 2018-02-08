package com.example.gerbo.wavealgoritm4;

import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by gerbo on 30.01.2018.
 */

public class DrawView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawMap gameMap;   //поток отрисовки

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameMap = new DrawMap(getHolder(), getResources(), this.getWidth(), this.getHeight());
        gameMap.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameMap.join();
                retry = false;
            } catch (InterruptedException e) {}
        }
    }
 }

