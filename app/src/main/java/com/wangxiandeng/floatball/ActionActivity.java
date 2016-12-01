package com.wangxiandeng.floatball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActionActivity extends AppCompatActivity {
    private List<Action> mData = new ArrayList<>();
    private ActionAdapter mAdapter;
    private String mEventType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        initView();
        initData();
    }

    private void initData() {
        String[] actionName = BaseApplication.getInstance().getActionNames();
        for (int i = 0; i < actionName.length; i++) {
            Action action = new Action(actionName[i]);
            mData.add(action);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mEventType = getIntent().getStringExtra(ConstaceValue.EVENT_TYPE);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new ActionAdapter(mData, mEventType));
    }
}
