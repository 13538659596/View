package com.example.view.ListView;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.group.MyScrollView;
import com.example.view.utils.StattuBarUtil;

import butterknife.BindView;

public class ImmersiveActivity extends BaseActivity {

    @BindView(R.id.scroll_view)
    MyScrollView scrollView;

    @BindView(R.id.title_bar)
    LinearLayout titleBar;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.title)
    TextView title;

    private int mTitleBarHight;

    private int mIamgeViewHight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StattuBarUtil.setStatubar(this, getResources().getColor(R.color.blue));
        titleBar.getBackground().setAlpha(0);
        title.setAlpha(0);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                mIamgeViewHight = imageView.getMeasuredHeight();
                mTitleBarHight = titleBar.getMeasuredHeight();
            }
        });
        scrollView.setOnScrollChangeListener(new MyScrollView.ScrollChangeListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {

                float scale =  (float) t /(mIamgeViewHight - mTitleBarHight);
                if(scale > 1) {
                    scale = 1;
                }else if(scale < 0) {
                    scale = 0;
                }
                Log.e("============",  "scale  "  + scale );
                titleBar.getBackground().setAlpha((int)(scale * 255));
                title.setAlpha((int)(scale * 255));
            }
        });


    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_immersive;
    }


}
