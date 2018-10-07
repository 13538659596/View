package com.example.view.customizeTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView.BufferType;

import com.example.view.R;

public class CircleText extends View {

	private Paint mPaint;
	private Paint mTextPaint;
	private Paint mCurrentStepPaint;
	private String mText ="";
	private int mTextColor;
	private int mBordeWidth = 1;

	private int maxtStep = 100;
	private int currentStep = 0;
	private int mTextSize = 20;

	public CircleText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub

		TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CircleText);
		mText = array.getString(R.styleable.CircleText_circText);
		mTextColor = array.getColor(R.styleable.CircleText_circTextColor,mTextColor);
		mBordeWidth = array.getDimensionPixelSize(R.styleable.CircleText_circBordeWidth,mBordeWidth);
		mTextSize = array.getDimensionPixelSize(R.styleable.CircleText_circTextSize,mTextSize);
		array.recycle();

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(mTextColor);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(mBordeWidth);
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(mBordeWidth);
		mPaint. setStrokeCap(Paint.Cap.ROUND);

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(mTextColor);
		mTextPaint.setStrokeWidth(2);
		mTextPaint.setTextSize(mTextSize);

		mCurrentStepPaint = new Paint();
		mCurrentStepPaint.setAntiAlias(true);
		mCurrentStepPaint.setColor(mTextColor);
		mCurrentStepPaint.setStyle(Paint.Style.STROKE);
		mCurrentStepPaint.setStrokeWidth(mBordeWidth);
		mCurrentStepPaint.setColor(Color.RED);
		mCurrentStepPaint.setStrokeWidth(mBordeWidth);
		mCurrentStepPaint. setStrokeCap(Paint.Cap.ROUND);

	}

	public CircleText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public CircleText(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int size = widthSize >= heightSize?heightSize:widthSize;
		setMeasuredDimension(size, size);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//正方形
		//float x = (getWidth() - getHeight() / 2) / 2;
		//float y = getHeight() / 4;
		//画外圆弧
		//Log.e(">>>>>", getWidth() + "   width");
		//Log.e(">>>>>", getHeight() + "  hight");

		RectF oval = new RectF(mBordeWidth/2, mBordeWidth/2,
				getMeasuredWidth()-mBordeWidth/2 , getMeasuredHeight() - mBordeWidth/2);
		//边缘不完整  描边有宽度  需要计算画笔的宽度
		canvas.drawArc(oval, 135, 270, false, mPaint);

		if(maxtStep == 0) {
			return;
		}
		float angel = (float)currentStep/maxtStep;
		//画内圆弧
		canvas.drawArc(oval, 135, angel * 270, false, mCurrentStepPaint);


		mText = currentStep+"";
		int x = (int)(getWidth() - mTextPaint.measureText(mText)) / 2;
		Paint.FontMetricsInt metrics = mTextPaint.getFontMetricsInt();
		int y = getMeasuredHeight()/2 + ((metrics.bottom - metrics.top)/2 - metrics.bottom);
		canvas.drawText(mText, x, y, mTextPaint);
		canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, mTextPaint);
	}


	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
		invalidate();
	}

}
