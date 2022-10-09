package com.sesxh.commoncore;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

/**
 * @author LYH
 * @date 2021/4/9
 * @time 10:21
 * @desc
 **/
public class HandlerPollingUtils {

    private Handler mHandler;
    private int mTotalCount;
    private int mCurrentCount;
    private OnPollingListener mListener;
    public static final int COUNT_MAX = -1;
    private static final int WHAT_POLLING=1;

    public HandlerPollingUtils(OnPollingListener listener) {
        this(COUNT_MAX,listener);
    }
    public HandlerPollingUtils(int totalCount, OnPollingListener listener) {
        this.mHandler= new MyHandler(listener);
        this.mTotalCount=totalCount;
        this.mListener=listener;
    }


    public void polling( final long delay) {
        if(mCurrentCount>=mTotalCount&&mTotalCount!=COUNT_MAX){
            cancel();
        }else {
            mCurrentCount++;
            if(delay>0) {
                mHandler.sendEmptyMessageDelayed(WHAT_POLLING, delay);
            }else {
                mHandler.sendEmptyMessage(WHAT_POLLING);
            }
        }
    }

    public void cancel() {
        if (mHandler != null) {
            mHandler.removeMessages(WHAT_POLLING);
            mHandler=null;
        }
        if(mListener!=null){
            mListener.onPollingFinish();
        }
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public interface OnPollingListener{
        void onPollingStart();
        void onPollingFinish();
    }


    private static class MyHandler extends Handler{

        private OnPollingListener mListener;

        public MyHandler(OnPollingListener listener) {
            mListener = listener;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==WHAT_POLLING){
                removeMessages(WHAT_POLLING);
                mListener.onPollingStart();
            }
        }
    }
}
