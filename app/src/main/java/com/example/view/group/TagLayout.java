package com.example.view.group;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.view.adpter.TagLayoutAdapter;

public class TagLayout extends ViewGroup{

	private List<List<View>> mChildViews = new ArrayList<List<View>>();
	private TagLayoutAdapter mAdapter;

	public TagLayout(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub

	}
	public TagLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		mChildViews.clear();
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	     int lineWidth = getPaddingLeft();

	     int width = MeasureSpec.getSize(widthMeasureSpec);
	     int hight = getPaddingTop() + getPaddingBottom();
	     int maxChildLineHight = 0;

		//1.循环测量子View的宽,高
		final int count = getChildCount();
		Log.e(">>>>>", "child count " + count);
		ArrayList<View> chidViews = new ArrayList<View>();
		for(int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			//获取子view的margin
			ViewGroup.MarginLayoutParams params = (MarginLayoutParams)child.getLayoutParams();
			  measureChild(child, widthMeasureSpec, heightMeasureSpec);
			  if(lineWidth + child.getMeasuredWidth() + params.leftMargin + params.rightMargin> width) {
				  //换行
				  Log.e("------->", "================================");
				  hight += maxChildLineHight;   //高度叠加每行View的最高高度
				  mChildViews.add(chidViews);
				  lineWidth = 0;
				  maxChildLineHight = 0;
				  chidViews = new ArrayList<>();
			  }
			if(i == count - 1) {
			  	//添加最后一行
				mChildViews.add(chidViews);
			}
			lineWidth += child.getMeasuredWidth()+ params.leftMargin + params.rightMargin;
			chidViews.add(child);
			maxChildLineHight = Math.max(child.getMeasuredHeight()+ params.topMargin + params.bottomMargin, maxChildLineHight);
		}
		hight += maxChildLineHight;
		setMeasuredDimension(width, hight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		// TODO Auto-generated method stub
		int left = getPaddingLeft();
		int right = 0;
		int top = getPaddingTop();
		int bottom = 0;
		Log.e("------->", "行数  "  + mChildViews.size());
		for(List<View> childViews : mChildViews) {
			left = getPaddingLeft();
			int lineMaxHight = 0;
			for(View childView : childViews) {
				//获取子view的margin
				ViewGroup.MarginLayoutParams params = (MarginLayoutParams)childView.getLayoutParams();
				left += params.leftMargin;
				int childTop = top + params.topMargin;
				right = left + childView.getMeasuredWidth();
				bottom = childTop + childView.getMeasuredHeight();
				childView.layout(left, childTop, right, bottom);
				left += childView.getMeasuredWidth() + params.leftMargin;

				//获取没行最高的子view的高度
				int childHight = childView.getMeasuredHeight() + params.bottomMargin + params.topMargin;
				lineMaxHight = Math.max(childHight, lineMaxHight);
			}
			top += lineMaxHight;
		}
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

	public void setAdater(TagLayoutAdapter adapter) {
	    if(adapter == null) {
	        return;
        }
        //先清空容器中所有view，在循环添加
        removeAllViews();
        mAdapter = null;
        mAdapter = adapter;

	    int childViewCount = mAdapter.getCount();
	    for (int i = 0; i < childViewCount; i++) {
            View childView = mAdapter.getView(i, this);
            addView(childView);
        }
    }
}
