package com.text.viewpager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initViewPager();
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initViewPager() {

        //创建List集合
        fragments = new ArrayList<>();
        //添加到fragments集合里
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        //创建适配器
        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        //把fragment添加到adapter
        adapter.setFragments(fragments);
        //吧adapter添加到viewPager
        viewPager.setAdapter(adapter);

        //tabLayout有几个创建几个
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        //是tabLayout和viewPager  关联同步一下
        tabLayout.setupWithViewPager(viewPager);
        //设置创建的名字  万物从0开始
//        tabLayout.getTabAt(0).setText("第一个");
//        tabLayout.getTabAt(1).setText("第二个");
//        tabLayout.getTabAt(2).setText("第三个");
//        tabLayout.getTabAt(3).setText("Rear View Mirror \n and Seat Key Memory");
//        tabLayout.getTabAt(4).setText("第五个");
        for (int i = 0; i < 5; i++) {
            tabLayout.getTabAt(i).setCustomView(adapter.getTabView(i));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setTag(R.string.select_fragment,position);
                for (int i = 0; i < 5; i++) {
                    View customView = tabLayout.getTabAt(i).getCustomView();
                    TextView tab_tv = (TextView)customView.getTag(R.string.tab_tv);
                    if (i==position)
                        tab_tv.setTextColor(Color.WHITE);
                    else
                        tab_tv.setTextColor(Color.GRAY);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            float down_x = 0;
            float up_x = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int selectPosition;
                try {
                    selectPosition = (int)viewPager.getTag(R.string.select_fragment);
                }catch (Exception e){
                    selectPosition=0;
                }

                    if (v.getId()==R.id.viewPager) {

                        if (event.getAction()==MotionEvent.ACTION_DOWN){
                            down_x = event.getX();
                            Log.d("selectPosition", "down_x: "+down_x);
                        }else if (event.getAction()==MotionEvent.ACTION_UP){
                            up_x = event.getX();
                            Log.d("selectPosition", "up_x: "+up_x);
                            if (selectPosition==0 && up_x-down_x>496f){
                                viewPager.setCurrentItem(4,false);
                                viewPager.setTag(R.string.select_fragment,4);
                                return true;
                            }else if (selectPosition==4 && up_x-down_x<-496f){
                                viewPager.setCurrentItem(0,false);
                                viewPager.setTag(R.string.select_fragment,0);
                                return true;
                            }
                        }


                    }
                return MainActivity.super.onTouchEvent(event);
            }
        });

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }


 }
