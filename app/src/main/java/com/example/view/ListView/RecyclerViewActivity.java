package com.example.view.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.view.R;
import com.example.view.adpter.RecyclerViewAdaper;
import com.example.view.ui.RecyclerViewGrideDecration;
import com.example.view.ui.RecyclerViewListDecration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private List<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initData();
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        mRecyclerView = findViewById(R.id.receycler_view);

    }


    private void initData() {
        items = new ArrayList<>();
        for(int i='A'; i <'z'; i++) {
            items.add("" + (char)i);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button1) {
            //指定显示样式
            /**
             * LinearLayoutManager  ListView的显示样式
             * GridLayoutManager    GridView的显示样式
             */
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(new RecyclerViewAdaper(this, items));
            //添加分隔线
            mRecyclerView.addItemDecoration(new RecyclerViewListDecration(this, R.drawable.divid_item));
        }else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            Log.e(">>>>>>>",  gridLayoutManager.getSpanCount() + " 列数");
            mRecyclerView.setAdapter(new RecyclerViewAdaper(this, items));
            //添加分隔线
            mRecyclerView.addItemDecoration(new RecyclerViewGrideDecration(this, R.drawable.divid_item, gridLayoutManager.getSpanCount()));
        }
    }
}
