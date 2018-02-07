package com.example.gerbo.wavealgoritm4;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
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
    static int[][] map;
    InputStream in;
    Bitmap emptyField, wall, start, finish, path;

    public DrawMap(SurfaceHolder surfaceHolder, Resources resources) {
        this.surfaceHolder = surfaceHolder;
        map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int z = 0; z < 10; z++) {  // Заполнение массива нулями
                map[i][z] = 0;
            }
        }
        map[4][4]=1;
    }

    @Override
    public void run() {
        Canvas canvas = surfaceHolder.lockCanvas();
        try {
            Paint paint = new Paint();
            for(int i=0;i<10;i++){
                for(int z=0;z<10;z++){
                    if ( map[i][z] == 0 ){
                        canvas.drawBitmap(emptyField, i*50,z*50, paint);
                    }
                }
            }
        } finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
          }
    }

static public void setMap(int x,int y,int n){
        map[x][y]= n;
}



}

