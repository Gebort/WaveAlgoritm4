package com.example.gerbo.wavealgoritm4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //https://habrahabr.ru/post/126316/ - статья про SurfaceView
        DrawView mainDisplay = new DrawView(this);  //передаем графику в объект mainDisplay
        setContentView(mainDisplay);                        //класса DrawView (SurfaceView)

    }
}
