package com.example.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.view.ListView.ColorTextActivity;
import com.example.view.ListView.DragHelpActivity;
import com.example.view.ListView.ListpicActivity;
import com.example.view.ListView.QQScrollActivity;
import com.example.view.ListView.QQStepActivirt;
import com.example.view.ListView.RatingBarActivity;
import com.example.view.ListView.ScrollActivity;
import com.example.view.ListView.ShapeActivity;
import com.example.view.ListView.StreamingLayoutActivity;
import com.example.view.ListView.ViewPageActivity;
import com.example.view.touch.TouchActivity;

import butterknife.OnClick;
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.button1)
    public void pullPic() {
        startActivity(new Intent(this, ListpicActivity.class));
    }

    @OnClick(R.id.button2)
    public void  qqStep() {
        startActivity(new Intent(this, QQStepActivirt.class));
    }

    @OnClick(R.id.button3)
    public void  showColorText() {
        startActivity(new Intent(this, ColorTextActivity.class));
    }

    @OnClick(R.id.button4)
    public void changeColor() {
        startActivity(new Intent(this, ViewPageActivity.class));
    }

    @OnClick(R.id.button5)
    public void showShape() {
        startActivity(new Intent(this, ShapeActivity.class));
    }

    @OnClick(R.id.button6)
    public void showRatingBar() {
        startActivity(new Intent(this, RatingBarActivity.class));
    }

    @OnClick(R.id.button7)
    public void streamingLayout() {
        startActivity(new Intent(this, StreamingLayoutActivity.class));}

    @OnClick(R.id.button8)
    public void showTouch() {
        startActivity(new Intent(this, TouchActivity.class));}

    @OnClick(R.id.button9)
    public void scroll() {
        startActivity(new Intent(this, ScrollActivity.class));}

    @OnClick(R.id.button10)
    public void showQQ() {
        startActivity(new Intent(this, QQScrollActivity.class));}

    @OnClick(R.id.button11)
    public void dragHelp() {
        startActivity(new Intent(this, DragHelpActivity.class));}




}
