package com.example.administrator.customviewtest.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.customviewtest.R;
import com.example.administrator.customviewtest.utils.DensityUtil;

import java.util.ArrayList;

/**
 * @Description:系统控件重新组合-广告条效果
 * @author: Administrator
 * @Date: 2017/8/30 15:25
 * 遗留问题：1、point点击监听
 */

public class ViewPagerActivity extends AppCompatActivity{

    private static final String TAG = ViewPagerActivity.class.getSimpleName();
    // 图片资源ID
    private final int[] imageIds = {
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m4,
            R.drawable.m5};
    // 图片标题集合
    private final String[] imageDescriptions = {
            "美女1号",
            "美女2号",
            "美女3号",
            "美女4号",
            "美女5号"
    };
    private ViewPager viewpager;
    private TextView tvViewPager;
    private LinearLayout llPointGroup;
    private ArrayList<ImageView> imageViews;
    /**
     * 上一次高亮显示的位置
     */
    private int prePosition = 0;
    /**
     * 是否已经滚动
     */
    private boolean isDragging = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = viewpager.getCurrentItem() + 1;
            viewpager.setCurrentItem(item);

            //延迟发消息
            handler.sendEmptyMessageDelayed(0, 4000);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_viewpager);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        //设置标题
        textView.setText("广告条效果");

        initView();
        initData();
        setListener();
    }

    private void setListener() {

    }
    private void initData() {
        //ViewPager的使用
        //1.在布局文件中定义ViewPager
        //2.在代码中实例化ViewPager
        //3.准备数据
        imageViews = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            //添加到集合中
            imageViews.add(imageView);
            //添加点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            int width = DensityUtil.dip2px(this, 8);
            Toast.makeText(ViewPagerActivity.this, "width==" + width, Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i == 0) {
                point.setEnabled(true); //显示红色
            } else {
                point.setEnabled(false);//显示灰色
                params.leftMargin = width;
            }


            point.setLayoutParams(params);

            llPointGroup.addView(point);
        }
        //4.设置适配器(PagerAdapter)-item布局-绑定数据
        viewpager.setAdapter(new MyPagerAdapter());
        //设置监听ViewPager页面的改变
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置中间位置
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();//要保证imageViews的整数倍
        viewpager.setCurrentItem(item);

        tvViewPager.setText(imageDescriptions[prePosition]);

        //发消息
        handler.sendEmptyMessageDelayed(0, 3000);
    }
    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tvViewPager = (TextView) findViewById(R.id.tv_viewPager);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动了的时候回调这个方法
         *
         * @param position             当前页面的位置
         * @param positionOffset       滑动页面的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中了的时候回调
         *
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            int realPosition = position % imageViews.size();
            Log.e(TAG, "onPageSelected==" + realPosition);
            //设置对应页面的文本信息
            tvViewPager.setText(imageDescriptions[realPosition]);

            //把上一个高亮的设置默认-灰色
            llPointGroup.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮-红色
            llPointGroup.getChildAt(realPosition).setEnabled(true);

            prePosition = realPosition;


        }

        /**
         * 当页面滚动状态变化的时候回调这个方法
         * 静止->滑动
         * 滑动-->静止
         * 静止-->拖拽
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isDragging = true;
                handler.removeCallbacksAndMessages(null);
                Log.e(TAG, "SCROLL_STATE_DRAGGING-------------------");
            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                Log.e(TAG, "SCROLL_STATE_SETTLING-----------------");

            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                isDragging = false;
                Log.e(TAG, "SCROLL_STATE_IDLE------------");
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0, 4000);
            }

        }
    }

    class MyPagerAdapter extends PagerAdapter {


        /**
         * 得到图片的总数
         *
         * @return
         */
        @Override
        public int getCount() {
//            return imageViews.size();
            return Integer.MAX_VALUE;
        }

        /**
         * 相当于getView方法
         *
         * @param container ViewPager自身
         * @param position  当前实例化页面的位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            int realPosition = position % imageViews.size();

            final ImageView imageView = imageViews.get(realPosition);
            container.addView(imageView);//添加到ViewPager中
            Log.e(TAG, "instantiateItem==" + realPosition + ",---imageView==" + imageView);

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN://手指按下
                            Log.e(TAG, "onTouch==手指按下");
                            handler.removeCallbacksAndMessages(null);
                            break;

                        case MotionEvent.ACTION_MOVE://手指在这个控件上移动
                            break;
                        case MotionEvent.ACTION_CANCEL://手指在这个控件上移动
                            Log.e(TAG, "onTouch==事件取消");
//                            handler.removeCallbacksAndMessages(null);
//                            handler.sendEmptyMessageDelayed(0,4000);
                            break;
                        case MotionEvent.ACTION_UP://手指离开
                            Log.e(TAG, "onTouch==手指离开");
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0, 4000);
                            break;
                    }
                    return false;
                }
            });

            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "点击事件");
                    int position = (int) v.getTag() % imageViews.size();
                    String text = imageDescriptions[position];
                    Toast.makeText(ViewPagerActivity.this, "text==" + text, Toast.LENGTH_SHORT).show();

                }
            });

            return imageView;
        }

        /**
         * 比较view和object是否同一个实例
         *
         * @param view   页面
         * @param object 这个方法instantiateItem返回的结果
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
//            if(view == object){
//                return true;
//            }else{
//                return  false;
//            }
            return view == object;
        }


        /**
         * 释放资源
         *
         * @param container viewpager
         * @param position  要释放的位置
         * @param object    要释放的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//            Log.e(TAG, "destroyItem==" + position + ",---object==" + object);
            container.removeView((View) object);

        }
    }

}
