package com.sesxh.commondemo;

import android.app.Application;

import com.sesxh.commoncore.base.Utils;
import com.sesxh.commoncore.log.CrashHandler;
import com.sesxh.commoncore.log.XLogUtils;


/**
 * @author LYH
 * @date 2021/1/18
 * @time 16:17
 * @desc
 **/
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this,new Utils.Builder()
                .debug(true));
        //日志打印初始化
        XLogUtils.getInstance()
                .setDebugLog(true)
                .setFoldername("sesxh/"+getPackageName()+"/ToolXLog")
                .XlogInit();
        //崩溃打印初始化
        CrashHandler.getInstance(getApplicationContext())
                .setFoldername("sesxh/"+getPackageName()+"/ToolCrash")
                .init();
    }
}
