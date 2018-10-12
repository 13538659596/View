package com.example.view.touch;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.R;

public class TouchActivity extends Activity implements OnClickListener, OnTouchListener{

	private static final String TAG = "MainActivity";
	private TextView myTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch);
		myTextView = (TextView)findViewById(R.id.myTextView);
		myTextView.setOnClickListener(this);
		//myTextView.setOnTouchListener(this);

	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()) {
			case R.id.myTextView:
				Log.e(TAG, "MyTextView onclick");
				Toast.makeText(this, "点击事件", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}

	}
	@Override
	public boolean onTouch(View view, MotionEvent env) {
		// TODO Auto-generated method stub
		switch(view.getId()) {
			case R.id.myTextView:
				switch(env.getAction()) {
					case MotionEvent.ACTION_DOWN:
						Log.e(TAG,"onTouch  ACTION_DOWN");
						break;
					case MotionEvent.ACTION_MOVE:
						Log.e(TAG,"onTouch  ACTION_MOVE");
						break;
					case MotionEvent.ACTION_UP:
						Log.e(TAG,"onTouch  ACTION_UP");
					default:
						break;
				}
				break;
			default:
				break;
		}
		return false;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch(ev.getAction()) {
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
		//return true;
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.e(TAG,"onTouchEvent  ACTION_DOWN");
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
		return super.onTouchEvent(event);
		//return true;
	}
}
