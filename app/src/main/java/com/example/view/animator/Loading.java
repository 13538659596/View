package com.example.view.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.view.R;
import com.example.view.customizeTextView.ShapeView;

import java.security.KeyStore;

/**
 * Created by TheShy on 2018/10/23 10:52.
 * Email:406262584@qq.com
 */
public class Loading extends LinearLayout{

    private ShapeView shapView;
    private View ShadowView;
    private final int DURATION_TIME = 350;
    private boolean isFinihAnimator = false;    //结束动画
    public Loading(Context context) {
        this(context, null);
    }

    public Loading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        inflate(getContext(), R.layout.loading_view, this);
        shapView = findViewById(R.id.shape_view);
        ShadowView = findViewById(R.id.shadow_view);
        fallAnimo();
    }


    private void fallAnimo() {
        if(isFinihAnimator) {
            return;
        }
        ObjectAnimator fall = ObjectAnimator.ofFloat(shapView, "translationY", 0, dip2px(80));
        fall.setDuration(DURATION_TIME);
        ObjectAnimator scall = ObjectAnimator.ofFloat(ShadowView, "scaleX", 1.0f, 0.3f);
        scall.setDuration(DURATION_TIME);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateInterpolator(1.5f));
        //动画一起执行
        animatorSet.playTogether(fall, scall);
        animatorSet.start();
        //先后执行
        //animatorSet.playSequentially();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
               // super.onAnimationEnd(animation);
                shapView.changeShape();
                upAnimo();

        }
        });
    }

    private void upAnimo() {
        if(isFinihAnimator) {
            return;
        }
        ObjectAnimator fall = ObjectAnimator.ofFloat(shapView, "translationY", dip2px(80), 0);
        fall.setDuration(DURATION_TIME);
        ObjectAnimator scall = ObjectAnimator.ofFloat(ShadowView, "scaleX", 0.3f, 1f);
        scall.setDuration(DURATION_TIME);
        AnimatorSet animatorSet = new AnimatorSet();
        //给动画设置加速插值器
        animatorSet.setInterpolator(new DecelerateInterpolator());
        //动画一起执行
        animatorSet.playTogether(fall, scall);

        //先后执行
        //animatorSet.playSequentially();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // super.onAnimationEnd(animation);
                //shapView.changeShape();
                Log.e(">>>>>>>>>", "  onAnimationEnd" );
                fallAnimo();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                //super.onAnimationStart(animation);
                Log.e(">>>>>>>>>", " " + shapView.getCurrentShape());
                rotationAimator();
            }
        });
        animatorSet.start();
    }

    private void rotationAimator() {
        int oritation = 0;
        switch (shapView.getCurrentShape()) {
            case RECT:
                oritation = 180;
                break;
            case TRIANGLE:
                oritation = 120;
                break;
        }
        Log.e(">>>>>>>>>", " 选择角度" + oritation);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(shapView, "rotation", 0, oritation);
        rotation.setInterpolator(new DecelerateInterpolator());
        rotation.setDuration(DURATION_TIME);
        rotation.start();
    }

    private int dip2px(int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(View.INVISIBLE);
        ViewGroup viewGroup =(ViewGroup) getParent();
        if(viewGroup != null) {
            viewGroup.removeView(this);
        }
        removeAllViews();
        isFinihAnimator = true;
    }

}
