package com.example.view.customizeTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by TheShy on 2018/10/27 11:31.
 * Email:406262584@qq.com
 */
public class DargView extends View{
    private PointF mDownPoint;  //手指按下的点位置
    private PointF mMovePoint;   //手指移动的点的位置
    private Paint mPaint;

    private int moveCirclRadius;   //移动圆的半径,半径不变

    private int mFixCirclRadius;   //固定园的半径，随着拖动的距离变小
    public DargView(Context context) {
        this(context, null);
    }

    public DargView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DargView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        moveCirclRadius = dip2px(8);
        mFixCirclRadius = dip2px(7);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(mMovePoint == null || mDownPoint == null) {
            return;
        }
        canvas.drawCircle(mMovePoint.x,mMovePoint.y, moveCirclRadius, mPaint);
        int radius = (int)(mFixCirclRadius - getDistance(mMovePoint, mDownPoint) / 14);

        Path path = getBasierPath();
        if(path != null) {
            canvas.drawCircle(mDownPoint.x,mDownPoint.y, radius, mPaint);
            canvas.drawPath(path, mPaint);
        }
    }


    private Path getBasierPath() {
        //固定圆的半径太小，不画曲线
        int radius = (int)(mFixCirclRadius - getDistance(mMovePoint, mDownPoint) / 14);
        if(radius < dip2px(3)) {
            return null;
        }
        Path path = new Path();
        //计算两个点间x,和y的距离
        float dx = mMovePoint.x - mDownPoint.x;
        float dy = mMovePoint.y - mDownPoint.y;
        //计算夹角
        float tanA = dy / dx;
        double a = Math.atan(tanA);

        //夹角知道后求切点坐标
        //先求固定圆的两个切点坐标
        float p0X = (float) (mDownPoint.x + radius * Math.sin(a));
        float p0Y = (float)(mDownPoint.y - radius * Math.cos(a));

        float p3X = (float)(mDownPoint.x - radius * Math.sin(a));
        float p3Y = (float)(mDownPoint.y + radius * Math.cos(a));

        //再求移动圆的两个切点坐标

        float p1X =(float) (mMovePoint.x + moveCirclRadius * Math.sin(a));
        float p1Y =(float) (mMovePoint.y - moveCirclRadius * Math.cos(a));

        float p2X =(float) (mMovePoint.x - moveCirclRadius * Math.sin(a));
        float p2Y =(float) (mMovePoint.y + moveCirclRadius * Math.cos(a));

        //两个圆的中点坐标
        float centerX = (mDownPoint.x + mMovePoint.x) / 2;
        float centterY = (mDownPoint.y + mMovePoint.y) / 2;

        //求完所有点后开始画路径
        path.moveTo(p0X, p0Y);
        path.quadTo(centerX, centterY, p1X, p1Y);
        path.lineTo(p2X, p2Y);
        path.quadTo(centerX, centterY, p3X, p3Y);
        path.close();
        return path;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initPoint(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                updateMovePiont(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    /**
     * 计算两个点间的距离
     * @param p1
     * @param p2
     * @return
     */
    private double getDistance(PointF p1, PointF p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }


    private void initPoint(float downX, float downY) {
        mDownPoint  = new PointF(downX, downY);
        mMovePoint  = new PointF(downX, downY);
    }

    private void updateMovePiont(float moveX, float moveY) {
        mMovePoint.x = moveX;
        mMovePoint.y = moveY;
    }


    private int dip2px(int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
