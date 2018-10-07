package com.example.view.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.customizeListView.ParallaxListView;

public class ListpicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callList();
    }

    private void callList() {
        View headView = LayoutInflater.from(this).inflate(R.layout.head_view, null);
        ImageView imageView = (ImageView) headView.findViewById(R.id.image);
        ImageView icon = (ImageView) headView.findViewById(R.id.icon);

        ParallaxListView listView = (ParallaxListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new String[]{
                        "星期一  和马云洽谈",
                        "星期二  约见李彦宏",
                        "星期三  约见乔布斯",
                        "星期四  降低发动机",
                        "星期五  撒点开始",
                        "星期六  死哦接打手机",
                        ".........",
                }
        );
        listView.addHeaderView(headView);
        listView.setZoomImageView(imageView);
        listView.setIcon(icon);
        listView.setAdapter(adapter);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_listpic;
    }
}
