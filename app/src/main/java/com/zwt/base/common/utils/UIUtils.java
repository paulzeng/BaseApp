package com.zwt.base.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.zwt.base.app.App;


/**
 * 封装的目的，让以后用起来更简单
 * Created by ZWT on 2016/9/9.
 */
public class UIUtils {
    public static Context getContext(){
        return App.mContext;
    }
    public static int getMainThreadId(){
        return App.mainThreadId;
    }
    public static Handler getHandler(){
        return App.mHandler;
    }

    ////////////////加载资源文件////////////////////
    public static String getString(int id){
        return getContext().getResources().getString(id);
    }

    public static String[] getStringArray(int id){
        return getContext().getResources().getStringArray(id);
    }

    public static Drawable getDrawable(int id){
        return getContext().getResources().getDrawable(id);
    }

    public static int getColor(int id){
        return getContext().getResources().getColor(id);
    }

    public static int getDimen(int id){
        return getContext().getResources().getDimensionPixelSize(id);//返回具体的像素值
    }

    ////////////////dp和pix转换////////////////////
    public static int dip2px(float dip){
        //获取设备密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(dip*density+0.5f);
    }

    public static float px2dip(int px){
        //获取设备密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return px/density;
    }

    ////////////////加载布局文件////////////////////
    public static View inflate(int id){
        return View.inflate(getContext(),id,null);
    }

    ////////////////判断是否运行在主线程////////////////////
    public static boolean isRunOnUIThread(){
        //获取当前线程ID，判断是否与主线程ID相同
        int myTid = android.os.Process.myPid();
        if(myTid == getMainThreadId()){
            return true;
        }
        return false;
    }

    public static void runOnUIThread(Runnable r){
        if(isRunOnUIThread()){
            //已经是主线程
            r.run();
        }else{
            //如果是子线程，借助Handler让其运行在主线程
            getHandler().post(r);
        }
    }
}
