package com.example.view.ListView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.view.BaseActivity;
import com.example.view.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ScrollActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_scroll;
    }

    @OnClick(R.id.test_button)
    public void showToast() {
        Toast.makeText(this, "按钮点击事件", Toast.LENGTH_SHORT).show();}
}
