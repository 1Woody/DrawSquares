package com.example.drawsquares;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linlay;
    ImageView imageview;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;

    int screenwidth, screenheight;
    int width,height, squaresize;

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
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(width/2-squaresize/2, height/2-squaresize/2,
                width/2-squaresize/2,height/2-squaresize/2, paint);
        canvas.drawColor(Color.RED);
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(linlay);
    }
}