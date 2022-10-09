package com.sesxh.commoncore.scancode;

import android.view.KeyEvent;

/**
 * @author LYH
 * @date 2021/5/12
 * @time 17:33
 * @desc 扫码枪事件
 **/
public class ScanGunEvent {

    private String mCode;
    private static final int DEVICE_ID=-1;
    private volatile static boolean sIsScan;// 是否需要处理扫码事件

    public ScanGunEvent(String code) {
        this.mCode = code;
    }

    public String getCode() {
        return mCode;
    }

    public static boolean isScanGunEvent(KeyEvent event){
        if(!sIsScan){
            return false;
        }
        if(event==null){
            return false;
        }
        return DEVICE_ID!=event.getDeviceId();
    }
    public static boolean isScan() {
        return sIsScan;
    }
    public synchronized static void setScan(boolean isScan) {
        sIsScan = isScan;
    }
}
