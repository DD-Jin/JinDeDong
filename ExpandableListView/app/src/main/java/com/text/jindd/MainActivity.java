package com.text.jindd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = findViewById(R.id.view1);

        final MyExpandableListView myExpandableListView = new MyExpandableListView(MainActivity.this);
        mView.setAdapter(myExpandableListView);
        mView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                myExpandableListView.setmExpand(groupPosition);
                myExpandableListView.notifyDataSetChanged();
            }
        });
        mView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                myExpandableListView.setmCollapse(groupPosition);
                myExpandableListView.notifyDataSetChanged();
            }
        });
        mView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String tag = (String)v.getTag(R.string.groupimage);
                Toast.makeText(MainActivity.this, tag, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
