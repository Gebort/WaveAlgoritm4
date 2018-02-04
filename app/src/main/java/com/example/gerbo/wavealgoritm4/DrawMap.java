package com.example.gerbo.wavealgoritm4;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gerbo on 30.01.2018.
 */

public class DrawMap extends Thread {

    private SurfaceHolder surfaceHolder;
    int CellSize = 50;
    int xm, ym;
    int xw = CellSize; // Размеры квадрата по X
    int yw = CellSize; // Размеры квадрата по Y
    int finx, finy;
    int[][] map;
    float movex = 0;
    float movey = 0;
    private volatile boolean running = true;//флаг для остановки потока
    Bitmap[][] map_images;

    InputStream in;
    Bitmap emptyField, wall, start, finish, path;

    {

        map = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int z = 0; z < 10; z++) {  // Заполнение массива нулями
                map[i][z] = 0;
            }
        }
        map[4][4]=1;
    }



    public DrawMap(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }


    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    // рисование на canvas
                    Paint paint = new Paint();
                    paint.setColor(Color.BLUE);
                    paint.setStyle(Paint.Style.STROKE);



                for(int i=0;i<10;i++){
                        for(int z=0;z<10;z++){
                        if ( map[i][z]== 0 ){
                            canvas.drawBitmap(emptyField, i*10,z*10,paint);
                        }
                    }
                }


                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
