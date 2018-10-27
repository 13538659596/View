package com.example.view.group;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.view.customizeTextView.CircleView;

/**
 * Created by TheShy on 2018/10/27 10:05.
 * Email:406262584@qq.com
 */
public class LoadingView extends RelativeLayout{

    private CircleView leftView,midView,rightView;

    private int translatDistance;  //移动距离
    private final int DURATION = 350;


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        leftView = creatCircleView(context, Color.RED);
        midView = creatCircleView(context, Color.GREEN);
        rightView = creatCircleView(context, Color.BLUE);
        addView(leftView);
        addView(rightView);
        addView(midView);
        translatDistance = dip2px(24);
        separate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private CircleView creatCircleView(Context context, int color) {
        CircleView view = new CircleView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dip2px(10), dip2px(10));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(params);
        view.setViewColor(color);
        return view;
    }


    /**
     * View分开动画
     */
    private void separate() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(leftView, "translationX", 0, -translatDistance);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(rightView, "translationX", 0, translatDistance);
        DecelerateInterpolator interpolator = new DecelerateInterpolator();
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(interpolator);
        set.playTogether(animator1, animator2);
        set.setDuration(DURATION);

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                close();
            }
        });
        set.start();
    }

    /**
     * 交换颜色左边给中间，中间给右边，右边给左边
     */
    private void exchangeColor() {
        int tempColor = leftView.getmColor();
        leftView.setViewColor(midView.getmColor());
        midView.setViewColor(rightView.getmColor());
        rightView.setViewColor(tempColor);

    }


    /**
     * View合拢动画
     */
    private void close() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(leftView, "translationX",  -translatDistance, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(rightView, "translationX",  translatDistance, 0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1, animator2);
        set.setDuration(DURATION);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //交换颜色
                exchangeColor();
                separate();
            }
        });
        set.start();
    }

    private int dip2px(int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
