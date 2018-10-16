package com.example.view.group;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.LogPrinter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by TheShy on 2018/10/16 20:43.
 * Email:406262584@qq.com
 */
public class DargVIewList extends FrameLayout{

    private ViewDragHelper mViewDragHelper;
    public DargVIewList(@NonNull Context context) {
        this(context, null);
    }

    public DargVIewList(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DargVIewList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, 10f, new ViewDragCallback());
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {

        /**
         * 尝试捕获子view，一定要返回true
         *
         * @param child     尝试捕获的view
         * @param pointerId 指示器id？
         *                  这里可以决定哪个子view可以拖动
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        /**
         * 处理竖直方向上的拖动
         *
         * @param child 被拖动到view
         * @param top   移动到达的y轴的距离
         * @param dy    建议的移动的y距离
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            // 两个if主要是为了让viewViewGroup里
           /* if (getPaddingTop() > top) {
                return getPaddingTop();
            }
            if(getHeight() - child.getHeight() < top) {
                return getHeight() - child.getHeight();
            }*/

            return top;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(">>>>>>>>", "onTouchEvent  " + event.getAction());
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
