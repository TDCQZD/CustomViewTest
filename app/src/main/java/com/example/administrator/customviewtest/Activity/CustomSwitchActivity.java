package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.view.CoustomToggleButton;

/**
 * @Description:自定义View——自定义开关
 * @author: Administrator
 * @Date: 2017/8/31 9:14
 * 遗留问题：自定义Button的监听
 */

public class CustomSwitchActivity extends AppCompatActivity {
    boolean switchState = false;
    CoustomToggleButton toggleButton;
    ImageView ImageView_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_switch);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("自定义开关");

        initView();
//        initData();
        setListener();

    }

    private void initView() {
        toggleButton = (CoustomToggleButton) findViewById(R.id.toggleButton);
        ImageView_switch= (ImageView) findViewById(R.id.ImageView_switch);
    }

    private void setListener() {
        toggleButton = new CoustomToggleButton(this);
        toggleButton.setOnClickListener(new CoustomToggleButton.moveClickListern() {
            @Override
            public void OnClickListener() {
                if (switchState) {

                    Toast.makeText(CustomSwitchActivity.this, "打开开关", Toast.LENGTH_SHORT).show();
                    switchState = false;
                } else {
                    Toast.makeText(CustomSwitchActivity.this, "关闭开关", Toast.LENGTH_SHORT).show();
                    switchState = true;
                }
            }
        });
        ImageView_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchState) {
                    ImageView_switch.setImageResource(R.drawable.health_set_switch_on);
                    Toast.makeText(CustomSwitchActivity.this, "打开开关", Toast.LENGTH_SHORT).show();
                    switchState = false;
                } else {
                    ImageView_switch.setImageResource(R.drawable.health_set_switch_off);
                    Toast.makeText(CustomSwitchActivity.this, "关闭开关", Toast.LENGTH_SHORT).show();
                    switchState = true;
                }

            }
        });
    }


}
