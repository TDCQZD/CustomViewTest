package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.utils.Tools;

/**
 * @Description:系统控件重新组合-优酷菜单
 * @author: Administrator
 * @Date: 2017/8/30 14:56
 */

public class YouKuMenuActivity extends AppCompatActivity{

    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;
    private ImageView iconMenu;
    private ImageView iconHome;
    private ImageView music;
    private ImageView seach;
    /**
     * 是否显示第三圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel3 = true;

    /**
     * 是否显示第二圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel2 = true;


    /**
     * 是否显示第一圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel1 = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_youku_tools);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("优酷菜单");
        
        initView();
       
        setListener();
    }

    private void setListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        //设置点击事件
        iconHome.setOnClickListener(myOnClickListener);
        iconMenu.setOnClickListener(myOnClickListener);
        level1.setOnClickListener(myOnClickListener);
        level2.setOnClickListener(myOnClickListener);
        level3.setOnClickListener(myOnClickListener);
        music.setOnClickListener(myOnClickListener);
        seach.setOnClickListener(myOnClickListener);
    }

    private void initView() {
        level3 = (RelativeLayout) findViewById(R.id.level3);
        level2 = (RelativeLayout) findViewById(R.id.level2);
        iconMenu = (ImageView) findViewById(R.id.icon_menu);
        level1 = (RelativeLayout) findViewById(R.id.level1);
        iconHome = (ImageView) findViewById(R.id.icon_home);
        music = (ImageView) findViewById(R.id.music);
        seach = (ImageView) findViewById(R.id.seach);
    }
    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.icon_home:
                    HomeTouch();
                    break;
                case R.id.icon_menu:
                    MMenuTouch();
                    break;
                case R.id.level1:
                    Toast.makeText(YouKuMenuActivity.this, "level1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.level2:
                    Toast.makeText(YouKuMenuActivity.this, "level2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.level3:
                    Toast.makeText(YouKuMenuActivity.this, "level3", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.music:
                    Toast.makeText(YouKuMenuActivity.this, "音乐", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.seach:
                    Toast.makeText(YouKuMenuActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
    //二级菜单处理
    private void MMenuTouch() {
        if (isShowLevel3) {
            //隐藏
            isShowLevel3 = false;
            Tools.hideView(level3);
        } else {
            //显示
            isShowLevel3 = true;
            Tools.showView(level3);
        }
    }

    //一级菜单处理
    private void HomeTouch() {
        //如果三级菜单和二级菜单是显示，都设置隐藏
        if (isShowLevel2) {
            //隐藏二级菜单
            isShowLevel2 = false;
            Tools.hideView(level2);

            if (isShowLevel3) {
                //隐藏三级菜单
                isShowLevel3 = false;
                Tools.hideView(level3, 200);
            }
        } else {
            //如果都是隐藏的，二级菜单显示
            //显示二级菜单
            isShowLevel2 = true;
            Tools.showView(level2);

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {

            //如果一级，二级，三级菜单是显示的就全部隐藏
            if (isShowLevel1) {
                isShowLevel1 = false;
                Tools.hideView(level1);
                if (isShowLevel2) {
                    //隐藏二级菜单
                    isShowLevel2 = false;
                    Tools.hideView(level2, 200);
                    if (isShowLevel3) {
                        //隐藏三级菜单
                        isShowLevel3 = false;
                        Tools.hideView(level3, 400);
                    }
                }
            } else {
                //如果一级，二级菜单隐藏，就显示
                //显示一级菜单
                isShowLevel1 = true;
                Tools.showView(level1);

                //显示二级菜单
                isShowLevel2 = true;
                Tools.showView(level2, 200);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
