package com.text.jindd;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IllegalFormatCodePointException;

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private int mSelectGroup = 0;
    private int mSelectChild = 0;

    public MyExpandableListViewAdapter(Context context) {
        mContext = context;
    }

    public void setmSelectNumber(int mSelectGroup,int mSelectChild) {
        this.mSelectGroup = mSelectGroup;
        this.mSelectChild = mSelectChild;
    }


    /**
     * @return 一级目录的个数
     */
    @Override
    public int getGroupCount() {
        return DataSource.FATHER.length;
    }

    /**
     * @param groupPosition 一级目录的position
     * @return 对应一级目录下的二级目录个数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return DataSource.CHILD_NAME[groupPosition].length;
    }

    /**
     * @return 获得某个父项
     */
    @Override
    public Object getGroup(int groupPosition) {
        return DataSource.FATHER[groupPosition];
    }

    /**
     * @return 获得某个获得某个子项
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return DataSource.CHILD_NAME[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * @param isExpanded     该父组件是否被打开
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gh;
        if (convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.father_layout, parent, false);
            gh = new GroupHolder(convertView);
            convertView.setTag(gh);
        }else
            gh = (GroupHolder)convertView.getTag();

        String s = DataSource.FATHER[groupPosition];
        gh.mGroupTxt.setText(s);
        if (isExpanded)
            gh.mGroupImg.setImageResource(R.drawable.up);
        else
            gh.mGroupImg.setImageResource(R.drawable.down);
        return convertView;
    }

    /**
     * @param isLastChild  该子项是否是组中的最后一个子项
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder ch;
        if (convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.child_layout, parent, false);
            ch = new ChildHolder(convertView);
            convertView.setTag(ch);
        }else
            ch = (ChildHolder)convertView.getTag();

        String s = DataSource.CHILD_NAME[groupPosition][childPosition];
        ch.mChildTxt.setText(s);
        convertView.setTag(R.string.groupimage,s);
        ch.mChildImg.setImageResource(DataSource.CHILD_IMAGE[groupPosition][childPosition]);
        if (groupPosition==mSelectGroup && childPosition==mSelectChild) {
            ch.mChildTxt.setTextColor(Color.WHITE);
            ch.mChildImg.setAlpha(1.0f);
        } else {
            ch.mChildTxt.setTextColor(Color.GRAY);
            ch.mChildImg.setAlpha(0.5f);
        }
        return convertView;
    }

    /**
     * @return  子项是否可选中,如果要设置子项的点击事件,需要返回true
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class GroupHolder{
        public TextView mGroupTxt;
        public ImageView mGroupImg;

        public GroupHolder(View v) {
            mGroupTxt = v.findViewById(R.id.father_text);
            mGroupImg = v.findViewById(R.id.father_image);
        }
    }

    private static class ChildHolder{
        TextView mChildTxt;
        ImageView mChildImg;

        public ChildHolder(View v) {
            mChildTxt = v.findViewById(R.id.child_text);
            mChildImg = v.findViewById(R.id.child_image);
        }
    }

}
