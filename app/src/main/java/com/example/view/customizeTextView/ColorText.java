package com.example.view.customizeTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.view.R;

/**
 * Created by 胡冬 on 2018/9/22.
 */

public class ColorText extends TextView{
    private int normalColor = Color.BLACK;  //默认字体颜色
    private int changeColor;   //滑动时的字体颜色

    private Paint mNormalPaint;
    private Paint mChangePaint;

    private float progress = 0f;

    private Derection mDerection = Derection.LEFT_TO_RIGHT;

    public enum Derection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public ColorText(Context context) {
        this(context, null);
    }

    public ColorText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
        initPaint();
    }

    /**
     * 初始化资源属性
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorText);
        normalColor = array.getColor(R.styleable.ColorText_normalColor, normalColor);
        changeColor = array.getColor(R.styleable.ColorText_changeColor, changeColor);

    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setDirection(Derection mDerection) {
        this.mDerection = mDerection;
    }


    private void initPaint() {
        float textSize = getTextSize();
        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setColor(normalColor);
        mNormalPaint.setTextSize(textSize);

        mChangePaint = new Paint();
        mChangePaint.setAntiAlias(true);
        mChangePaint.setColor(changeColor);
        mChangePaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        if(mDerection == Derection.LEFT_TO_RIGHT) {
            int middle = (int)(progress * getWidth());
            drawText(canvas, mChangePaint, 0, middle);
            drawText(canvas, mNormalPaint, middle, getWidth());
        }else {
            int middle = (int)((1 - progress) * getWidth());
            drawText(canvas, mNormalPaint, 0, middle);
            drawText(canvas, mChangePaint, middle, getWidth());
        }

    }


    /**
     * 画文字
     * @param canvas
     * @param paint
     * @param start   裁剪起始区域位置
     * @param end     裁剪结束区域位置
     */
    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        String text = getText().toString();
        float width = paint.measureText(text);
        int dx = (int)((getWidth() - width)/2);
        canvas.clipRect(start,0,end, getHeight());
        canvas.drawText(text, dx, getBaseLine(), paint);
        canvas.restore();

    }

    /**
     * 获取文字基线
     * @return
     */
    private int getBaseLine() {
        Paint.FontMetricsInt metrics = mNormalPaint.getFontMetricsInt();
        int dy = (metrics.bottom - metrics.top)/2 - metrics.bottom;
        return getHeight()/2 + dy;
    }


    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
    }

    public void setChangeColor(int changeColor) {
        this.changeColor = changeColor;
        mChangePaint.setColor(changeColor);
    }
}
