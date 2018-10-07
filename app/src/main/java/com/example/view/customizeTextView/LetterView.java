package com.example.view.customizeTextView;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class LetterView extends View {

	private String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	private Paint mPaint;

	private int currentPosition; // 记录当前手指的位置
	private boolean onTouch; // 是否在触摸
	private Paint backgrountPaint;

	private OnTuchListener mOnTuchListener;

	public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initPaint();
	}

	public LetterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public LetterView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(px2sp(12));

		backgrountPaint = new Paint();
		backgrountPaint.setColor(Color.BLUE);
		backgrountPaint.setStyle(Paint.Style.FILL);
	}

	private int px2sp(int size) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
				getResources().getDisplayMetrics());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = (int) (getPaddingLeft() + getPaddingRight() + mPaint
				.measureText(letter[0]));
		int hight = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(width, hight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (onTouch) {
			canvas.drawRect(0, 0, getMeasuredWidth(), getHeight(),
					backgrountPaint);
		}

		int textHight = (int) getHeight() / letter.length;
		int center = 0;
		int dy = getBaseLine();
		for (int i = 0; i < letter.length; i++) {
			center = i * textHight + textHight / 2;
			canvas.drawText(letter[i],
					(getWidth() - mPaint.measureText(letter[i])) / 2, center
							+ dy, mPaint);
		}
	}

	private int getBaseLine() {
		Paint.FontMetricsInt metrics = mPaint.getFontMetricsInt();
		int dy = (metrics.bottom - metrics.top) / 2 - metrics.bottom;
		return dy;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				onTouch = true;
				currentPosition = (int) (event.getY() /  (getHeight() / letter.length));
				if(mOnTuchListener != null) {
					mOnTuchListener.showText(letter[currentPosition]);
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if((int)(event.getY() /  (getHeight() / letter.length)) != currentPosition) {
					onTouch = true;
					currentPosition = (int) (event.getY() /  (getHeight() / letter.length));
					if(mOnTuchListener != null) {
						mOnTuchListener.showText(letter[currentPosition]);
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				onTouch = false;
				if(mOnTuchListener != null) {
					mOnTuchListener.dissmissText();
				}
				break;

		}
		Log.e("----->", "onTouch " + letter[(int) (event.getY() /  (getHeight() / letter.length))] );
		invalidate();
		return true;
	}


	public interface OnTuchListener {
		void showText(String text);
		void dissmissText();
	}

	public void setLitsener(OnTuchListener mOnTuchListener) {
		this.mOnTuchListener = mOnTuchListener;
	}

}
