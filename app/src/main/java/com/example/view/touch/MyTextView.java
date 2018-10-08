package com.example.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView{

	private static final String TAG = "MyTextView";
	private int count = 0;
	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.e(TAG,"dispatchTouchEvent  ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.e(TAG,"dispatchTouchEvent  ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.e(TAG,"dispatchTouchEvent  ACTION_UP");
				break;
			case MotionEvent.ACTION_CANCEL:
				Log.e(TAG,"dispatchTouchEvent  ACTION_CANCEL");
				break;
			default:
				break;
		}

		// TODO Auto-generated method stub
		//return false;
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.onTouchEvent(event);
		count++;
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.e(TAG,"onTouchEvent  ACTION_DOWN");
				performClick();
				//return true;
				break;
			case MotionEvent.ACTION_MOVE:
				Log.e(TAG,"onTouchEvent  ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.e(TAG,"onTouchEvent  ACTION_UP");
				break;
			case MotionEvent.ACTION_CANCEL:
				Log.e(TAG,"onTouchEvent  ACTION_CANCEL");
				break;
			default:
				break;
		}
		Log.e(TAG, " onTouchEvent  " + count);
		//Log.e(TAG,"onTouchEvent  result  "  + result);
		return true;
		//return super.onTouchEvent(event);
	}

}
