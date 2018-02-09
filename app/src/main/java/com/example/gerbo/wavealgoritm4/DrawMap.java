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
    private static int screenSizeX, screenSizeY; //размеры экрана
    private static int cellSizeX, cellSizeY; //размеры клеток по высоте и ширине
    private static int cellNumbX = 15;
    private static int cellNumbY = 25;
    private static int[][] map;
    private Bitmap emptyField, wall, start, finish, path;

    /*
    Дабы не мудрить с массивом map, чтобы с ним один работали и отрисовщик, и будущий алгорит
    поиска пути, которому нужно будет в массив забивать длину пути, решил сделать обозначение
    разных типов полей отрицательными значениями:
     emptyField - 0
     wall ------ -1
     start ----- -2
     finish ---- -3
     path ------ -4
     Такие дела
     */

    public DrawMap(SurfaceHolder surfaceHolder, Resources resources, int screenX, int screenY) {

        /* Если честно, все блоки, которые я выделил ниже, кроме запоминания параметров,
        в хорошем стиле ООП нужно выносить в отдельные функции. Но раз
        это просто тест алгоритма, пусть будет так. В противном случае в большой программе
        можно было бы запутаться*/

        //запоминаем переданные параметры
        this.surfaceHolder = surfaceHolder;
        screenSizeX = screenX;
        screenSizeY = screenY;

        //загружаем картинки
        emptyField = BitmapFactory.decodeResource(resources, R.drawable.empty_field);
        wall = BitmapFactory.decodeResource(resources, R.drawable.wall);
        start = BitmapFactory.decodeResource(resources, R.drawable.start);
        finish = BitmapFactory.decodeResource(resources, R.drawable.finish);
        path = BitmapFactory.decodeResource(resources, R.drawable.path);

        //создаем массив нужной размерности и заполняем его 0-ми
        map = new int[cellNumbY][cellNumbX];
        for (int y = 0; y < cellNumbY; y++) {
            for (int x = 0; x < cellNumbX; x++) {
                map[y][x] = 0;
            }
        }

        //считаем размер клеток
        cellSizeX = screenSizeX / cellNumbX;
        cellSizeY = screenSizeY / cellNumbY;
    }

    @Override
    public void run() {
        while (true) {
        Canvas canvas = surfaceHolder.lockCanvas();

            try {
                Paint paint = new Paint();
                canvas.drawColor(Color.WHITE);
                for (int y = 0; y < cellNumbY; y++) {
                    for (int x = 0; x < cellNumbX; x++) {
                        switch (map[y][x]) {
                            case 0:
                                canvas.drawBitmap(emptyField, x * cellSizeX, y * cellSizeY, paint);
                                break;
                            case -1:
                                canvas.drawBitmap(wall, x * cellSizeX, y * cellSizeY, paint);
                                break;
                            case -2:
                                canvas.drawBitmap(start, x * cellSizeX, y * cellSizeY, paint);
                                break;
                            case -3:
                                canvas.drawBitmap(finish, x * cellSizeX, y * cellSizeY, paint);
                                break;
                            case -4:
                                canvas.drawBitmap(path, x * cellSizeX, y * cellSizeY, paint);
                                break;
                        }

                    }
                }
            } finally {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }}


static public void setMap(int x,int y){
        int cellX = x / cellSizeX;
        int cellY = y / cellSizeY;
        map[cellY][cellX]--;
        if (map[cellY][cellX] == -4) map[cellY][cellX] = 0;
}

static public void buildWay() {
    int[][] wayMap = new int[cellNumbY][cellNumbX];
    wayMap = PathFinder.findYourWay(map, cellNumbY, cellNumbX);

    for (int y = 0; y < cellNumbY; y++) {
        for (int x = 0; x < cellNumbX; x++) {
            if (wayMap[y][x] == -4) map[y][x] = -4;
        }
    }
}

}

