package com.example.administrator.customviewtest.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.customviewtest.utils.TriangleView;

/**
 * Created by wangjinfeng-iri on 2017/7/17.
 */

public class MyVideoView extends View{

    //控件中心点坐标
    private int cvX,cvY;
    //三角形边长
    private int edge = 200;
    //画笔
    private Paint myPaint;
    //绘制三角形的路径
    private Path mPath;
    //存放三角形的数组，一共有4个三角形
    private TriangleView[] triangles = new TriangleView[4];
    //绘制状态，用来标记当前应该绘制哪个三角形
    private STATUS currentStatus = STATUS.MID_LOADING;
    //绘制动画
    private ValueAnimator valueAnimator;
    //枚举变量，存放绘制状态
    private enum STATUS {
        MID_LOADING,
        FIRST_LOADING,
        SECOND_LOADING,
        THIRD_LOADING,
        LOADING_COMPLETE,
        THIRD_DISMISS,
        FIRST_DISMISS,
        SECOND_DISMISS,
        MID_DISMISS
    }


    public MyVideoView(Context context) {
        super(context);
        init();
    }

    private void init() {
        //初始画笔和路径
        myPaint = new Paint();
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setAntiAlias(true);
        mPath = new Path();
    }

    public MyVideoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyVideoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化控件中心点和四个三角形的位置
        cvX = w/2;
        cvY = h/2;
        initTriangle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //初始化控件中心点和四个三角形的位置
//        cvX = getMeasuredWidth()/2;
//        cvY = getMeasuredHeight()/2;
//        initTriangle();
    }

    public void startTranglesAnimation() {
        //初始化三角形位置
        initTriangle();
        //如果有动画已经在执行了，取消当前执行的动画。
        if (valueAnimator != null && valueAnimator.isRunning()){
            valueAnimator.cancel();
        }
        //动画插值从0变成1
        valueAnimator = ValueAnimator.ofFloat(0,1);
        //每次动画的执行时长为300毫秒
        valueAnimator.setDuration(300);
        //无限次执行
        valueAnimator.setRepeatCount(-1);
        //每次执行的方案都是从头开始
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        //监听每次动画的循环情况，没循环一次进入下一个阶段
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //当上一个动画状态执行完之后进入下一个阶段。
                if (currentStatus == STATUS.MID_LOADING){
                    currentStatus = STATUS.FIRST_LOADING;
                }else if (currentStatus == STATUS.FIRST_LOADING){
                    currentStatus = STATUS.SECOND_LOADING;
                }else if (currentStatus == STATUS.SECOND_LOADING){
                    currentStatus = STATUS.THIRD_LOADING;
                }else if (currentStatus == STATUS.THIRD_LOADING){
                    currentStatus = STATUS.LOADING_COMPLETE;
                    reverseTriangleStart();
                }else if (currentStatus == STATUS.LOADING_COMPLETE){
                    currentStatus = STATUS.THIRD_DISMISS;
                }else if (currentStatus == STATUS.THIRD_DISMISS){
                    currentStatus = STATUS.FIRST_DISMISS;
                }else if (currentStatus == STATUS.FIRST_DISMISS){
                    currentStatus = STATUS.SECOND_DISMISS;
                }else if (currentStatus == STATUS.SECOND_DISMISS){
                    currentStatus = STATUS.MID_DISMISS;
                }else if (currentStatus == STATUS.MID_DISMISS){
                    Log.e("wangjinfeng","onAnimationRepeat");
                    currentStatus = STATUS.MID_LOADING;
                    reverseTriangleStart();
                }
            }
        });
        //监听动画执行过程
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //或者目前的插值(0-1)
                float fraction = animation.getAnimatedFraction();
                //如果目前的动画是消失状态，则插值正好是反过来的，是1-0，所以需要用1-fraction
                if (currentStatus == STATUS.FIRST_DISMISS || currentStatus == STATUS.SECOND_DISMISS || currentStatus == STATUS.THIRD_DISMISS || currentStatus == STATUS.MID_DISMISS){
                    fraction = 1 - fraction;
                }
                //根据目前执行的状态，取出对应的需要处理的三角形
                TriangleView triangleView = triangles[0];
                if (currentStatus == STATUS.MID_LOADING || currentStatus == STATUS.MID_DISMISS){
                    triangleView = triangles[0];
                }else if (currentStatus == STATUS.FIRST_LOADING || currentStatus == STATUS.FIRST_DISMISS){
                    triangleView = triangles[1];
                }else if (currentStatus == STATUS.SECOND_LOADING || currentStatus == STATUS.SECOND_DISMISS){
                    triangleView = triangles[2];
                }else if (currentStatus == STATUS.THIRD_LOADING || currentStatus == STATUS.THIRD_DISMISS){
                    triangleView = triangles[3];
                }else if (currentStatus == STATUS.LOADING_COMPLETE){
                    //如果是LOADING_COMPLETE状态的话，此次动画效果保持不变
                    invalidate();
                    return;
                }
                //这里是三角形变化的过程，计算目前current的坐标应当处在什么位置上
                //当fration为0的时候，current的坐标为start位置，当fratcion为1的时候，current的坐标是end位置
                triangleView.currentX1 = (int) (triangleView.startX + fraction * (triangleView.endX1 - triangleView.startX));
                triangleView.currentY1 = (int) (triangleView.startY + fraction * (triangleView.endY1 - triangleView.startY));
                triangleView.currentX2 = (int) (triangleView.startX + fraction * (triangleView.endX2 - triangleView.startX));
                triangleView.currentY2 = (int) (triangleView.startY + fraction * (triangleView.endY2 - triangleView.startY));
                invalidate();
            }
        });

        valueAnimator.start();
    }

    private void initTriangle() {
        //计算中间三角形的坐标位置，startx表示要开始延伸的起点，endx1和endx2表示延伸的两个终点，currentX1、currentX2表示的是正在延伸的点的位置
        currentStatus = STATUS.MID_LOADING;
        TriangleView triangleView = new TriangleView();
        //offset就是CD的长度，利用勾股定理
        int offset = (int) Math.sqrt(Math.pow(edge,2) - Math.pow(edge/2,2));
        triangleView.startX = cvX + offset/2;
        triangleView.startY = cvY + edge/2;
        triangleView.endX1 = cvX + offset/2;
        triangleView.endY1 = cvY - edge/2;
        triangleView.endX2 = cvX - offset/2;
        triangleView.endY2 = cvY;
        //current为延伸中的实时坐标，默认在起始点位置
        triangleView.currentX1 = triangleView.startX;
        triangleView.currentY1 = triangleView.startY;
        triangleView.currentX2 = triangleView.startX;
        triangleView.currentY2 = triangleView.startY;
        triangleView.color = "#be8cd5";
        triangles[0] = triangleView;
        //计算第一个三角形的坐标位置
        TriangleView firstTriangle = new TriangleView();
        firstTriangle.startX = triangleView.endX2;
        firstTriangle.startY = triangleView.endY2;
        firstTriangle.endX1 = triangleView.endX1;
        firstTriangle.endY1 = triangleView.endY1;
        firstTriangle.endX2 = firstTriangle.startX;
        firstTriangle.endY2 = firstTriangle.startY - edge;
        firstTriangle.color = "#fcb131";
        triangles[1] = firstTriangle;
        //计算第二个三角形的坐标位置
        TriangleView secondTriangle = new TriangleView();
        secondTriangle.startX = triangleView.endX1;
        secondTriangle.startY = triangleView.endY1;
        secondTriangle.endX1 = secondTriangle.startX;
        secondTriangle.endY1 = secondTriangle.startY + edge;
        secondTriangle.endX2 = secondTriangle.startX + offset;
        secondTriangle.endY2 = secondTriangle.startY + edge/2;
        secondTriangle.color = "#67c6ca";
        triangles[2] = secondTriangle;
        //计算第三个三角形的坐标位置
        TriangleView thirdTriangle = new TriangleView();
        thirdTriangle.startX = triangleView.startX;
        thirdTriangle.startY = triangleView.startY;
        thirdTriangle.endX1 = triangleView.endX2;
        thirdTriangle.endY1 = triangleView.endY2;
        thirdTriangle.endX2 = triangleView.endX2;
        thirdTriangle.endY2 = thirdTriangle.endY1 + edge;
        thirdTriangle.color = "#eb7583";
        triangles[3]  = thirdTriangle;
    }

    private void reverseTriangleStart(){
        for (int i = 0; i < triangles.length; i++){
            int startX = triangles[i].startX;
            int startY = triangles[i].startY;
            triangles[i].startX = triangles[i].endX1;
            triangles[i].startY = triangles[i].endY1;
            triangles[i].endX1 = startX;
            triangles[i].endY1 = startY;
            triangles[i].currentX1 = triangles[i].endX1;
            triangles[i].currentY1 = triangles[i].endY1;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < triangles.length;i++){
            mPath.reset();
            //移动到当前三角形的起始点位置上
            mPath.moveTo(triangles[i].startX,triangles[i].startY);
            //连接目前的current1
            mPath.lineTo(triangles[i].currentX1,triangles[i].currentY1);
            //连接目前的current2
            mPath.lineTo(triangles[i].currentX2,triangles[i].currentY2);
            //三角形线段闭合
            mPath.close();
            //设置三角形颜色
            myPaint.setColor(Color.parseColor(triangles[i].color));
            //绘制三角形
            canvas.drawPath(mPath,myPaint);
            //当只绘制中间三角形时，其他三角形不需要进行绘制
            if (currentStatus == STATUS.MID_LOADING){
                break;
            }
        }

    }
}
