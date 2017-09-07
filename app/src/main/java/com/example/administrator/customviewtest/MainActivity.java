package com.example.administrator.customviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.customviewtest.Activity.CanvasActivity;
import com.example.administrator.customviewtest.Activity.CanvasPictureActivity;
import com.example.administrator.customviewtest.Activity.ContactIndexActivity;
import com.example.administrator.customviewtest.Activity.CustomPropertiesActivity;
import com.example.administrator.customviewtest.Activity.CustomSwitchActivity;
import com.example.administrator.customviewtest.Activity.MoveBallActivity;
import com.example.administrator.customviewtest.Activity.PathActivity;
import com.example.administrator.customviewtest.Activity.PopupWindowActivity;
import com.example.administrator.customviewtest.Activity.SlideLayoutActivity;
import com.example.administrator.customviewtest.Activity.ViewPagerActivity;
import com.example.administrator.customviewtest.Activity.ViewPagerFunctionActivity;
import com.example.administrator.customviewtest.Activity.WaveActivity;
import com.example.administrator.customviewtest.Activity.YouKuMenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("自定义控件示例");

//        initView();
//        initData();
//        setListener();

    }

    public void youku_Menu(View view) {
        startActivity(new Intent(MainActivity.this, YouKuMenuActivity.class));
    }

    public void viewPger(View view) {
        startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
    }

    public void popup(View view) {
        startActivity(new Intent(MainActivity.this, PopupWindowActivity.class));
    }

    public void Switch(View view) {
        startActivity(new Intent(MainActivity.this, CustomSwitchActivity.class));
    }

    public void properties(View view) {
        startActivity(new Intent(MainActivity.this, CustomPropertiesActivity.class));
    }

    public void wave(View view) {
        startActivity(new Intent(MainActivity.this, WaveActivity.class));
    }

    public void index(View view) {
        startActivity(new Intent(MainActivity.this, ContactIndexActivity.class));
    }

    public void moveBall(View view) {
        startActivity(new Intent(MainActivity.this, MoveBallActivity.class));
    }

    public void viewPagerFunction(View view) {
        startActivity(new Intent(MainActivity.this, ViewPagerFunctionActivity.class));
    }

    public void SlideLayout(View view) {
        startActivity(new Intent(MainActivity.this, SlideLayoutActivity.class));
    }

    public void UseCanvas(View view) {
        startActivity(new Intent(MainActivity.this, CanvasActivity.class));
    }
    public void CanvasPicture(View view) {
        startActivity(new Intent(MainActivity.this, CanvasPictureActivity.class));
    }
    public void Path(View view) {
        startActivity(new Intent(MainActivity.this, PathActivity.class));
    }


}
