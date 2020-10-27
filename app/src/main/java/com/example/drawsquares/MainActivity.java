package com.example.drawsquares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
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
    Paint paintfill,paintstroke;

    int screenwidth, screenheight;
    int width,height, squaresize;

    private class ButtonMenu{
        Button bt;
        public ButtonMenu(final String text, Context THIS){
            bt = new Button(THIS);
            bt.setText(text);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int x1 = (int)(Math.random()*(width-squaresize));
                    int y1 = (int)(Math.random()*(height-squaresize));
                    switch (text) {
                        case "SQUARE":
                            paintfill.setColor(Color.BLUE);
                            paintstroke.setColor(Color.YELLOW);
                            drawRect(x1, y1, x1 + squaresize, y1 + squaresize);
                            break;
                        case "CIRCLE":
                            paintfill.setColor(Color.BLACK);
                            paintstroke.setColor(Color.GREEN);
                            drawCircle(x1, y1);

                            break;
                        case "TRIANGLE":
                            paintfill.setColor(Color.MAGENTA);
                            paintstroke.setColor(Color.WHITE);
                            drawTriangle(x1, y1);
                            break;
                        default:
                            canvas.drawColor(Color.RED);
                            break;
                    }
                }
            });
        }
    }

    public void drawRect(int x1, int y1, int x2, int y2){
        canvas.drawRect(x1,y1,x2,y2,paintfill);
        canvas.drawRect(x1,y1,x2,y2,paintstroke);

    }

    public void drawCircle(int x1, int y1){

        canvas.drawCircle(x1,y1,60,paintfill);
        canvas.drawCircle(x1,y1,60,paintstroke);
    }

    public void drawTriangle(int x1, int y1){

        Point a = new Point(x1,y1);
        Point b = new Point(x1+squaresize, y1);
        Point c = new Point(x1+squaresize/2, y1-squaresize);

        Path path = new Path();
        path.moveTo(a.x,a.y);
        path.lineTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, paintfill);
        canvas.drawPath(path,paintstroke);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] polygonNames = {"SQUARE","CIRCLE","TRIANGLE","CLEAN"};
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

        paintfill = new Paint();
        paintfill.setColor(Color.BLUE);
        paintfill.setStyle(Paint.Style.FILL);
        paintstroke= new Paint();
        paintstroke.setStyle(Paint.Style.STROKE);
        paintstroke.setStrokeWidth(5);

        canvas.drawColor(Color.RED);
        for(int i=0; i<4; i++){
            linlay.addView((new ButtonMenu(polygonNames[i],this)).bt);
        }

        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(linlay);
    }
}