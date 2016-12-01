package com.wangxiandeng.floatball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llMoveUp;
    private TextView tvUpAction;
    private LinearLayout llMoveDown;
    private TextView tvDownAction;
    private LinearLayout llMoveLeft;
    private TextView tvLeftAction;
    private LinearLayout llMoveRight;
    private TextView tvRightAction;
    private LinearLayout llMoveClick;
    private TextView tvClickAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        tvUpAction.setText((String) SPUtils.get(ConstaceValue.EVENT_MOVE_UP, "无"));
        tvDownAction.setText((String) SPUtils.get(ConstaceValue.EVENT_MOVE_DOWN, "无"));
        tvLeftAction.setText((String) SPUtils.get(ConstaceValue.EVENT_MOVE_LEFT, "无"));
        tvRightAction.setText((String) SPUtils.get(ConstaceValue.EVENT_MOVE_RIGHT, "无"));
        tvClickAction.setText((String) SPUtils.get(ConstaceValue.EVENT_ONCLICK, "无"));
    }

    private void initView() {
        llMoveUp = (LinearLayout) findViewById(R.id.llMoveUp);
        llMoveDown = (LinearLayout) findViewById(R.id.llMoveDown);
        llMoveLeft = (LinearLayout) findViewById(R.id.llMoveLeft);
        llMoveRight = (LinearLayout) findViewById(R.id.llMoveRight);
        llMoveClick = (LinearLayout) findViewById(R.id.llMoveClick);

        tvUpAction = (TextView) findViewById(R.id.tvUpAction);
        tvDownAction = (TextView) findViewById(R.id.tvDownAction);
        tvLeftAction = (TextView) findViewById(R.id.tvLeftAction);
        tvRightAction = (TextView) findViewById(R.id.tvRightAction);
        tvClickAction = (TextView) findViewById(R.id.tvClickAction);


        llMoveUp.setOnClickListener(this);
        llMoveDown.setOnClickListener(this);
        llMoveLeft.setOnClickListener(this);
        llMoveRight.setOnClickListener(this);
        llMoveClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActionActivity.class);

        switch (v.getId()) {
            case R.id.llMoveUp:
                intent.putExtra(ConstaceValue.EVENT_TYPE, ConstaceValue.EVENT_MOVE_UP);
                break;
            case R.id.llMoveDown:
                intent.putExtra(ConstaceValue.EVENT_TYPE, ConstaceValue.EVENT_MOVE_DOWN);
                break;
            case R.id.llMoveLeft:
                intent.putExtra(ConstaceValue.EVENT_TYPE, ConstaceValue.EVENT_MOVE_LEFT);
                break;
            case R.id.llMoveRight:
                intent.putExtra(ConstaceValue.EVENT_TYPE, ConstaceValue.EVENT_MOVE_RIGHT);
                break;
            case R.id.llMoveClick:
                intent.putExtra(ConstaceValue.EVENT_TYPE, ConstaceValue.EVENT_ONCLICK);
                break;
        }
        startActivity(intent);
    }
}
