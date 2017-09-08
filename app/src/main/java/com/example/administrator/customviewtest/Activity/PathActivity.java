package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.customviewtest.view.PathView;
import com.example.administrator.customviewtest.view.RadarView;

/**
 * @Description:Path测试
 * @author: Administrator
 * @Date: 2017/9/7 9:24
 */

public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PathView mPathView = new PathView(this);
        RadarView mRadarView=new RadarView(this);
        setContentView(mPathView);
    }
}
