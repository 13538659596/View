package com.example.view.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by TheShy on 2018/10/22 18:03.
 * Email:406262584@qq.com
 */
public class RecyclerViewListDecration extends RecyclerView.ItemDecoration{

   // private Paint mPaint;
    private Drawable mDrawable;

    public RecyclerViewListDecration(Context mContext, int drawableResourceId) {
       // mPaint = new Paint();
       // mPaint.setAntiAlias(true);
        //mPaint.setColor(Color.RED);
        mDrawable = mContext.getResources().getDrawable(drawableResourceId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);

        Log.e(">>>>>>>>>>>>>", parent.getChildAdapterPosition(view) + "");
        //因为 parent.getChildCount()是不断变化的，无法判断是否最后一条
        //但是第一条的位置   parent.getChildAdapterPosition(view) 肯定等于0
        if(parent.getChildAdapterPosition(view) != 0) {
            outRect.top = mDrawable.getIntrinsicHeight();
        }

    }


    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        //super.onDraw(c, parent, state);
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();

        for(int i = 1; i < parent.getChildCount(); i++) {
            rect.bottom = parent.getChildAt(i).getTop();
            rect.top = rect.bottom - mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(rect);
            mDrawable.draw(canvas);
        }
    }
}
