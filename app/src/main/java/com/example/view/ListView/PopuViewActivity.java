package com.example.view.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.adpter.MenuAdapater;
import com.example.view.group.PopuView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PopuViewActivity extends BaseActivity {


    @BindView(R.id.popu_view)
    PopuView popuView;

    private List<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<>();
        items.add("类型");
        items.add("品牌");
        items.add("价格");
        items.add("颜色");
        items.add("尺寸");
        items.add("更多");
        MenuAdapater adapater = new MenuAdapater(this, items);
        popuView.setAdapter(adapater);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_popu_view;
    }

}
