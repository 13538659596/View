package com.example.view.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.view.R;

/**
 * Created by TheShy on 2018/10/19 08:39.
 * Email:406262584@qq.com
 */
public class StattuBarUtil {

    public static void setStatubar(Activity ac, int color) {
        //phoneWindow
        Window window = ac.getWindow();
        //5.1 系统提供了设置状态栏的方法
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            View decorView = window.getDecorView();
            //设置全屏
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);

        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //4.4到5.1系统没有提供setStatusBarColor该方法
           /* 实现方式      1.把window设置全屏
                            2.在转态栏的部分添加一个View*/
            //这种设置全屏的模式会把状态栏也遮挡
            //window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
           /* ViewGroup decorView = (ViewGroup) window.getDecorView();

            int statuBarHight = getStatusBarHeight(ac);
            //View会遮挡内容页,可以在布局文件的根布局添加  android:fitsSystemWindows="true"
            View view = new View(ac);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statuBarHight);
            view.setLayoutParams(params);
            //view.setBackgroundColor(color);


            decorView.addView(view);

            ViewGroup contentView = decorView.findViewById(android.R.id.content);
            //第一种方式
          *//* View acticityView = contentView.getChildAt(0);
             acticityView.setFitsSystemWindows(true);*//*


            //第二种方式
            contentView.setPadding(0, statuBarHight, 0, 0);*/
        }
    }


    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

}
