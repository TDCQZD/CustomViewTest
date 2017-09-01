package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.viewGroup.MyViewPager;

/**
 * @Description:Viewpager实现Spinner功能
 * @author: Administrator
 * @Date: 2017/9/1 15:13
 */

public class ViewPagerFunctionActivity extends AppCompatActivity{
    private MyViewPager myviewpager;
    private RadioGroup rg_main;
    private int[] ids = {R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vp_fun);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("仿ViewPager功能");

        initView();
//        initData();
        setListener();
    }

    private void setListener() {
        //设置RadioGroup选中状态的变化
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *
             * @param group
             * @param checkedId : 0~5之间
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                myviewpager.scrollToPager(checkedId);//根据下标位置定位到具体的某个页面
            }
        });


        //设置监听页面的改变
        MyOnPagerChangListenter myOnPagerChangListenter = new MyOnPagerChangListenter();
        myviewpager.setOnPagerChangListenter(myOnPagerChangListenter);
    }

    private void initView() {
        myviewpager = (MyViewPager) findViewById(R.id.myviewpager);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        //添加页面
        addViewPager();
        //添加测试页面
        addTestViewpager();
    }
    private void addTestViewpager() {
        View testview = View.inflate(this, R.layout.test,null);
        myviewpager.addView(testview,2);

        for(int i=0;i<myviewpager.getChildCount();i++){
            RadioButton button = new RadioButton(this);
            button.setId(i);//0~5的id

            if(i==0){
                button.setChecked(true);
            }

            //添加到RadioGroup
            rg_main.addView(button);
        }
    }

    private void addViewPager() {
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);

            //添加到MyViewPager这个View中
            myviewpager.addView(imageView);
        }
    }

    private class MyOnPagerChangListenter implements MyViewPager.OnPagerChangListenter{
        @Override
        public void onScrollToPager(int position) {
            rg_main.check(position);
        }
    }
}
