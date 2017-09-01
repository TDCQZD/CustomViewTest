package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description:自定义小球
 * @author: Administrator
 * @Date: 2017/9/1 15:01
 */

public class DrawView extends View {
    Paint paint = new Paint();//定义创建画笔
    private float currentX = 40;
    private float currentY = 50;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);//设置画笔颜色
        //绘制圆环作为小球
        canvas.drawCircle(currentX, currentY, 15, paint);
    }

    /**
     * 为触摸事件写事件处理方法
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //修改XY属性
        currentX = event.getX();
        currentY = event.getY();
        //重新绘制
        invalidate();
        //返回true表明该处理方法已经处理该事件
        return true;
    }
}
