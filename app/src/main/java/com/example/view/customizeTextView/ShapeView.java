package com.example.view.customizeTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.view.R;

/**
 * Created by 胡冬 on 2018/9/23.
 */

public class ShapeView extends View{
    private Paint mPaint;
    private Shape currentShape = Shape.CIRCLE;  //默认画圆形
    private  Path mPath;
    public enum Shape {
        CIRCLE,
        RECT,
        TRIANGLE
    }

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        mPath = new Path();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int hightSize = MeasureSpec.getSize(heightMeasureSpec);

        int size = widthSize > hightSize ? hightSize : widthSize;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        switch (currentShape) {
            case CIRCLE:
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.circle));
                canvas.drawArc(rect, 0, 360, false, mPaint);
                break;
            case RECT:
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.rect));
                canvas.drawRect(rect, mPaint);
                break;
            case TRIANGLE:
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.triangle));
                //将等腰三角形画成等边三角形
               /* mPath.moveTo(getMeasuredWidth() / 2, 0);
                mPath.lineTo(0,getMeasuredHeight());
                mPath.lineTo(getMeasuredWidth(), getMeasuredHeight());
                mPath.close();*/
                mPath.moveTo(getMeasuredWidth() / 2, 0);
                float hight = (float) Math.sqrt(3) * getMeasuredWidth() / 2;
                mPath.lineTo(0, hight);
                mPath.lineTo(getMeasuredWidth(), hight);
                mPath.close();
                canvas.drawPath(mPath, mPaint);
                break;
        }
    }


    public void changeShape() {
        switch (currentShape) {
            case CIRCLE:
               currentShape = Shape.RECT;
                break;
            case RECT:
                currentShape = Shape.TRIANGLE;
                break;
            case TRIANGLE:
                currentShape = Shape.CIRCLE;
                break;
        }
        invalidate();
    }

    public Shape getCurrentShape() {
        return currentShape;
    }
}
