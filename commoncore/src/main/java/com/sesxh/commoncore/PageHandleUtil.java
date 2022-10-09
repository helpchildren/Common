package com.sesxh.commoncore;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;

/**
 * @auther LYH
 * @date 2020/11/27
 * @time 8:47
 * @desc
 **/
public class PageHandleUtil {
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private int timeout = 20000;
    private Runnable handle;
    private EventListener mEventListener;

    public int getTimeout() {
        return timeout;
    }

    public PageHandleUtil timeout(int timeout) {
        this.timeout = timeout;
        return  this;
    }

    public Runnable getHandle() {
        return handle;
    }

    public PageHandleUtil handle(Runnable handle) {
        this.handle = handle;
        return this;
    }

    public EventListener getEventListener() {
        return mEventListener;
    }

    public PageHandleUtil eventListener(EventListener mEventListener) {
        this.mEventListener = mEventListener;
        return this;
    }

    public void stop() {
        handler.removeCallbacks(handle);
    }

    public void start() {
        if(handle!=null) {
            handler.postDelayed(handle, timeout);
        }
    }

    public static void dispatchTouchEvent(PageHandleUtil handle,MotionEvent event){
        if(handle!=null&&handle.getEventListener()!=null){
            handle.getEventListener().dispatchTouchEvent(event);
        }
    }


    public interface EventListener{
        void dispatchTouchEvent(MotionEvent event);
    }
}
