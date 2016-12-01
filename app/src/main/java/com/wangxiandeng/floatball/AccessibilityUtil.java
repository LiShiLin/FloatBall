package com.wangxiandeng.floatball;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.provider.Settings;

/**
 * Created by wangxiandeng on 2016/11/25.
 */

public class AccessibilityUtil {

    private static FlashLightManager mManager;

    /**
     * 单击返回功能
     *
     * @param service
     */
    public static void doBack(AccessibilityService service) {
        service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    /**
     * 下拉打开通知栏
     *
     * @param service
     */
    public static void doPullNotification(AccessibilityService service) {
        service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS);
    }

    /**
     * 上拉返回桌面
     *
     * @param service
     */
    public static void doPullHome(AccessibilityService service) {
        service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
    }

    /**
     * 左右滑动打开多任务
     *
     * @param service
     */
    public static void doMultiTask(AccessibilityService service) {
        service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
    }

    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
//            android.provider.Settings.Secure.
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }

        return false;
    }

    public static void doAction(AccessibilityService service, String action) {
        String[] actionNames = BaseApplication.getInstance().getActionNames();
        if (actionNames[1].equals(action)) {
            //下拉通知
            doPullNotification(service);
        } else if (actionNames[2].equals(action)) {
            //多任务
            doMultiTask(service);
        } else if (actionNames[3].equals(action)) {
            //返回
            doBack(service);
        } else if (actionNames[4].equals(action)) {
            //返回主页
            doPullHome(service);
        } else if (actionNames[5].equals(action)) {
            //手电筒
            if (mManager == null) {
                mManager = new FlashLightManager(BaseApplication.getInstance());
                mManager.init();
            }
            if (!mManager.isTurnOnFlash())
                mManager.turnOn();
            else
                mManager.turnOff();
        }
    }


}
