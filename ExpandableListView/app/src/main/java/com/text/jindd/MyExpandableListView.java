package com.text.jindd;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class MyExpandableListView extends BaseExpandableListAdapter {

    private ArrayList<String[]> childlist = new ArrayList<String[]>();
    private ArrayList<Integer[]> childlistimage = new ArrayList<Integer[]>();
    private Context mContext;

    public MyExpandableListView(Context context) {
        mContext = context;
        childlist.add(DataSource.CHILD1);
        childlist.add(DataSource.CHILD2);
        childlistimage.add(DataSource.CHILD3);
        childlistimage.add(DataSource.CHILD4);
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
        return childlist.get(groupPosition).length;
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
        return childlist.get(groupPosition)[childPosition];
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.father_layout, parent, false);
        TextView grouptext = convertView.findViewById(R.id.father_text);
        String s = DataSource.FATHER[groupPosition];
        grouptext.setText(s);
        ImageView groupimage = convertView.findViewById(R.id.father_image);
        if (isExpanded)
            groupimage.setImageResource(R.drawable.up);
        else
            groupimage.setImageResource(R.drawable.down);
        return convertView;
    }

    /**
     * @param isLastChild  该子项是否是组中的最后一个子项
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.child_layout, parent, false);
        TextView childtext = convertView.findViewById(R.id.child_text);
        ImageView childimage = convertView.findViewById(R.id.child_image);
        String s = childlist.get(groupPosition)[childPosition];
        childtext.setText(s);
        convertView.setTag(R.string.groupimage,s);
        childimage.setImageResource(childlistimage.get(groupPosition)[childPosition]);
        return convertView;
    }

    /**
     * @return  子项是否可选中,如果要设置子项的点击事件,需要返回true
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
