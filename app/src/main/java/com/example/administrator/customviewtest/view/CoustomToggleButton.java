package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.customviewtest.R;

/**
 * @Description:自定义开关
 * @author: Administrator
 * @ate: 2017/8/31 9:21
 * 一个视图从创建到显示过程中的主要方法
 * //1.构造方法实例化类
 * //2.测量-measure(int,int)-->onMeasure();
 * 如果当前View是一个ViewGroup,还有义务测量孩子
 * 孩子有建议权
 * //3.指定位置-layout()-->onLayout();
 * 指定控件的位置，一般View不用写这个方法，ViewGroup的时候才需要，一般View不需要重写该方法
 * //4.绘制视图--draw()-->onDraw(canvas)
 * 根据上面两个方法参数，进入绘制
 */

public class CoustomToggleButton extends View implements View.OnClickListener {

    private Bitmap slidingBitmap;
    private Paint paint;
    private Bitmap backgroundBitmap;
    /**
     * 距离左边最大距离
     */
    private int slidLeftMax;
    /**
     * 距离左边距离
     */
    private int slideLeft;
    /**
     * 按钮点击坐标
     */
    private float lastX;
    private float startX;
    /**
     * 结束值
     */
    private float endX;
    /**
     * 偏移量
     */
    private float distanceX;
    private boolean isOpen = false;
    private boolean isEnableClick = true;

    public CoustomToggleButton(Context context) {
        super(context);
    }

    /*
    如果我们在布局文件使用该类，将会用这个构造方法实例该类，如果没有就崩溃
     */
    public CoustomToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CoustomToggleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    布局Layout
     onlayout()
     */
    private void initView() {
        paint = new Paint();
        paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿

        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        slidingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        slidLeftMax = backgroundBitmap.getWidth() - slidingBitmap.getWidth();

        setOnClickListener(this);
    }

    /*
    视图的测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(backgroundBitmap.getWidth(), backgroundBitmap.getHeight());
    }

    /*
    绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
        canvas.drawBitmap(slidingBitmap, slideLeft, 0, paint);
    }

    /*
    点击监听
    true:点击事件生效，滑动事件不生效
    false:点击事件不生效，滑动事件生效
     */
    @Override
    public void onClick(View view) {
        if (isEnableClick) {
            isOpen = !isOpen;

            flushView();
        }
    }

    private void flushView() {
        if (isOpen) {
            slideLeft = slidLeftMax;

        } else {
            slideLeft = 0;
        }
        invalidate();//重新绘制view
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //1.记录按下的坐标
                lastX = startX = event.getX();
                isEnableClick = true;
                break;
            case MotionEvent.ACTION_UP:
                if (!isEnableClick) {
                    if (slideLeft > slidLeftMax / 2) {

                        //显示按钮开
                        isOpen = true;
                    } else {

                        isOpen = false;

                    }
                    flushView();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                //2.计算结束值
                endX = event.getX();
                //3.计算偏移量
                distanceX = endX - startX;
//                slideLeft = (int) (slideLeft + distanceX);
                slideLeft += distanceX;
                if (slideLeft < 0) {
                    slideLeft = 0;
                } else if (slideLeft > slidLeftMax) {
                    slideLeft = slidLeftMax;
                }
                //4.屏蔽非法值
                //5.刷新
                invalidate();
                //6.数据还原
                startX = event.getX();

                if (Math.abs(endX - lastX) > 5) {
                    //滑动
                    isEnableClick = false;
                }
                break;
        }
        return true;
    }

    public void setOnClickListener(moveClickListern moveClickListern) {
    }

    public interface moveClickListern {
        void OnClickListener();
    }

    ;

}
