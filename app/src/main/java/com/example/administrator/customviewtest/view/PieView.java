package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.customviewtest.bean.PieData;

import java.util.ArrayList;

/**
 * @Description:饼状图
 * @author: Administrator
 * @Date: 2017/9/5 15:23
 */

public class PieView extends View {
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFF6495ED, 0xFFCCFF00, 0xFFE32636, 0xFFFF8C69, 0xFF7CFC00, 0xFF800000, 0xFF808000, 0xFF808080,
            0xFFE6B800};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth, mHeight;
    // 文字色块部分
    private PointF mStartPoint = new PointF(20, 20);
    private PointF mCurrentPoint = new PointF(mStartPoint.x, mStartPoint.y);
    private float mColorRectSideLength = 20;
    private float mTextInterval = 10;
    private float mRowMaxLength;
    /*
    ①创建画笔
     */
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//用来防止边缘的锯齿,抗锯齿
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    ③确定View大小
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /*
    ⑤
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData) {
            return;
        }
        float currentStartAngle = mStartAngle;                    // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);                // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        RectF rect = new RectF(-r, -r, r, r);

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rect, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();

//            canvas.save();
//            canvas.translate(-mWidth / 2, -mHeight / 2);
//            RectF colorRect = new RectF(mCurrentPoint.x, mCurrentPoint.y, mCurrentPoint.x + mColorRectSideLength, mCurrentPoint.y + mColorRectSideLength);
//
//            canvas.restore();
        }

    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }

    /*
    ⑥设置数据
     */
    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();   // 刷新,重新绘制
    }

    // 初始化数据
    private void initData(ArrayList<PieData> mData) {
//        Log.i("TAG", "数据总数->"+mData.size());
        if (null == mData || mData.size() == 0) {   // 数据有问题 直接返回
            return;
        }
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            sumValue += pie.getValue();       //计算数值和
            int j = i % mColors.length;       //设置颜色
//            pie.setColor(mColors[j]);
            pie.setColor(pie.getColor());
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
//
//            float percentage = pie.getValue() / sumAngle;// 百分比
            float percentage = pie.getValue() / sumValue;// 百分比

            float angle = percentage * 360;// 对应的角度

            pie.setPercentage(percentage);// 记录百分比
            pie.setAngle(angle);// 记录角度大小
            sumAngle += angle;
//            Log.i("TAG", "angle->" + pie.getAngle());
        }

    }


}
