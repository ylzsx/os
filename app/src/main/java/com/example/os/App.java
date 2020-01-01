package com.example.os;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.lib_memory.service.MemoryManager;
import com.example.lib_thread.service.ThreadManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 9:14.
 * email yangzhaoxin@hrsoft.net.
 */

public class App extends Application {

    private static List<Activity> sActivityList = new ArrayList<>();

    private static App sInstance;

    private static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sAppContext=getApplicationContext();

        registerActivityLifecycleCallbacks(getActivityLifecycleCallbacks());

        MemoryManager.getInstance().init();
        ThreadManager.getInstance().init();
    }

    public static App getInstance() {
        return sInstance;
    }

    private ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                sActivityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                sActivityList.remove(activity);
            }
        };
    }

    /**
     * 移除Activity
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 移除所有Activity
     */
    public void removeAllActivity() {
        for (Activity activity : sActivityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
