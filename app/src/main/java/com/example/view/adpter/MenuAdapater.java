package com.example.view.adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.view.R;

import java.util.List;

/**
 * Created by TheShy on 2018/10/24 11:26.
 * Email:406262584@qq.com
 */
public class MenuAdapater extends BaseMenuAdapter{

    private List<String> items;
    private Context context;
    public MenuAdapater(Context context, List<String> items) {
        this.items = items;
        this.context = context;
    }
    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public TextView getTabView(int position, ViewGroup parent) {
        TextView t = new TextView(context);
        t.setTextSize(16);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(Color.BLACK);
        t.setText(items.get(position));
        return t;
    }

    @Override
    public View getMenuView(int position, ViewGroup parent) {
        // 真正开发过程中，不同的位置显示的布局不一样
        TextView menuView = (TextView) LayoutInflater.from(context).inflate(R.layout.ui_list_data_screen_menu,parent,false);
        menuView.setText(items.get(position));
        return menuView;
    }
}
