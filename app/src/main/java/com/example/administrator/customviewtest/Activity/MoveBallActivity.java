package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.view.DrawView;

/**
 * @Description:自定义View——跟随手指移动的小球
 * @author: Administrator
 * @Date: 2017/9/1 15:00
 */

public class MoveBallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ball);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("跟随手指移动的小球");

        initView();
//        initData();
//        setListener();
    }

    private void initView() {
        //获取布局文件中容器
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_root);
        //创建DrawView组件
        DrawView drawView = new DrawView(this);
        //设置自定义组件的属性
        drawView.setMinimumHeight(500);//最小高度
        drawView.setMinimumWidth(300);//最小宽度
        relativeLayout.addView(drawView);
    }
}
