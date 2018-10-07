package com.example.view.customizeListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.view.R;

public class ParallaxListView extends ListView{

	private ImageView mImageView;
	private ImageView icon;
	private int mImageViewHight;
	public ParallaxListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mImageViewHight = context.getResources().getDimensionPixelSize(R.dimen.defualt_hight);
		// TODO Auto-generated constructor stub
	}

	public void setZoomImageView(ImageView mImageView) {
		this.mImageView = mImageView;
		//mImageViewHight = mImageView.getHeight();
	}

	public void setIcon(ImageView icon) {
		this.icon = icon;
		//mImageViewHight = mImageView.getHeight();
	}





	/**
	 * 监听滑动控件过度滑动
	 */
	@SuppressLint("NewApi")
	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
								   int scrollY, int scrollRangeX, int scrollRangeY,
								   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		// TODO Auto-generated method stub
		//deltax,deltaY 增量
		/**
		 * deltaY
		 * -:下拉过度
		 * +:上拉过度
		 */
		Log.e(">>>>>>", deltaY + "");
		//下拉放大图片
		if(deltaY < 0) {
			mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
			mImageView.requestLayout();
			icon.setRotation(icon.getRotation() - deltaY);
		}else {
			//缩小
			if(mImageView.getHeight() > mImageViewHight) {
				mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
				mImageView.requestLayout();
				icon.setRotation(icon.getRotation() - deltaY);
			}

		}

		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
				scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
	}


	@SuppressLint("NewApi")
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// 让ImageView 在上划过程中缩小
		View parent = (View)mImageView.getParent();
		if(parent.getTop() < 0 && mImageView.getHeight() > mImageViewHight) {
			mImageView.getLayoutParams().height = mImageView.getHeight() + parent.getTop();
			icon.setRotation(icon.getRotation() + parent.getTop());
			parent.layout(parent.getLeft(), 0, parent.getRight(), parent.getHeight());
			mImageView.requestLayout();
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if(ev.getAction() == MotionEvent.ACTION_UP) {
			ResetAnimation animation = new ResetAnimation(mImageView, mImageViewHight);
			animation.setDuration(300);
			mImageView.startAnimation(animation);
		}


		return super.onTouchEvent(ev);
	}


	public class ResetAnimation extends Animation {
		private ImageView iv;
		private int targeHight;  //原始高度
		private int orginalHeight;
		private int extraHeight;

		public ResetAnimation(ImageView iv, int targeHight) {
			this.iv = iv;
			this.targeHight = targeHight;
			orginalHeight = iv.getHeight();
			this.extraHeight = orginalHeight - targeHight;
		}
		@Override
		protected void applyTransformation(float interpolatedTime,
										   Transformation t) {

			//interpolatedTime(0.0 to 1.0)  执行的百分比

			iv.getLayoutParams().height = (int)(orginalHeight - extraHeight*interpolatedTime);
			iv.requestLayout();
			super.applyTransformation(interpolatedTime, t);
		}
	}

}
