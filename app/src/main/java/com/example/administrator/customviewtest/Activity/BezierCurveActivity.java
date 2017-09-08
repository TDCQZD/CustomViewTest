package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.view.BezierCircleheartView;
import com.example.administrator.customviewtest.view.BezierCurveThreeView;
import com.example.administrator.customviewtest.view.BezierCurveTwoView;

/**
 * @Description:
 * @author: Administrator
 * @Date: 2017/9/8 14:38
 */

public class BezierCurveActivity extends AppCompatActivity {
    BezierCurveThreeView mBezierCurveThreeView;
    BezierCircleheartView circleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        BezierCurveTwoView mBezierCurveTwoView = new BezierCurveTwoView(this);
//        setContentView(mBezierCurveTwoView);
        setContentView(R.layout.activity_beziercircle);
//        mBezierCurveThreeView = (BezierCurveThreeView) findViewById(R.id.bezierView);
        circleView = (BezierCircleheartView) findViewById(R.id.checkview);
    }

//    public void control1(View view) {
//        mBezierCurveThreeView.setMode(true);
//    }
//
//    public void control2(View view) {
//        mBezierCurveThreeView.setMode(false);
//    }

}
