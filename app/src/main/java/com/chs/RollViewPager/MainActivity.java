package com.chs.RollViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout dots_ll,top_news_viewpager;
    private ArrayList<View> dotList;
    private RollViewPager mViewPager;
    private int[] imgs = new int[]{R.mipmap.img_1,R.mipmap.img_2,R.mipmap.img_3,R.mipmap.img_4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
    }

    private void initView() {
        dots_ll = (LinearLayout) findViewById(R.id.dots_ll);
        top_news_viewpager = (LinearLayout) findViewById(R.id.top_news_viewpager);
    }

    private void initViewPager() {
        initDot(imgs.length);
        mViewPager = new RollViewPager(this, dotList,
                R.mipmap.point_facous, R.mipmap.point_normal,
                new RollViewPager.OnPagerClickCallback() {
                    @Override
                    public void onPagerClick(int position) {

                    }
                });
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        //top新闻的图片地址
        if(imgs.length!=0){
//            mViewPager.setUriList(imgsUrl); //网络获取的图片地址
            mViewPager.setResImageIds(imgs);//本地图片
        }else {
            Toast.makeText(this,"轮播图片加载失败...",Toast.LENGTH_LONG).show();
        }
//		mViewPager.setTitle(topNewsTitle, titleList);
        mViewPager.startRoll();
        top_news_viewpager.removeAllViews();
        top_news_viewpager.addView(mViewPager);
    }
    /**
     * 制作小点
     * @param size
     */
    private void initDot(int size) {
        dotList = new ArrayList<View>();
        dots_ll.removeAllViews();
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtil.dip2px(this, 6), DensityUtil.dip2px(this, 6));
            params.setMargins(5, 0, 5, 0);
            View m = new View(this);
            if (i == 0) {
                m.setBackgroundResource(R.mipmap.point_facous);
            } else {
                m.setBackgroundResource(R.mipmap.point_normal);
            }
            m.setLayoutParams(params);
            dots_ll.addView(m);
            dotList.add(m);
        }
    }
}
