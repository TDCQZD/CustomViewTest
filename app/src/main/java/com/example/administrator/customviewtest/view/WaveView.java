package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description:水波纹
 * @author: Administrator
 * @Date: 2017/8/31 10:41
 */

public class WaveView extends View {
    private Paint paint;
    private float downX;
    private float downY;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        radio = 5;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(radio / 3);

    }

    private int radio = 5;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            radio += 5;
            int alpha = paint.getAlpha();
            alpha -= 5;
            if(alpha <0){
                alpha =0;
            }
            paint.setAlpha(alpha);
            paint.setStrokeWidth(radio / 3);
            invalidate();//onDraw();
        }
    };
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(paint.getAlpha()>0&&downX>0&&downY>0){
            canvas.drawCircle(downX,downY,radio,paint);
            handler.sendEmptyMessageDelayed(0,50);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                downX = event.getX();
                downY = event.getY();
                initView();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
