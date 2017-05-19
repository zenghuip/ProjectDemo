package com.jgg.games.base;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * activity管理
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static final AppManager instance = new AppManager();

    private AppManager(){

    }

    public static AppManager getAppManager() {
        return instance;
    }

    /**
     * 添加Activity到堆
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activityStack == null){
            return;
        }
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishOtherActivity(Class<?> cls) {
        if (activityStack != null) {
            for (Activity act : activityStack) {
                if (!act.equals(cls) && !act.isFinishing()) {
                    act.finish();
                    activityStack.remove(act);
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        activityStack=null;
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否存在activity
     *
     * @return
     */
    public boolean isLive(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * 启动true 非启动状false
     *
     */
    public boolean isStarted() {
        if (activityStack == null) {
            return false;
        }
        return true;
    }
}
