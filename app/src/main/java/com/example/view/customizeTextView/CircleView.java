package com.example.view.customizeTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by TheShy on 2018/10/27 10:07.
 * Email:406262584@qq.com
 */
public class CircleView extends View {

    private Paint mPaint;
    private int mColor;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int hight = getHeight() / 2;
        int min = width > hight ? hight:width;
        canvas.drawCircle(min, min, min, mPaint);

    }

    public void setViewColor(int color) {
        mColor = color;
        mPaint.setColor(mColor);
        invalidate();
    }

    public int getmColor() {
        return mColor;
    }
}
