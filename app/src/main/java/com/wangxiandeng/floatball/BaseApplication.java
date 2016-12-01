package com.wangxiandeng.floatball;

import android.app.Application;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        SPUtils.init(this);
        instance=this;
        mActionNames =getResources().getStringArray(R.array.acticon_name);
    }
    private String[] mActionNames;

    public String[] getActionNames() {
        return mActionNames;
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
