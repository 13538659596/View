package com.example.view.ListView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.customizeTextView.ColorText;

import butterknife.BindView;
import butterknife.OnClick;

public class ColorTextActivity extends BaseActivity {

    @BindView(R.id.textView)
    ColorText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_color_text;
    }

    @OnClick(R.id.button1)
    public void  leftToRight() {
        textView.setDirection(ColorText.Derection.LEFT_TO_RIGHT);
        setAnima();
    }

    @OnClick(R.id.button2)
    public void rightToLeft() {
        textView.setDirection( ColorText.Derection.RIGHT_TO_LEFT);
        setAnima();
    }

    private void setAnima() {
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float)animation.getAnimatedValue();
                textView.setProgress(progress);
            }
        });
        valueAnimator.start();
    }
}
