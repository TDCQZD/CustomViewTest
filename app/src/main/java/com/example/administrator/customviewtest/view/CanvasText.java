package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:Canvas之文字
 * @author: Administrator
 * @Date: 2017/9/6 16:20
 */

public class CanvasText extends View {
    private Paint mPaint = new Paint();
    private int mWidth, mHeight;        // 宽高

    public CanvasText(Context context) {
        super(context);
    }

    public CanvasText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);        // 设置颜色
        mPaint.setStyle(Paint.Style.FILL);   // 设置样式
        mPaint.setTextSize(50);              // 设置字体大小
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 移动坐标系到画布中央
//        canvas.translate(mWidth / 2, mHeight / 2);
        /*
        第一类(drawText):指定文本开始的位置，可以截取文本中部分内容进行绘制。
        public void drawText (String text, float x, float y, Paint paint)
        public void drawText (String text, int start, int end, float x, float y, Paint paint)
        public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
        public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
         */
        String text = "ABCDEFG";
        /*
          public void drawText (String text, float x, float y, Paint paint)
        参数分别为 (文本 基线x 基线y 画笔)
         */
        canvas.drawText(text, mWidth / 2, mHeight / 2, mPaint);
        /*
         public void drawText (String text, int start, int end, float x, float y, Paint paint)
       参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
         */
        canvas.drawText(text, 1, 3, mWidth / 2 + 50, mHeight / 2 + 50, mPaint);

        /*
         public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
         参数为 (字符数组 起始坐标 截取长度 基线x 基线y 画笔)
         */
        // 字符数组(要绘制的内容)
        char[] chars = "ABCDEFGHIJK".toCharArray();
        canvas.drawText(chars, 1, 3, mWidth / 2 + 100, mHeight / 2 + 100, mPaint);

        /*
        第二类(drawPosText)
         */
        String str = "SLOOP";
        canvas.drawPosText(str, new float[]{
                50, 50,
                100, 100,    // 第一个字符位置
                200, 200,    // 第二个字符位置
                300, 300,    // ...
                400, 400,

        }, mPaint);
    }
}
