package com.example.view.group;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.LogPrinter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

/**
 * Created by TheShy on 2018/10/16 20:43.
 * Email:406262584@qq.com
 */
public class DargVIewList extends FrameLayout{

    private ViewDragHelper mViewDragHelper;
    private int mMenuHight;
    private View dragView;  //需要拖动的V
    private float startToucthY = 0; //手指按下的的位置
    private boolean isMenuOpen = false;
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
            return dragView == child;
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
           if(top < 0) {
               top = 0;
           }else if(top > mMenuHight) {
               top = mMenuHight;
           }

            return top;
        }

        /*@Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }*/

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //Log.e("===========", "  onViewReleased  " + yvel + "   " + mMenuHight / 2);
            Log.e("===========", "  onViewReleased  " + dragView.getTop() + "   " + mMenuHight / 2);
            if (dragView.getTop() > mMenuHight / 2) {
                mViewDragHelper.settleCapturedViewAt(0, mMenuHight);
                isMenuOpen = true;
            } else {
                mViewDragHelper.settleCapturedViewAt(0, 0);
                isMenuOpen = false;
            }
            invalidate();
        }
    }

    /**
     * 实现这个方法在手指松开是设置子View的位置才会生效
     */
    @Override
      public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //当菜单打开时，直接拦截子View的事件
        if(isMenuOpen) {
            return true;
        }

        //下拉时拦截子View的滚动
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startToucthY = ev.getY();
                //此处调用的目的 事件不拦截，传到子View，子 View消费事件后不再调用自己的
                    //onTouchEvent事件  ViewDragHelper: Ignoring pointerId=0 because ACTION_DOWN was not received
                    // for this pointer before ACTION_MOVE. It likely happened because
                    // ViewDragHelper did not receive all the events in the event stream. 出现事件不完整异常
                mViewDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                //满足子View滑动到顶部 和下拉动作就拦截事件
                if(moveY - startToucthY > 0 && !canChildScrollUp()) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            mMenuHight = getChildAt(0).getMeasuredHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(">>>>>>>>", "onTouchEvent  " + event.getAction());
        mViewDragHelper.processTouchEvent(event);
        return true;
    }


    /**
     * 判断子View是否可以向上滚动，可以滚动返回true
     * @return
     */
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (dragView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) dragView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(dragView, -1) || dragView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(dragView, -1);
        }
    }
}
