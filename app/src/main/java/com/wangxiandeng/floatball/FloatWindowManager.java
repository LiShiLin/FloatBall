package com.wangxiandeng.floatball;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import static com.wangxiandeng.floatball.FloatBallView.MODE_CLICK;
import static com.wangxiandeng.floatball.FloatBallView.MODE_DOWN;
import static com.wangxiandeng.floatball.FloatBallView.MODE_LEFT;
import static com.wangxiandeng.floatball.FloatBallView.MODE_RIGHT;
import static com.wangxiandeng.floatball.FloatBallView.MODE_UP;

/**
 * Created by wangxiandeng on 2016/11/25.
 */

public class FloatWindowManager {
    private static FloatBallView mBallView;

    private static WindowManager mWindowManager;


    public static void addBallView(Context context) {
        if (mBallView == null) {
            WindowManager windowManager = getWindowManager(context);
            int screenWidth = windowManager.getDefaultDisplay().getWidth();
            int screenHeight = windowManager.getDefaultDisplay().getHeight();
            mBallView = new FloatBallView(context);
            LayoutParams params = new LayoutParams();
            params.x = screenWidth;
            params.y = screenHeight / 2;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.LEFT | Gravity.TOP;
            params.type = LayoutParams.TYPE_PHONE;
            params.format = PixelFormat.RGBA_8888;
            params.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | LayoutParams.FLAG_NOT_FOCUSABLE;
            mBallView.setLayoutParams(params);
            mBallView.setOnActionListener(new FloatBallView.OnActionListener() {
                @Override
                public void onAction(int Mode) {
                    String action = "";
                    switch (Mode) {
                        case MODE_LEFT:
                            action = (String) SPUtils.get(ConstaceValue.EVENT_MOVE_LEFT, "无");
                            break;
                        case MODE_RIGHT:
                            action = (String) SPUtils.get(ConstaceValue.EVENT_MOVE_RIGHT, "无");
//                            AccessibilityUtil.doLeftOrRight(mBallView.getService());
                            break;
                        case MODE_DOWN:
                            action = (String) SPUtils.get(ConstaceValue.EVENT_MOVE_DOWN, "无");
//                            AccessibilityUtil.doPullDown(mBallView.getService());
                            break;
                        case MODE_UP:
                            action = (String) SPUtils.get(ConstaceValue.EVENT_MOVE_UP, "无");
//                            AccessibilityUtil.doPullUp(mBallView.getService());
                            break;
                        case MODE_CLICK:
                            action = (String) SPUtils.get(ConstaceValue.EVENT_ONCLICK, "无");
                            break;
                    }
                    AccessibilityUtil.doAction(mBallView.getService(), action);
                }

            });

            windowManager.addView(mBallView, params);
        }
    }

    public static void removeBallView(Context context) {
        if (mBallView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mBallView);
            mBallView = null;
        }
    }

    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

}
