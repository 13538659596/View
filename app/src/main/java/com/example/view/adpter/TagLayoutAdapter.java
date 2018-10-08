package com.example.view.adpter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TheShy on 2018/10/8 15:00.
 * Email:406262584@qq.com
 */
public abstract class TagLayoutAdapter {

    //获取子view的个数
    public abstract int getCount();

    //通过position获取子View
    public abstract View getView(int position, ViewGroup parent);
}
