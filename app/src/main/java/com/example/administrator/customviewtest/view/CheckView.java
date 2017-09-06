package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.customviewtest.R;

/**
 * @Description:动态对号
 * @author: Administrator
 * @Date: 2017/9/6 15:39
 */

public class CheckView extends View {
    /*
    动画状态
     */
    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束
    /*
    创建画笔
     */
    private Paint mPaint = new Paint();

    private Bitmap mBitmap;

    private int mWidth, mHeight;        // 宽高
    private Handler mHandler;

    private int animCurrentPage = -1;       // 当前页码
    private int animMaxPage =13;           // 总页数
    private int animDuration = 500;         // 动画时长
    private int animState = ANIM_NULL;      // 动画状态

    private boolean isCheck = false;        // 是否只选中状态

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.checks);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();//刷新

                    if (animState == ANIM_NULL) {
                        return;
                    } else if (animState == ANIM_CHECK) {

                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }

                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    Log.e("TAG", "Count=" + animCurrentPage);
                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
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
        canvas.translate(mWidth / 2, mHeight / 2);

        // 绘制背景圆形
        canvas.drawCircle(0, 0, 150, mPaint);

        // 得出图像边长
        int sideLength = mBitmap.getWidth();

        // 得到图像选区 和 实际绘制位置
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        Rect dst = new Rect(-150, -150, 150, 150);

        // 绘制
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    /**
     * 选择
     */
    public void check() {
        if (animState != ANIM_NULL || isCheck)
            return;


        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
//        invalidate();
        isCheck = true;

    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck))
            return;

        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
//        invalidate();
        isCheck = false;
    }

    /**
     * 设置动画时长
     *
     * @param animDuration
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0)
            return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        mPaint.setColor(color);
    }

}
