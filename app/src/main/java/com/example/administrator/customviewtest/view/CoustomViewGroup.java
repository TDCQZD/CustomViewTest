package com.example.administrator.customviewtest.view;

import android.content.Context;
import android.view.ViewGroup;

/**
 * @Description:自定义ViewGroup
 * @author: Administrator
 * @Date: 2017/8/30 11:44
 */

public class CoustomViewGroup extends ViewGroup {

    public CoustomViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /*
        控制其子View显示位置的逻辑
         */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
