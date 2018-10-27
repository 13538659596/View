package com.example.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.view.ListView.Animo1Activity;
import com.example.view.ListView.Animo2Activity;
import com.example.view.ListView.BehaviorActivity;
import com.example.view.ListView.ColorTextActivity;
import com.example.view.ListView.DragHelpActivity;
import com.example.view.ListView.ImmersiveActivity;
import com.example.view.ListView.ListpicActivity;
import com.example.view.ListView.PopuViewActivity;
import com.example.view.ListView.QQScrollActivity;
import com.example.view.ListView.QQStepActivirt;
import com.example.view.ListView.RatingBarActivity;
import com.example.view.ListView.RecyclerViewActivity;
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

    @OnClick(R.id.button12)
    public void statuBar() {
        startActivity(new Intent(this, ImmersiveActivity.class));}

    @OnClick(R.id.button13)
    public void studyBehavior() {
        startActivity(new Intent(this, BehaviorActivity.class));}

    @OnClick(R.id.button14)
    public void recycler() {
        startActivity(new Intent(this, RecyclerViewActivity.class));}

    @OnClick(R.id.button15)
    public void animo1() {
        startActivity(new Intent(this, Animo1Activity.class));}

    @OnClick(R.id.button16)
    public void popu() {
        startActivity(new Intent(this, PopuViewActivity.class));}

    @OnClick(R.id.button17)
    public void animo2() {
        startActivity(new Intent(this, Animo2Activity.class));}


}
