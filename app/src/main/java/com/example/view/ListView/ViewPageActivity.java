package com.example.view.ListView;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.view.R;
import com.example.view.customizeTextView.ColorText;
import com.example.view.fragment.ItemFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends FragmentActivity {

    private String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华"};
    private LinearLayout mIndicatorContainer;// 变成通用的
    private List<ColorText> mIndicators;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        mIndicators = new ArrayList<>();
        mIndicatorContainer =  findViewById(R.id.indicator_view);
        mViewPager =  findViewById(R.id.view_pager);
        initIndicator();
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("TAG","position -> "+position +"  positionOffset -> "+positionOffset);
                // position 代表当前的位置
                // positionOffset 代表滚动的 0 - 1 百分比

                // 1.左边  位置 position
                ColorText left = mIndicators.get(position);
                left.setDirection(ColorText.Derection.RIGHT_TO_LEFT);
                left.setProgress(1 - positionOffset);

                try {
                    ColorText right = mIndicators.get(position + 1);
                    right.setDirection(ColorText.Derection.LEFT_TO_RIGHT);
                    right.setProgress(positionOffset);
                }catch (Exception e){

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化可变色的指示器
     */
    private void initIndicator() {
        for (int i = 0; i < items.length; i++) {
            // 动态添加颜色跟踪的TextView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorText colorTextView = new ColorText(this);
            // 设置颜色
            colorTextView.setTextSize(30);
            colorTextView.setChangeColor(Color.RED);
            colorTextView.setText(items[i]);
            colorTextView.setLayoutParams(params);
            // 把新的加入LinearLayout容器
            mIndicatorContainer.addView(colorTextView);
            // 加入集合
            mIndicators.add(colorTextView);
        }
    }
}
