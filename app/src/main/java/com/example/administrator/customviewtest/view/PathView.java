package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:Path 使用
 * @author: Administrator
 * @Date: 2017/9/7 9:19
 */

public class PathView extends View {
    private Paint mPaint = new Paint();
    ;
    private int mWidth, mHeight;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h/2;
        mWidth = w/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth , mHeight );  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
        canvas.scale(1,-1);                         //  翻转y坐标轴

        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
//        mPaint.setStrokeWidth(10);              // 边框宽度 - 10

        Path path = new Path();                     // 创建Path

//        path.lineTo(200, 200);                      // lineTo
//        path.setLastPoint(200,100);
//        path.moveTo(200,100);
//        path.lineTo(200,0);
//        path.close();
//        path.addRect(-200,-200,200,200, Path.Direction.CW);
//        path.setLastPoint(-100,100);                // 重置最后一个点的位置

        /*
        addPath
         */
        Path src = new Path();

//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        src.addCircle(0, 0, 100, Path.Direction.CW);
//
//        path.addPath(src, 0, 200);

        /*
        addArc与arcTo
         */
//        path.lineTo(100,100);
//
//        RectF oval = new RectF(0,0,200,200);
//
//        path.addArc(oval,0,270);
//         path.arcTo(oval,0,270,true);             // <-- 和上面一句作用等价

//        path.arcTo(oval,0,270);
//         path.arcTo(oval,0,270,false);             // <-- 和上面一句作用等价

        /*
        set
         */
//        path.addRect(-200,-200,200,200, Path.Direction.CW);
//        src.addCircle(0,0,100, Path.Direction.CW);// src添加一个圆
//        path.set(src);
//        path.addCircle(0,0,50, Path.Direction.CW);

//        Path dst = new Path();                      // dst中添加一个矩形
//        dst.addRect(-200,-200,200,200, Path.Direction.CW);

//        path.offset(100,0,dst);                     // 平移
//        canvas.drawPath(dst, mPaint);

//        mPaint.setColor(Color.BLUE);                // 更改画笔颜色

        /*
        rXxx方法
         */
//        src.moveTo(20,20);
//        src.rLineTo(20,40);
//        canvas.drawPath(src,mPaint);
//        mPaint.setColor(Color.BLUE);
        path.moveTo(20,20);
//        path.lineTo(20,40);
        path.rLineTo(20,40);
        canvas.drawPath(path,mPaint);               // 绘制Path
    }
}
