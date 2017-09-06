package com.example.administrator.customviewtest.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.bean.PieData;
import com.example.administrator.customviewtest.view.PieView;

import java.util.ArrayList;

/**
 * @Description:Canvas
 * @author: Administrator
 * @Date: 2017/9/5 14:24
 */

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        PieView mPieView= (PieView) findViewById(R.id.pieview);
        mPieView.setData(addData());
        mPieView.setStartAngle(30);
//        PieView pieView = new PieView(this);
//        pieView.setStartAngle(10);
//        pieView.setData(addData());
//        setContentView(pieView);

    }

    private ArrayList<PieData> addData() {
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("GREEN", 180);
        pieData.setColor(Color.GREEN);
        PieData pieData2 = new PieData("BLUE", 90);
        pieData2.setColor(Color.BLUE);
        PieData pieData3 = new PieData("RED", 40);
        pieData3.setColor(Color.RED);
        PieData pieData4 = new PieData("BLACK", 30);
        pieData4.setColor(Color.BLACK);
        PieData pieData5 = new PieData("YELLOW", 10);
        pieData5.setColor(Color.YELLOW);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        return datas;
    }
}
