package com.example.view.ListView;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.customizeTextView.ShapeView;

import butterknife.BindView;

public class ShapeActivity extends BaseActivity {

    @BindView(R.id.shape_view)
    ShapeView shapeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        shapeView.changeShape();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shape;
    }
}
