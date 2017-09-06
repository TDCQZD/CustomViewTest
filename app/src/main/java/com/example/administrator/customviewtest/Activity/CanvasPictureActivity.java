package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.administrator.customviewtest.view.CanvasPicture;
import com.example.administrator.customviewtest.view.CanvasText;
import com.example.administrator.customviewtest.view.CheckView;

/**
 * @Description:
 * @author: Administrator
 * @Date: 2017/9/6 14:44
 */

public class CanvasPictureActivity extends AppCompatActivity {
    CheckView checkView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        CanvasPicture canvasPicture = new CanvasPicture(this);
        CanvasText canvasText = new CanvasText(this);
        setContentView(canvasText);
//        setContentView(R.layout.activity_checkview);
//        checkView= (CheckView) findViewById(R.id.checkview);
//        checkView.setBackgroundColor(Color.YELLOW);
//        checkView.setAnimDuration(1000);
    }
//    public void uncheck(View view){
//        checkView.unCheck();
//    }
//    public void check(View view){
//        checkView.check();
//    }
}
