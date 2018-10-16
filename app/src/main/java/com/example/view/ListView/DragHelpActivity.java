package com.example.view.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.view.BaseActivity;
import com.example.view.R;

import butterknife.BindView;

public class DragHelpActivity extends BaseActivity {

    @BindView(R.id.list_view)
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new String[]{
                        "星期一  和马云洽谈",
                        "星期二  约见李彦宏",
                        "星期三  约见乔布斯",
                        "星期四  降低发动机",
                        "星期五  撒点开始",
                        "星期一  和马云洽谈",
                        "星期二  约见李彦宏",
                        "星期三  约见乔布斯",
                        "星期四  降低发动机",
                        "星期五  撒点开始",
                        "星期一  和马云洽谈",
                        "星期二  约见李彦宏",
                        "星期三  约见乔布斯",
                        "星期四  降低发动机",
                        "星期五  撒点开始",
                        "星期一  和马云洽谈",
                        "星期二  约见李彦宏",
                        "星期三  约见乔布斯",
                        "星期四  降低发动机",
                        "星期五  撒点开始",
                        "星期一  和马云洽谈",
                        "星期二  约见李彦宏",
                        "星期三  约见乔布斯",
                        "星期四  降低发动机",
                        "星期五  撒点开始",
                        "星期一  和马云洽谈",
                        "星期二  约见李彦宏",
                        "星期三  约见乔布斯",
                        "星期四  降低发动机",
                        "星期五  撒点开始",
                        ".........",
                }
        );
        listView.setAdapter(adapter);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_drag_help;
    }
}
