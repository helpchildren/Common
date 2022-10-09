package com.sesxh.commoncore.scancode;

public interface OnScanValueListener {
    /*
    * 扫描碗回调
    * */
    void onScanValue(String value);

    /*
    * 异常回调
    * */
    void onError(String value);

}
