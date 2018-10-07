package com.example.view.customizeTextView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.view.R;


//自定义属性  在values下新建文件夹attrs.xml文件
//在layout中指明命名空间  xmlns:app="http://schemas.android.com/apk/res-auto"  app命名空间名称
//在自定义View的构造方法中拿到布局中属性的值

@SuppressLint("DrawAllocation")
public class MyTextView extends TextView{

	private String mText;
	private int mTextSize = 15;
	private int mTextColor = Color.BLACK;
	private Paint mPaint;

	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr,
					  int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	//在布局文件中使用，但是会有style
	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub

		//获取自定义属性
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
		mText = array.getString(R.styleable.MyTextView_mytext);
		mTextSize = array.getDimensionPixelOffset(R.styleable.MyTextView_mytextSize, mTextSize);
		mTextColor = array.getColor(R.styleable.MyTextView_mytextColor, mTextColor);

		//回收
		array.recycle();

		mPaint = new Paint();
		//设置抗锯齿
		mPaint.setAntiAlias(true);
		mPaint.setColor(mTextColor);
		mPaint.setTextSize(mTextSize);
	}

	//在布局文件中使用
	public MyTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	//new 的时候调用
	public MyTextView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 指定布局的宽高
	 */
	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		//获取设置的宽高模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		//MeasureSpec.AT_MOST  		在布局中指定的是wrap_content
		//MeasureSpec.EXACTLY  		在布局中指定的是具体值
		//MeasureSpec.UNSPECIFIED	尽可能的大  很少用到

		//ScrollView 嵌套 ListView 显示不全问题
		/**
		 *  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		 *		heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
		 *		MeasureSpec.AT_MOST);
		 *		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 *	}
		 */

		//widthMeasureSpec 解析  包含两个信息  1:模式 2位   2:值 30位
		//int 占32位   widthMeasureSpec 最低两位保存模式   高30位保存值

		/*if(widthMode == MeasureSpec.UNSPECIFIED) {
			Log.e(">>>>", "设置的mach_parent");

		}else if(widthMode == MeasureSpec.AT_MOST) {
			Log.e(">>>>", "设置的wrap_content");

		}else if(widthMode == MeasureSpec.EXACTLY) {
			Log.e(">>>>", "设置的具体宽度");
		}*/

		//获取文字设置的大小
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		if(widthMode == MeasureSpec.AT_MOST) {
			Rect bounds = new Rect();
			widthSize = (int)mPaint.measureText(mText) + getPaddingLeft() + getPaddingRight()+1;
			//mPaint.getTextBounds(mText, 0, mText.length(), bounds);
			//widthSize = bounds.width() + getPaddingLeft() + getPaddingRight() + 10;

		}
		if(heightMode == MeasureSpec.AT_MOST) {
			Rect bounds = new Rect();
			mPaint.getTextBounds(mText, 0, mText.length(), bounds);
			heightSize = bounds.height() + getPaddingTop() + getPaddingBottom();

		}
		setMeasuredDimension(widthSize, heightSize);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		/**
		 * drawText
		 * x:起始绘制位置
		 * y:baseLine
		 */
		Paint.FontMetricsInt metrics = mPaint.getFontMetricsInt();

		int centerY = getHeight()/2;  //中线
		int t = (metrics.bottom - metrics.top)/ 2;

		Log.e(">>>>>>>> centerY ", centerY + "");
		Log.e(">>>>>>>> t ", t + "");

		int dy = (metrics.bottom - metrics.top)/ 2 - metrics.bottom;
		int dy1 = (metrics.bottom - metrics.top)/ 2 + metrics.top;
		Log.e(">>>>>>>>dy ", dy + "");
		Log.e(">>>>>>>>dy1 ", dy1 + "");
		//下移
		canvas.drawText(mText, getPaddingLeft(), getHeight()/2 + dy , mPaint);
	}


	//事件分发
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
}

