package com.example.view.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by TheShy on 2018/10/22 18:03.
 * Email:406262584@qq.com
 */
public class RecyclerViewGrideDecration extends RecyclerView.ItemDecoration{

   // private Paint mPaint;
    private Drawable mDrawable;
    private int spanCount;         //GridView每行的列数

    public RecyclerViewGrideDecration(Context mContext, int drawableResourceId, int spanCount) {
       // mPaint = new Paint();
       // mPaint.setAntiAlias(true);
        //mPaint.setColor(Color.RED);
        mDrawable = mContext.getResources().getDrawable(drawableResourceId);
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);

        //  outRect.top = mDrawable.getIntrinsicHeight();
        //  outRect.left = mDrawable.getIntrinsicWidth();

        //GridView 不能简单的取第0个位置了，应该找到第一排的最上边，和左边一列的右边
        //Log.e(">>>>>>>>>>>>>", parent.getChildAdapterPosition(view) + "");
        //因为 parent.getChildCount()是不断变化的，无法判断是否最后一条
        //但是第一条的位置   parent.getChildAdapterPosition(view) 肯定等于0
        //最右边一列
        if((parent.getChildAdapterPosition(view) + 1 ) % spanCount == 0 ) {
            outRect.right = 0;
        }else {
            outRect.right = mDrawable.getIntrinsicWidth();
        }
        //去除第一行
        if(parent.getChildAdapterPosition(view) >= spanCount) {
            outRect.top = mDrawable.getIntrinsicHeight();
        }

    }


    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        horizontalDraw(canvas, parent);
        verticalDraw(canvas, parent);
    }

    /**
     * 画水平分割线
     */
    private void horizontalDraw(Canvas canvas, RecyclerView parent) {
        int left,top,right, bottom;
       /* for(int i = 0; i < parent.getChildCount() ; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params =(RecyclerView.LayoutParams) childView.getLayoutParams();
            left = childView.getLeft() - params.leftMargin;
            top = childView.getBottom() + params.topMargin;
            right = childView.getRight() +  mDrawable.getIntrinsicWidth() + params.rightMargin;
            bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(canvas);
        }*/



        for(int i = spanCount; i < parent.getChildCount() ; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params =(RecyclerView.LayoutParams) childView.getLayoutParams();
            left = childView.getLeft() - params.leftMargin;

            bottom = childView.getTop() - params.topMargin;
            top = bottom - mDrawable.getIntrinsicHeight();
            right = childView.getRight()  + mDrawable.getIntrinsicWidth() + params.rightMargin;

            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(canvas);
        }
    }

    /**
     * 画竖直分割线
     * @param canvas
     * @param parent
     */
    private void verticalDraw(Canvas canvas, RecyclerView parent) {
        int left,top,right, bottom;
        for(int i = 0; i < parent.getChildCount(); i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params =(RecyclerView.LayoutParams) childView.getLayoutParams();
            left = childView.getRight() + params.rightMargin;
            top = childView.getTop() - params.topMargin;
            right = left + mDrawable.getIntrinsicWidth();
            bottom = childView.getBottom() + params.bottomMargin;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(canvas);
        }
    }

}


