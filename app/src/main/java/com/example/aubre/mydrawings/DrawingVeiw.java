package com.example.aubre.mydrawings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingVeiw extends View {

    //the drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //the starting color
    private int paintColor = 0xFF660000, paintAlpha = 255;
    //the canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    //brush sizes
    private float brushSize, lastBrushSize;
    //erase flag
    private boolean erase = false;


    public DrawingVeiw(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupDrawing();
    }

    private void setupDrawing() {
        //settting the paint and path object and properties
        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }
   //the size for the view
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    //this will draw the view
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0,0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         float touchX, touchY;
         touchX = event.getX();
         touchY = event.getY();
       //up, down
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath,drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        //cretaes the redrawing
        invalidate();
        return true;
    }

    //this updates the colors
    public void setColor(String newColor){
        invalidate();

        //checking if it is a color value
        if (newColor.startsWith("#")){
            paintColor = Color.parseColor(newColor);
            drawPaint.setColor(paintColor);
            drawPaint.setShader(null);

        }else {
            

        }
    }

}

