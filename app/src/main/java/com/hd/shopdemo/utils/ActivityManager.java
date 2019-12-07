package com.hd.shopdemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * activity管理器
 */
public class ActivityManager {

    private static ActivityManager instance;
    private List<Activity> mActivityList = new ArrayList<Activity>();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /***
     * 将 Activity 添加到容器中
     * @param mActivity
     * @return
     */
    public boolean addActivity(Activity mActivity) {
        return mActivityList.add(mActivity);
    }

    /****
     * 将 Activity 移除制容器
     * @param mActivity
     * @return
     */
    public boolean deleteActivity(Activity mActivity) {
        return mActivityList.remove(mActivity);
    }

    /****
     * 将 Activity 移除制容器
     * @param cls
     * @return
     */
    public boolean deleteActivity(Class<?> cls) {
        boolean flag = false;
        for (int i = 0; i < mActivityList.size(); i++) {
            Activity ac = mActivityList.get(i);
            if (ac.getClass().equals(cls)) {
                flag = true;
                mActivityList.remove(i);
            }
        }
        return flag;
    }

    /****
     * 通过 地址 移除容器
     * @param mActivity
     */
    public void finishActivity(Activity mActivity) {
        for (android.app.Activity Activity : mActivityList) {
            if (Activity == mActivity) {
                Activity.finish();
                mActivityList.remove(Activity);
            }
        }
    }

    /****
     * 通过 类名 移除容器
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        Activity ac = null;
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(cls)) {
                ac = activity;
                activity.finish();
            }
        }
        if (null != ac) {
            mActivityList.remove(ac);
        }
    }

    /****
     * 通过 类名 获取Activity 实例
     * @param cls
     */
    public Activity getActivity(Class<?> cls) {
        Activity ac = null;
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(cls)) {
                ac = activity;
            }
        }
        return ac;
    }

    /****
     * 清空容器
     */
    public void clearAllActivity() {
        for (Activity mActivity : mActivityList) {
            mActivity.finish();
        }
        mActivityList.clear();
    }

    /***
     *判断Activity是否存在
     */
    public boolean isExist(Class<?> cls) {
        boolean flag = false;
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(cls)) {
                flag = true;
            }
        }
        return flag;
    }
}
