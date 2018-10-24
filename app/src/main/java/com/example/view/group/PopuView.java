package com.example.view.group;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.R;
import com.example.view.adpter.BaseMenuAdapter;

/**
 * Created by TheShy on 2018/10/24 11:05.
 * Email:406262584@qq.com
 */
public class PopuView extends LinearLayout implements View.OnClickListener{

    private LinearLayout tabView; //存放标题栏

    // 1.2 创建 FrameLayout 用来存放 = 阴影（View） + 菜单内容布局(FrameLayout)
    private FrameLayout mMenuMiddleView;
    // 阴影
    private View mShadowView;
    //内容布局
    private FrameLayout mMenuContainerView;
    private Context mContext;
    private boolean isMenuOpen = false;  //菜单栏是否弹出

    private int mMenuContentHeight; //弹出的内容页高度
    public PopuView(Context context) {
        this(context, null);
    }

    public PopuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initLayout();
    }


    private void initLayout() {
        //设置摆放方式
        setOrientation(VERTICAL);
        //创建头部菜单栏
        tabView = new LinearLayout(mContext);
        tabView.setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.
                MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabView.setLayoutParams(params);
        addView(tabView);

        //创建内容+阴影布局
        mMenuMiddleView = new FrameLayout(mContext);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
               0);
        params1.weight = 1;
        mMenuMiddleView.setLayoutParams(params1);
        //mMenuMiddleView.setBackgroundColor(getResources().getColor(R.color.rect));

        //创建阴影View
        // 创建阴影 可以不用设置 LayoutParams 默认就是 MATCH_PARENT ，MATCH_PARENT
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(Color.parseColor("#99000000"));
        mShadowView.setAlpha(0);
        mMenuMiddleView.addView(mShadowView);


        mMenuContainerView = new FrameLayout(mContext);
        mMenuContainerView.setBackgroundColor(Color.WHITE);
        mMenuMiddleView.addView(mMenuContainerView);
        addView(mMenuMiddleView);
    }

    /**
     * adapter的设计模式
     * @param adapter
     */
    public void setAdapter(BaseMenuAdapter adapter) {
        int childCount = adapter.getCount();
        for (int i = 0; i < childCount; i++) {
            View childView =  adapter.getTabView(i, tabView);
            tabView.addView(childView);
            childView.setPadding(0, dip2px(10), 0, dip2px(10));
            LinearLayout.LayoutParams params = (LayoutParams) childView.getLayoutParams();
            params.weight = 1;
            childView.setLayoutParams(params);
            setTagClick(childView, i);

            //添加的所有的内容页，会堆起来
            View contentView = adapter.getMenuView(i, mMenuContainerView);
            //首先把内容隐藏，点击Tab菜单时再显示
            contentView.setVisibility(GONE);
            mMenuContainerView.addView(contentView);
        }
    }

    private void setTagClick(View childView, final int position) {
        childView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isMenuOpen) {

                }else {
                    openMenu(position);
                }
            }
        });
    }


    private void openMenu(int position) {
        mShadowView.setVisibility(View.VISIBLE);
        // 获取当前位置显示当前菜单，菜单是加到了菜单容器
        View menuView = mMenuContainerView.getChildAt(position);
        menuView.setVisibility(View.VISIBLE);

        // 打开开启动画  位移动画  透明度动画
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", -mMenuContentHeight, 0);
        translationAnimator.setDuration(300);
        translationAnimator.start();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 0f, 1f);
        alphaAnimator.setDuration(300);

        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //mAnimatorExecute = false;
               // mCurrentPosition = position;
            }

            @Override
            public void onAnimationStart(Animator animation) {
               // mAnimatorExecute = true;
                // 把当前的 tab 传到外面
               // mAdapter.menuOpen(tabView);
            }
        });
        alphaAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        // 内容的高度应该不是全部  应该是整个 View的 75%
        mMenuContentHeight = (int) (height * 75f / 100);
        ViewGroup.LayoutParams params = mMenuContainerView.getLayoutParams();
        params.height = mMenuContentHeight;
        mMenuContainerView.setLayoutParams(params);
        mMenuContainerView.setTranslationY(- mMenuContentHeight);
        //隐藏阴影和内容，把布局移上去
    }



    private int dip2px(int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mContext, "点击" , Toast.LENGTH_SHORT).show();
    }
}
