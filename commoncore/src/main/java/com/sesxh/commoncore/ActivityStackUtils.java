package com.sesxh.commoncore;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.sesxh.commoncore.base.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LYH
 * @date 2021/12/2
 * @time 10:41
 * @desc Activity管理
 **/
public class ActivityStackUtils  {

    private static List<Activity> mActivityStack;
    private static LifecycleCallbacks mCallback;

    private ActivityStackUtils() {
    }

    public void init() {
        if(mCallback==null){
            mActivityStack=new ArrayList<>();
            mCallback=new LifecycleCallbacks();
            Utils.getApp().registerActivityLifecycleCallbacks(mCallback);
        }
    }

    public static ActivityStackUtils getInstance() {
        return SingletonHolder.sInstance;
    }


    /**
     * 获取栈顶Activity
     */
    public Activity getTopActivity() {
        return mActivityStack.get(mActivityStack.size()-1);
    }

    /**
     * 彻底退出
     */
    public void finishAllActivity() {
        for(Activity activity:mActivityStack){
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 查找栈中是否存在指定的activity
     *
     * @param cls
     * @return
     */
    public boolean checkActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * finish指定的activity之上所有的activity
     *
     * @param actCls
     * @param isIncludeSelf
     * @return
     */
    public boolean finishToActivity(Class<? extends Activity> actCls, boolean isIncludeSelf) {
        List<Activity> buf = new ArrayList<Activity>();
        int size = mActivityStack.size();
        Activity activity = null;
        for (int i = size - 1; i >= 0; i--) {
            activity = mActivityStack.get(i);
            if (activity.getClass().isAssignableFrom(actCls)) {
                for (Activity a : buf) {
                    a.finish();
                }
                return true;
            } else if (i == size - 1 && isIncludeSelf) {
                buf.add(activity);
            } else if (i != size - 1) {
                buf.add(activity);
            }
        }
        return false;
    }

    private static class SingletonHolder {
        private static final ActivityStackUtils sInstance = new ActivityStackUtils();
    }
    private static class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks{
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            mActivityStack.add(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            mActivityStack.remove(activity);
        }
    }

}
