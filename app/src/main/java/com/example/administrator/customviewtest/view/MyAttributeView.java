package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.customviewtest.R;

/**
 * @Description:自定义属性
 * @author: Administrator
 * @Date: 2017/8/31 10:29
 */

public class MyAttributeView extends View {
    private int myAge;
    private String myName;
    private Bitmap myBitmap;

    public MyAttributeView(Context context) {
        super(context);
    }

    public MyAttributeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性三种方式
        //1.用命名空间取获取
        String age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_age");
        String name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_name");
        String bitmap = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_bitmap");

        //2.遍历属性集合
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            System.out.println(attrs.getAttributeName(i) + "=====" + attrs.getAttributeValue(i));
        }
        //3.使用系统工具，获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAttributeView);
        for (int i = 0; i < typedArray.length(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.MyAttributeView_my_age:
                    myAge = typedArray.getInt(index, 0);
                    break;
                case R.styleable.MyAttributeView_my_name:
                    myName = typedArray.getString(index);
                    break;
                case R.styleable.MyAttributeView_my_bitmap:
                    Drawable drawable = typedArray.getDrawable(index);
                    BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
                    myBitmap=bitmapDrawable.getBitmap();
                    break;
            }
        }

        // 记得回收
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawText(myName+"---"+myAge,50,50,paint);
        canvas.drawBitmap(myBitmap,50,50,paint);
    }
}
