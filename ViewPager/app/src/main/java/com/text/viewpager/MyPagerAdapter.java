package com.text.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public MyPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public static final String[] TITLE = {"第一个Fragment","第二个Fragment","第三个Fragment","第四个Fragment","第五个Fragment"};
    private static final Integer[] TITLE_COLOR = {Color.RED,Color.YELLOW,Color.GREEN,Color.BLUE,Color.GRAY};


    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
////        return super.getPageTitle(position);
//        return TITLE[position];
//    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout,null);
        ImageView mTab_img = view.findViewById(R.id.tab_img);
        TextView mTab_txt = view.findViewById(R.id.tab_txt);
        view.setTag(R.string.tab_tv,mTab_txt);
        if (position==0)
            mTab_txt.setTextColor(Color.WHITE);
            else
                mTab_txt.setTextColor(Color.GRAY);
        mTab_img.setBackgroundColor(TITLE_COLOR[position]);
        mTab_txt.setText(MyPagerAdapter.TITLE[position]);
        return view;
    }
}
