package com.example.view.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.view.BaseActivity;
import com.example.view.MainActivity;
import com.example.view.R;
import com.example.view.adpter.TagLayoutAdapter;
import com.example.view.group.TagLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StreamingLayoutActivity extends BaseActivity {

    @BindView(R.id.taglayout)
    TagLayout tagLayout;

    private List<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<>();
        items.add("时间");
        items.add("撒娇肯定是实打实的扩大进口圣诞节狂欢");
        items.add("快乐莎萨金葵花卡卡卡就");
        items.add("刷卡大客户的烧烤酱卡到金沙湖");
        items.add("和经济");

        tagLayout.setAdater(new TagLayoutAdapter() {
            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView view = (TextView) LayoutInflater.from(StreamingLayoutActivity.this)
                                .inflate(R.layout.item_tag, parent,false);
                view.setText(items.get(position));
                return view;
            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_streaming_layout;
    }
}
