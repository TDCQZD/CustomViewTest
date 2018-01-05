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
 * @Description:高仿小米视频加载动画效果
 * @author: Administrator
 * @Date: 2017/9/18 9:22
 */

public class MilletVoidLoading extends View {

    //控件中心点坐标
    private int centerX, centerY;
    //三角形边长
    private int longSide = 200;
    //画笔
    private Paint mPaint;
    //绘制三角形的路径
    private Path mPath;

    // 起始点坐标
    public int startX;
    public int startY;
    //终点坐标
    public int endX1;
    public int endY1;
    public int endX2;
    public int endY2;
    //当前延伸中的坐标位置
    public int currentX1;
    public int currentY1;

    public int currentX2;
    public int currentY2;

    //背景色
    public String color;

    public MilletVoidLoading(Context context) {
        super(context);
        initPaint();
    }

    public MilletVoidLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MilletVoidLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /*
    初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX , centerY );  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
        canvas.scale(1,-1);
        mPath = new Path();
        mPath.moveTo(10,10);
        mPath.lineTo(100,00);
        canvas.drawLine(100,100,200,200,mPaint);
        canvas.drawPath(mPath,mPaint);


    }
}
