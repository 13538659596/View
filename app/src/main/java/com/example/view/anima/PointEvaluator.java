package com.example.view.anima;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by TheShy on 2018/10/29 10:18.
 * Email:406262584@qq.com
 */
public class PointEvaluator implements TypeEvaluator<PointF> {



    //贝塞尔曲线的两个控制点
    private PointF point1,point2;
    public PointEvaluator(PointF point1, PointF point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
    @Override
    public PointF evaluate(float t, PointF point0, PointF point3) {
        PointF point = new PointF();
      /*  point.x = point0.x * (int)Math.pow((1-t),3) +
            3 * point1.x * t * (int)Math.pow((1-t), 2) +
            3 * point2.x * (int)Math.pow(t, 2) * (1- t) +
            point3.x * (int)Math.pow(t, 3);

        point.y = point0.y * (int)Math.pow((1-t),3) +
                3 * point1.y * t * (int)Math.pow((1-t), 2) +
                3 * point2.y * (int)Math.pow(t, 2) * (1- t) +
                point3.y * (int)Math.pow(t, 3);*/

        point.x = point0.x*(1-t)*(1-t)*(1-t)
                + 3 * point1.x * t *(1-t) * (1-t)
                + 3 * point2.x * t* t * (1-t)
                + point3.x * t * t * t;

        point.y = point0.y * (1-t) *(1-t) * (1-t)
                + 3 * point1.y * t *(1-t)*(1-t)
                + 3 * point2.y * t * t * (1-t)
                + point3.y * t * t * t;
        return point;
    }
}
