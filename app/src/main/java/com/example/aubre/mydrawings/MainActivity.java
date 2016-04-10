package com.example.aubre.mydrawings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Aubre on 2/28/2016.
 */
public class MainActivity extends AppCompatActivity {
    //cavnas view
    private DrawingVeiw drawingVeiw;
    private ImageButton currnPaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_drawing_veiw);

        drawingVeiw = (DrawingVeiw)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.Paint_colors);

        currnPaint = (ImageButton)paintLayout.getChildAt(0);
        currnPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));


    }
   public void paintClicked(View view){
       //using the colors

       //updating colors
       if (view != currnPaint){
           ImageButton imgView = (ImageButton)view;
           String color = view.getTag().toString();

           drawingVeiw.setColor(color);

           imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
           currnPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
           currnPaint=(ImageButton)view;
       }
   }
}
