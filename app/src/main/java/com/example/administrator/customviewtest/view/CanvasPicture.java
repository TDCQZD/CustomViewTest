package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.customviewtest.R;

/**
 * @Description:Canvas之图片
 * @author: Administrator
 * @Date: 2017/9/6 14:35
 */

public class CanvasPicture extends View {
    // 1.创建Picture
    private Picture mPicture = new Picture();
    private Context mContext;
    private int mWidth, mHeight;        // 宽高
    public CanvasPicture(Context context) {
        super(context);
    }

    public CanvasPicture(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        recording(); // 3.在使用前调用
    }


    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);

        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);
        //结束录制
        mPicture.endRecording();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight=h;
        mWidth=w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        Picture绘制的方法：
        1、使用Picture提供的draw方法绘制
         */
        mPicture.draw(canvas);

        /*
        2.使用Canvas提供的drawPicture方法绘制
        public void drawPicture (Picture picture)
        public void drawPicture (Picture picture, Rect dst)
        public void drawPicture (Picture picture, RectF dst)
         */
        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));

        // 包装成为Drawable
        PictureDrawable drawable = new PictureDrawable(mPicture);
        // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
        drawable.setBounds(0, 0, 250, mPicture.getHeight());
        // 绘制
        drawable.draw(canvas);

        /*
        绘制Bitmap:
         // 第一种
         public void drawBitmap (Bitmap bitmap, Matrix matrix, Paint paint)
        // 第二种 指定了图片左上角的坐标(距离坐标原点的距离)
        public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
      // 第三种
      public void drawBitmap (Bitmap bitmap, Rect src, Rect dst, Paint paint)
       public void drawBitmap (Bitmap bitmap, Rect src, RectF dst, Paint paint)
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m3);
        /*
        第一种
         */
//        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        /*
        第二种
         */
//        canvas.drawBitmap(bitmap, 200, 500, new Paint());
        /*
        第三种
         */
        // 将画布坐标系移动到画布中央
        canvas.translate(mWidth / 2, mHeight / 2);

        // 指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);

        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0, 0, 200, 400);

        // 绘制图片
        canvas.drawBitmap(bitmap, src, dst, null);
    }
}
