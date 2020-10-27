package com.example.drawsquares;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linlay;
    ImageView imageview;
    Bitmap bitmap;
    Canvas canvas;
    Paint paintfill,paintstroke, paintcircle;
    Button bt;

    int screenwidth, screenheight;
    int width,height, squaresize;

    public void drawRect(int x1, int y1, int x2, int y2){
        canvas.drawRect(x1,y1,x2,y2,paintfill);
        canvas.drawRect(x1,y1,x2,y2,paintstroke);
        //canvas.drawCircle(x1,y1,100,paintcircle);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenwidth  = metrics.widthPixels;
        screenheight = metrics.heightPixels;
        width=height=screenwidth;
        squaresize = width/10;
        linlay = new LinearLayout(this);
        imageview = new ImageView(this);
        linlay.addView(imageview);
        imageview.setLayoutParams(new LinearLayout.LayoutParams(width,height));
        bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        imageview.setImageBitmap(bitmap);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.RED);
        paintfill = new Paint();
        paintfill.setColor(Color.BLUE);
        paintfill.setStyle(Paint.Style.FILL);
        paintstroke= new Paint();
        paintstroke.setColor(Color.YELLOW);
        paintstroke.setStyle(Paint.Style.STROKE);
        paintstroke.setStrokeWidth(5);
        paintcircle= new Paint();
        paintcircle.setColor(Color.BLACK);
        paintcircle.setStyle(Paint.Style.FILL);
        paintcircle.setStrokeWidth(5);
        drawRect(width/2-squaresize/2, height/2-squaresize/2,
                width/2+squaresize/2,height/2+squaresize/2);
        bt = new Button(this);
        bt.setText("New Square");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x1 = (int)(Math.random()*(width-squaresize));
                int y1 = (int)(Math.random()*(height-squaresize));
                drawRect(x1,y1,x1+squaresize,y1+squaresize);
            }
        });
        linlay.addView(bt);
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(linlay);
    }
}