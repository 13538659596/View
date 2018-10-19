package com.example.view.ListView;


import android.os.Bundle;
import android.view.View;
import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.group.MyScrollView;
import com.example.view.utils.StattuBarUtil;

import butterknife.BindView;

public class ImmersiveActivity extends BaseActivity {

    @BindView(R.id.scroll_view)
    MyScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StattuBarUtil.setStatubar(this, getResources().getColor(R.color.blue));

        scrollView.setOnScrollChangeListener(new MyScrollView.ScrollChangeListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {

            }
        });


    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_immersive;
    }


}
