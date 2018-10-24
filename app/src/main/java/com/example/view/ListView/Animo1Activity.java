package com.example.view.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.view.BaseActivity;
import com.example.view.R;
import com.example.view.animator.Loading;

import butterknife.BindView;

public class Animo1Activity extends BaseActivity {

    @BindView(R.id.loading_view)
    Loading loadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadingView.setVisibility(View.GONE);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_animo1;
    }
}
