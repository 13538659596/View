package com.example.view.customizeTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.view.R;

/**
 * Created by 胡冬 on 2018/9/24.
 */

public class RatingBarView extends View {

    private Bitmap normalStart, selectStart;
    private int startNumber;
    private int rating = 0; //评分
    public RatingBarView(Context context) {
        this(context, null);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        int resourceIdNormal = array.getResourceId(R.styleable.RatingBarView_normalPic, R.drawable.star_normal);
        int resourceIdSelect = array.getResourceId(R.styleable.RatingBarView_selectPic, R.drawable.star_selected);
        startNumber = array.getInt(R.styleable.RatingBarView_startNumber, 5);
        normalStart = BitmapFactory.decodeResource(getResources(), resourceIdNormal);
        selectStart = BitmapFactory.decodeResource(getResources(), resourceIdSelect);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int hight = normalStart.getHeight();
        int wdith = normalStart.getWidth() * startNumber;
        setMeasuredDimension(wdith, hight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(">>>>>", " onDraw  " );
        super.onDraw(canvas);
        int x = 0;
        for(int i = 0; i < startNumber; i++) {
            if(i < rating) {
                canvas.drawBitmap(selectStart, x, 0, null);
            }else {
                canvas.drawBitmap(normalStart, x, 0, null);
            }
            x += normalStart.getWidth();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int position = (int)(event.getX() / normalStart.getWidth()) + 1;

        switch (event.getAction()) {

            //case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:
                Log.e(">>>>>>>>>", "ACTION_MOVE");
            //case MotionEvent.ACTION_UP:
                changeRating(position);
                break;
        }
        return super.onTouchEvent(event);
    }

    private void changeRating(int rating) {
        //减少onMove时频繁调用onDraw()
        if(this.rating != rating) {
            this.rating = rating;
            invalidate();
        }

    }


}
