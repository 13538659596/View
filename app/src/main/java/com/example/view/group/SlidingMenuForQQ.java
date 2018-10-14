package com.example.view.group;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.view.R;
import com.example.view.utils.ScreenUtils;

/**
 * Created by TheShy on 2018/10/10 19:08.
 * Email:406262584@qq.com
 */
public class SlidingMenuForQQ extends HorizontalScrollView{
    private  int menuWidth; //菜单页的宽度
    private View contentView, menu;
    private GestureDetector gestureDetector;
    private boolean menuIsOpen = false;
    private boolean interception = false;   //处理了快速滑动事件后，直接拦截onTouchEvent事件
    public SlidingMenuForQQ(Context context) {
        this(context, null);
    }

    public SlidingMenuForQQ(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenuForQQ(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SlidingMenu);

        int rightMargin =typedArray.getDimensionPixelOffset(R.styleable.
                    SlidingMenu_menuRightMargin, ScreenUtils.dip2px(context,50));

        menuWidth = ScreenUtils.getScreenWidth(context) - rightMargin;
        typedArray.recycle();

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e("========","快速滑动" +  velocityX + " x 方向滑动距离 " + (e1.getX() - e2.getX()));
                if(Math.abs(e1.getX() - e2.getX()) > 80) {
                    if(menuIsOpen && velocityX < 0 ) {
                        closeMenu();
                        return true;
                    }else if(!menuIsOpen && velocityX > 0) {
                        openMenu();
                        return true;
                    }
                }

                return super.onFling(e1, e2, velocityX, velocityY);

            }
        });
    }


    //布局解析完毕会调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //指定宽度
        //获取外层Linearlayout
        ViewGroup container = (ViewGroup) getChildAt(0);
        if(container.getChildCount() != 2) {
            throw new RuntimeException("only allowed put one layout");
        }
        //左边的menu页
        menu = container.getChildAt(0);
        ViewGroup.LayoutParams params = menu.getLayoutParams();
        params.width = menuWidth;
        menu.setLayoutParams(params);

        //右边的内存页
        contentView = container.getChildAt(1);
        ViewGroup.LayoutParams parm = contentView.getLayoutParams();
        parm.width = ScreenUtils.getScreenWidth(getContext());
        contentView.setLayoutParams(parm);

        //此方法没用,因为onFinishInflate的调用在onLayout()方法之前，在调用onLayout时会恢复原样
        //scrollTo(RightMargin, 0);
    }

    //出事进入默认关闭Menu页
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(menuWidth, 0);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        interception = false;
        if(menuIsOpen) {
            if(ev.getX() > menuWidth) {
                closeMenu();
                interception = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if(interception) {
            return true;
        }

        if(gestureDetector.onTouchEvent(ev)) {
            //快速滑动处理事件，就不继续处理了
            return true;
        }

        if(ev.getAction() == MotionEvent.ACTION_UP) {
            //根据滚动距离判断是关闭还是打开Menu
            int moveX = getScrollX();     //获取怎么View的滚动距离
            //Log.e("=========", moveX + "     " + MenuWidth/2);
            if(moveX > menuWidth / 2) {
                closeMenu();
            }else {
                openMenu();
            }
            //super的onTouchEvent 的UP时间 flingWithNestedDispatch(-initialVelocity);
            //掉用到fling()方法，  mScroller.fling(mScrollX, mScrollY, 0, velocityY, 0, 0, 0,
            //                    Math.max(0, bottom - height), 0, height/2); 与SmootScroll冲突
            return true;
        }
        return super.onTouchEvent(ev);
    }


    public void closeMenu() {
        Log.e("========", "closeMenu  ");
        smoothScrollTo(menuWidth, 0);
        menuIsOpen = false;
    }

    public void openMenu() {
        smoothScrollTo(0,0);
        menuIsOpen = true;
        Log.e("========", "openMenu  ");
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

      /*  contentView.getLayoutParams().width = ScreenUtils.ge(getContext()) - l * 2;
        contentView.requestLayout();*/

      //算梯度值
        float scale = (float) l  / menuWidth; //从1变化到0
        //滑到右边最小的缩放 默认0.7
        float rightScale = 0.7f + 0.3f * scale;
        //Log.e("========", "rightScale  " + rightScale);

        //设置缩放中心点位置
        /*ViewCompat.setPivotX(contentView, 0);
        ViewCompat.setPivotY(contentView,contentView.getMeasuredHeight() / 2);
        ViewCompat.setScaleX(contentView, rightScale);
        ViewCompat.setScaleY(contentView, rightScale);*/

        //透明度
       /* float alpha = 0.2f + 0.8f * (1 - scale);
        menu.setAlpha(alpha);

        float leftScale = 0.7f + 0.3f * (1 - scale);
        ViewCompat.setScaleX(menu, leftScale);
        ViewCompat.setScaleY(menu, leftScale);*/
        ViewCompat.setTranslationX(menu, 0.7f * l );
    }
}
