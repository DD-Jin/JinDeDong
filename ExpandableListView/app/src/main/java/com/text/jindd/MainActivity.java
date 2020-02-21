package com.text.jindd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = findViewById(R.id.view1);

        final MyExpandableListViewAdapter mAdapter = new MyExpandableListViewAdapter(MainActivity.this);
        mView.setAdapter(mAdapter);


        //二级列表展开监听
        mView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        //二级列表收起监听
        mView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        //二级列表item点击监听
        mView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String tag = (String)v.getTag(R.string.groupimage);
                Toast.makeText(MainActivity.this, tag, Toast.LENGTH_SHORT).show();
                mAdapter.setmSelectNumber(groupPosition,childPosition);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
        //一级目录item点击监听
        mView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // false : 一级目录能展开  true : 一级目录不能展开
                return false;
            }
        });

    }
}
