package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.utils.DensityUtil;

import java.util.ArrayList;

/**
 * @Description:系统控件重新组合-下拉框效果
 * @author: Administrator
 * @Date: 2017/8/30 16:01
 */

public class PopupWindowActivity extends AppCompatActivity {

    private EditText et_input;
    private ImageView iv_down_arrow;
    private PopupWindow popupWindow;
    private ListView listview;
    private MyAdapter myAdapter;

    private ArrayList<String> msgs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popupwindow);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("下拉框效果");

        initView();
        initData();
//        setListener();
    }

    private void initData() {
        et_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (popupWindow == null) {
                    popupWindow = new PopupWindow(PopupWindowActivity.this);
                    popupWindow.setWidth(et_input.getWidth());
                    int height = DensityUtil.dip2px(PopupWindowActivity.this, 200);//dp->px
                    Toast.makeText(PopupWindowActivity.this, "height==" + height, Toast.LENGTH_SHORT).show();
                    popupWindow.setHeight(height);//px

                    popupWindow.setContentView(listview);
                    popupWindow.setFocusable(true);//设置焦点
                }

                popupWindow.showAsDropDown(et_input, 0, 0);
            }
        });

        listview = new ListView(this);
        listview.setBackgroundResource(R.drawable.listview_background);
        //准备数据
        msgs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            msgs.add(i + "--aaaaaaaaaaaaaa--" + i);
        }
        myAdapter = new MyAdapter();
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1.得到数据
                String msg = msgs.get(position);
                //2.设置输入框
                et_input.setText(msg);

                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
            }
        });
    }

    private void initView() {
        iv_down_arrow = (ImageView) findViewById(R.id.iv_down_arrow);
        et_input = (EditText) findViewById(R.id.et_input);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return msgs.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(PopupWindowActivity.this, R.layout.item_popupwindow, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_msg = (TextView) convertView.findViewById(R.id.tv_msg);
                viewHolder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到数据
            final String msg = msgs.get(position);
            viewHolder.tv_msg.setText(msg);

            //设置删除
            viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //1.从集合中删除
                    msgs.remove(msg);
                    //2.刷新ui-适配器刷新
                    myAdapter.notifyDataSetChanged();//getCount()-->getView();

                }
            });
            return convertView;
        }
    }

    static class ViewHolder {
        TextView tv_msg;
        ImageView iv_delete;
    }
}
