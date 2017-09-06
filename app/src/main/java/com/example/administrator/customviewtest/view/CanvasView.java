package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:自定义View——Canvas画布使用
 * @author: Administrator
 * @Date: 2017/9/5 14:25
 */

public class CanvasView extends View {

    /*
    画笔使用步骤：
     1.创建一个画笔
     2.初始化画笔
     3.在构造函数中初始化
     */
    private Paint mPaint = new Paint();//1.创建一个画笔
    private Canvas mCanvas;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();//3.在构造函数中初始化
    }


    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
   2.初始化画笔
     */
    private void initPaint() {
        mPaint.setColor(Color.BLUE);//设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL); //设置画笔模式为填充
        mPaint.setStrokeWidth(10f); //设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        绘制颜色
         */
//        canvas.drawColor(Color.GREEN);
        /*
        绘制点
         */
//        canvas.drawPoint(50, 50, mPaint);
        /*
        绘制直线
         */
//        canvas.drawLine(300,300,500,600,mPaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
//        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
//                100,200,200,200,
//                100,300,200,300
//        },mPaint);
        /*
        绘制矩形
         */
        // 第一种
//        canvas.drawRect(100,100,450,400,mPaint);

        // 第二种
//        Rect rect = new Rect(100,100,450,400);
//        canvas.drawRect(rect,mPaint);

        // 第三种
//        RectF rectF = new RectF(100,100,450,400);
//        canvas.drawRect(rectF,mPaint);

        /*
        绘制圆角矩形
         */
        // 第一种
//        RectF rectF = new RectF(100,100,450,400);
//        canvas.drawRoundRect(rectF,30,30,mPaint);

        // 第二种
//        canvas.drawRoundRect(100,100,450,400,30,30,mPaint);
        /*
        圆角矩形—>椭圆
        rx>=宽度的一半
        ry>=高度的一半
         */
       // 矩形
//        RectF rectF = new RectF(100,100,450,400);

        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);

        // 绘制圆角矩形
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRoundRect(rectF,180,150,mPaint);

        /*
        绘制椭圆
         */
        // 第一种
//        RectF rectF = new RectF(100,100,400,400);//传递长宽相等的矩形(即正方形)，绘制出来的实际上就是一个圆
//        canvas.drawOval(rectF,mPaint);

        // 第二种
//        canvas.drawOval(100,100,450,400,mPaint);
        /*
        绘制圆
         */
//        canvas.drawCircle(200,200,100,mPaint);  // 绘制一个圆心坐标在(200,200)，半径为100 的圆。
        /*
        绘制圆弧
        绘制了一个起始角度为0度，扫过90度的圆弧，两者的区别就是是否使用了中心点
         */
        //①椭圆
//        RectF rectF = new RectF(100,100,450,300);
        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);

        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,0,90,false,mPaint);
//
//        RectF rectF2 = new RectF(100,400,450,600);
        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2,mPaint);

        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF2,0,90,true,mPaint);

        //②正圆
//        RectF rectF = new RectF(100,100,300,300);
        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,mPaint);

        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF,0,90,false,mPaint);

        //-------------------------------------

//        RectF rectF2 = new RectF(100,400,300,600);
        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2,mPaint);

        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF2,0,90,true,mPaint);

        /*
        Piant使用
         */
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);     //为了实验效果明显，特地设置描边宽度非常大

        // 描边
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(100,100,50,paint);

        // 填充
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200,200,50,paint);

        // 描边加填充
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(300, 300,50, paint);
    }
}
