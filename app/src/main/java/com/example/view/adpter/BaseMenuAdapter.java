package com.example.view.adpter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by TheShy on 2018/10/24 11:14.
 * Email:406262584@qq.com
 */
public abstract class BaseMenuAdapter {

    // 获取总共有多少条
    public abstract int getCount();
    // 获取当前的TabView
    public abstract View getTabView(int position, ViewGroup parent);
    // 获取当前的菜单内容
    public abstract View getMenuView(int position,ViewGroup parent);

    /**
     * 菜单打开
     * @param tabView
     */
    public void menuOpen(View tabView) {

    }

    /**
     * 菜单关闭
     * @param tabView
     */
    public void menuClose(View tabView) {

    }
}
