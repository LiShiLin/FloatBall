package com.wangxiandeng.floatball;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class ActionAdapter extends RecyclerView.Adapter {
    private String mEventType;
    private List<Action> mDatas;
    private Context mContext;

    public ActionAdapter(List<Action> datas, String eventType) {
        mDatas = datas;
        mEventType = eventType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ActionViewHolder(View.inflate(parent.getContext(), R.layout.item_action, null));
    }

    private CheckBox lastCheckBox;

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final ActionViewHolder viewHolder = (ActionViewHolder) holder;
        final Action action = mDatas.get(position);
        viewHolder.tvName.setText(action.Name);
        //取出当前Event的Action
        String actionStr = (String) SPUtils.get(mEventType, "无");
        if (actionStr.equals(action.Name)) {
            action.IsCheck = true;
            lastCheckBox = viewHolder.cbState;//选中的
        } else {
            action.IsCheck = false;
        }

        viewHolder.cbState.setChecked(action.IsCheck);

        viewHolder.llClick.setOnClickListener(new View.OnClickListener() {

            private FlashLightManager mManager;

            @Override
            public void onClick(View v) {
                if (viewHolder.cbState.isChecked()) return;//已经选中了
                if (mManager == null) {
                    mManager = new FlashLightManager(mContext);
                    mManager.init();
                }
                if ("手电筒".equals(action.Name) && !mManager.isTurnOnFlash()) {
                    //跳转开启权限
                    Toast.makeText(mContext, "请开启相机权限", Toast.LENGTH_SHORT).show();
                    Uri packageURI = Uri.parse("package:" + mContext.getPackageName());
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                    mContext.startActivity(intent);
                    return;
                }


                SPUtils.set(mEventType, action.Name);
                if (lastCheckBox != null) lastCheckBox.setChecked(false);
                viewHolder.cbState.setChecked(true);
                lastCheckBox = viewHolder.cbState;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ActionViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private LinearLayout llClick;
        private CheckBox cbState;

        public ActionViewHolder(View itemView) {
            super(itemView);
            llClick = (LinearLayout) itemView.findViewById(R.id.llClick);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            cbState = (CheckBox) itemView.findViewById(R.id.cbState);
        }
    }
}
