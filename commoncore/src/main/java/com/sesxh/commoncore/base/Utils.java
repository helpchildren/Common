package com.sesxh.commoncore.base;

import android.annotation.SuppressLint;
import android.app.Application;

import com.sesxh.commoncore.ActivityStackUtils;
import com.sesxh.commoncore.ThreadUtils;
import com.sesxh.commoncore.log.Logger;


/**
 * @author LYH
 * @date 2021/1/15
 * @time 14:00
 * @desc
 */


public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Application sApp; // 全局 Application Context

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Init utils.
     *
     * @param builder Builder
     */
    public static void init(Application context,Builder builder) {
        if(builder==null){
            builder=new Builder();
        }
        Logger.sDebug = builder.isDebug;
        if (context == null) {
            throw new IllegalArgumentException("context 不能为null");
        }
        if (sApp == null) {
            sApp = context;
        }
        ActivityStackUtils.getInstance().init();
    }


    /**
     * Init utils.
     *
     * @param app application
     */
    public static void init(final Application app, boolean isDebug) {

    }

    /**
     * @return the Application object
     */
    public static Application getApp() {
        return sApp;
    }



    public static class Builder{
        private boolean isDebug;

        public Builder debug(boolean debug) {
            isDebug = debug;
            return this;
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // interface
    ///////////////////////////////////////////////////////////////////////////

    public abstract static class Task<Result> extends ThreadUtils.SimpleTask<Result> {

        private Consumer<Result> mConsumer;

        public Task(final Consumer<Result> consumer) {
            mConsumer = consumer;
        }

        @Override
        public void onSuccess(Result result) {
            if (mConsumer != null) {
                mConsumer.accept(result);
            }
        }
    }



    public interface Consumer<T> {
        void accept(T t);
    }

    public interface Supplier<T> {
        T get();
    }

    public interface Func1<Ret, Par> {
        Ret call(Par param);
    }
}
