package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.bean.MyBean;
import com.example.administrator.customviewtest.controls.SlideLayout;

import java.util.ArrayList;

/**
 * @Description:侧滑删除菜单
 * @author: Administrator
 * @Date: 2017/9/1 15:44
 */

public class SlideLayoutActivity extends AppCompatActivity {
    private ListView lv_main;

    private ArrayList<MyBean> myBeans;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slide_delete);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("侧滑删除菜单");
        lv_main = (ListView) findViewById(R.id.lv_main);
//        initView();
        initData();
//        setListener();


    }

    private void initData() {
        //设置适配器
        //准备数据
        myBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            myBeans.add(new MyBean("Content" + i));
        }
        myAdapter = new MyAdapter();
        lv_main.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return myBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView ==null){
                convertView = View.inflate(SlideLayoutActivity.this,R.layout.item_main,null);
                viewHolder = new ViewHolder();
                viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
                viewHolder.item_menu = (TextView) convertView.findViewById(R.id.item_menu);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到内容
            final MyBean myBean = myBeans.get(position);
            viewHolder.item_content.setText(myBean.getName());

            viewHolder.item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyBean myBean1 = myBeans.get(position);
                    Toast.makeText(SlideLayoutActivity.this, myBean1.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.item_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SlideLayout slideLayout = (SlideLayout) v.getParent();
                    slideLayout.closeMenu();
                    myBeans.remove(myBean);
//                    myAdapter.notifyDataSetChanged();
                    notifyDataSetChanged();
                }
            });

            SlideLayout slideLayout = (SlideLayout) convertView;
            slideLayout.setOnStateChangeListenter(new MyOnStateChangeListenter());

            return convertView;
        }
    }

    private SlideLayout slideLayout;

    class MyOnStateChangeListenter implements SlideLayout.OnStateChangeListenter {

        @Override
        public void onClose(SlideLayout layout) {
            if(slideLayout ==layout){
                slideLayout = null;
            }

        }

        @Override
        public void onDown(SlideLayout layout) {
            if(slideLayout != null && slideLayout!=layout){
                slideLayout.closeMenu();
            }

        }

        @Override
        public void onOpen(SlideLayout layout) {
            slideLayout = layout;
        }
    }

    static class ViewHolder{
        TextView item_content;
        TextView item_menu;
    }
}
