package com.example.administrator.customviewtest.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.customviewtest.view.MilletVoidLoading;

/**
 * @Description:
 * @author: Administrator
 * @Date: 2017/9/18 9:41
 */

public class MilletVoidLoadingActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyVideoView myVideoView=new MyVideoView(this);
        MilletVoidLoading milletVoidLoading=new MilletVoidLoading(this);
        setContentView(milletVoidLoading);
//        milletVoidLoading.startTranglesAnimation();
    }
}
