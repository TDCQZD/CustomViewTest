package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.customviewtest.R;

/**
 * @Description:
 * @author: Administrator
 * @Date: 2017/8/31 10:40
 */

public class WaveActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wave);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("水波纹");

//        initView();
//        initData();
//        setListener();

    }
}
