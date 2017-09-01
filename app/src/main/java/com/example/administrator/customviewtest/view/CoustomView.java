package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;


/**
 * @Description:自定义View
 * @author: Administratr
 * @Date: 2017/8/30 11:07
 */

public class CoustomView extends View {
    private Bitmap bitmap1, bitmap2;

    public CoustomView(Context context) {
        super(context);
    }

    /**
     * 第一步：View的测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHight(heightMeasureSpec));
    }

    /*
    自定义测量View高
     */
    private int measureHight(int heightMeasureSpec) {
        int result = 0;
        /*
        测量步骤：
        1、从MeasureSpec对象中提取具体的测量模式和大小
         */
        int specMode = MeasureSpec.getMode(heightMeasureSpec);//测量的模式
        int specSize = MeasureSpec.getSize(heightMeasureSpec);//测量的大小
        /*
        2、判断测量模式。给出不同的值
         */
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /*
   自定义测量View自定义测量View宽
     */
    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        /*
        测量步骤：
        1、从MeasureSpec对象中提取具体的测量模式和大小
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);//测量的模式
        int specSize = MeasureSpec.getSize(widthMeasureSpec);//测量的大小
        /*
        2、判断测量模式。给出不同的值
         */
        if (specMode == MeasureSpec.EXACTLY) {//当specMode为EXACTLY时，直接使用指定的specSize 即可；当 specMode 为其他两种模式时，需要给它一个默认的大小。
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {//如果指定wrap_content祕性，即 AT_MOST 揹式，则需要取出我们指定的大小与 specSize中最小的一个来作为最后的测量值。
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 第二步：View的绘制
     * 在自定义 View 时，通常会去重写 onDrawO 方法来绘制 View 的显示内容。
     * 如果该 View还需要使用wrap _ content 属性，那么还必须重写 onMeasure ()方法。
     * 另外，通过自定义 attrs 属性，还可以设置新的属性配置值。
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(bitmap1, 0, 0, null);
        Canvas mCanvas=new Canvas(bitmap2);//创建Canvas对象

    }
}
