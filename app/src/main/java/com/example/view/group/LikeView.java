package com.example.view.group;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.view.R;
import com.example.view.anima.PointEvaluator;

import java.util.Random;

/**
 * Created by TheShy on 2018/10/29 09:54.
 * Email:406262584@qq.com
 */
public class LikeView extends RelativeLayout{

    private int[] imageResource;
    private int mWidth;  //布局的宽高
    private int mHight;

    /**
     * 图片的宽高
     */
    private int mDrawableWidth;
    private int mDrawableHeight;

    private Interpolator[] mInterpolator;

    public LikeView(Context context) {
        this(context, null);
    }

    public LikeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        imageResource = new int[] {R.drawable.pl_blue, R.drawable.pl_red, R.drawable.pl_yellow};

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.pl_blue);
        mDrawableWidth = drawable.getIntrinsicWidth();
        mDrawableHeight = drawable.getIntrinsicHeight();

        mInterpolator = new Interpolator[]{new AccelerateDecelerateInterpolator(),new AccelerateInterpolator(),
                new DecelerateInterpolator(),new LinearInterpolator()};
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            addImageView();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHight = MeasureSpec.getSize(heightMeasureSpec);

    }

    private void addImageView() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);
        imageView.setLayoutParams(params);
        Random random = new Random();
        imageView.setImageResource(imageResource[random.nextInt(imageResource.length)]);
        addView(imageView);
        setAnimator(imageView);
    }


    private void setAnimator(final View view) {
        AnimatorSet set = new AnimatorSet();

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0.3f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.3f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.3f, 1f);
        animatorSet.playTogether(alpha, scaleX, scaleY);
        animatorSet.setDuration(350);
        //animatorSet.start();

        Random random = new Random();
        PointF point0 = new PointF(mWidth / 2 - mDrawableWidth / 2, mHight - mDrawableHeight);
        PointF point1 = new PointF(random.nextInt(mWidth) - mDrawableWidth, random.nextInt(mHight / 2));
        PointF point2 = new PointF(random.nextInt(mWidth) - mDrawableWidth, random.nextInt(mHight / 2) + mHight / 2);
        PointF point3 = new PointF(random.nextInt(mWidth) - mDrawableWidth, 0);

        PointEvaluator evaluator = new PointEvaluator(point1, point2);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(evaluator, point0, point3);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
               PointF p = (PointF)valueAnimator.getAnimatedValue();
               view.setX(p.x);
               view.setY(p.y);
                // 透明度
                float t = valueAnimator.getAnimatedFraction();
                view.setAlpha(1 - t + 0.2f);
            }
        });
        valueAnimator.setDuration(3000);
        // 加一些随机的差值器（效果更炫）
        valueAnimator.setInterpolator(mInterpolator[random.nextInt(mInterpolator.length-1)]);

        set.playSequentially(animatorSet, valueAnimator);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //super.onAnimationEnd(animation);
               // Log.e(">>>>>", "动画结束");
               removeView(view);
            }
        });
    }

}
