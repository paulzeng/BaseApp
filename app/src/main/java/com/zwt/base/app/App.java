package com.zwt.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * 继承自Application,完成全局初始化
 * Created by ZWT on 2016/9/9.
 */
public class App extends Application{
    public static App mInstance;
    public static Context mContext;
    public static Handler mHandler;
    public static int mainThreadId;
    public static List<Activity> allActivity = new LinkedList<Activity>();
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
        mHandler = new Handler();
        mainThreadId = android.os.Process.myTid();//获取主线程的ID
        initImageLoader(mContext);
    }

    /**
     * 初始化图片加载
     *
     * @param context 上下文对象
     */
    public void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), "MyApp/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(3).denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(100)
                .diskCache(new UnlimitedDiskCache(cacheDir)).build();
        L.writeLogs(false);
        ImageLoader.getInstance().init(config);
    }

    /**
     * 销毁所有activity，应用退出
     */
    public void exit() {
        for (Activity activity : allActivity) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
