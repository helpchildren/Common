package com.sesxh.commoncore.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.DimenRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import com.sesxh.commoncore.CountDownTimerUtil;
import com.sesxh.commoncore.KeyboardUtils;
import com.sesxh.commoncore.R;
import com.sesxh.commoncore.SizeUtils;
import com.sesxh.commoncore.scancode.OnScanValueListener;
import com.sesxh.commoncore.scancode.ScanGunEvent;
import com.sesxh.commoncore.scancode.UsbScanCodeManager;


public class BaseDialog extends Dialog {



    private Context context;
    private int height = WindowManager.LayoutParams.WRAP_CONTENT, width = WindowManager.LayoutParams.WRAP_CONTENT, gravity = getGravity();
    private View view;
    private static int resStyle = R.style.dialog;
    private Handler mHandler = new Handler();
    private CountDownTimerUtil mTimer;
    private CountDownListener mCountDownListener;
    private int millisInFuture;
    private boolean mIsHideKeyboardNotInEtv=true;

    private final Runnable mAutoDelayed = new Runnable() {

        @Override
        public void run() {
            hideDialog();
        }
    };
    private UsbScanCodeManager scanKeyManager = new UsbScanCodeManager(new OnScanValueListener() {
        @Override
        public void onScanValue(String barcode) {
            onScanGunEvent(barcode);
        }

        @Override
        public void onError(String s) {

        }
    });

    public BaseDialog(@NonNull Context context) {
        super(context, resStyle);
        this.context = context;
    }

    public BaseDialog(@NonNull Context context, @LayoutRes int resView) {
        this(context,resStyle,resView);
    }

    public BaseDialog(@NonNull Context context, View view) {
        this(context,resStyle,view);
    }

    public BaseDialog(@NonNull Context context,@StyleRes int themeResId, @LayoutRes int resView) {
        super(context, themeResId);
        this.context = context;
        view(resView);
    }



    public BaseDialog(@NonNull Context context,@StyleRes int themeResId, View view) {
        super(context, themeResId);
        this.context = context;
        view(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        setCanceledOnTouchOutside(getCanceledOnTouchOutside());
        setCancelable(getCancelable());
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = gravity;
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);
        bindView(view);
    }


    public BaseDialog countDownListener(CountDownListener mCountDownListener) {
        this.mCountDownListener = mCountDownListener;
        return this;
    }

    public BaseDialog millisInFuture(int millisInFuture) {
        this.millisInFuture = millisInFuture;
        return this;
    }

    public int getMillisInFuture() {
        return millisInFuture;
    }

    public BaseDialog setHideKeyboardNotInEtv(boolean hideKeyboardNotInEtv) {
        mIsHideKeyboardNotInEtv = hideKeyboardNotInEtv;
        return this;
    }

    public void showDialog() {
        showDialog(0);
    }

    public void showDialog(long hideDelay) {
        show();
        startCountDown();
        autoHide(hideDelay);
    }

    public void autoHide(long hideDelay) {
        if (hideDelay > 0) {
            mHandler.postDelayed(mAutoDelayed, hideDelay);
        }
    }

    public void startCountDown() {
        if (millisInFuture > 0) {
            startTimer(millisInFuture);
        }
    }

    public void hideDialog() {
        mHandler.removeCallbacks(mAutoDelayed);
        dismiss();
    }

    public void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = null;
    }

    private void startTimer(int millisInFuture) {
        cancelTimer();
        mTimer = CountDownTimerUtil.getCountDownTimer()
                .setMillisInFuture(millisInFuture * 1000 + 1999)
                .setCountDownInterval(1000)
                .setDelegate(new CountDownTimerUtil.Delegate() {
                    @Override
                    public void onTick(long pMillisUntilFinished) {
                        long time = pMillisUntilFinished / 1000 - 1;
                        if(time>0){
                            if (mCountDownListener != null) {
                                mCountDownListener.onTick(time);
                            }
                        }else {
                            onFinish();
                        }

                    }

                    @Override
                    public void onFinish() {
                        if (mCountDownListener != null) {
                            mCountDownListener.onFinish();
                        }
                        cancelTimer();
                        hideDialog();
                    }
                }).create();
        mTimer.start();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (ScanGunEvent.isScanGunEvent(event)){
            scanKeyManager.analysisKeyEvent(event);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (mIsHideKeyboardNotInEtv&&KeyboardUtils.isShouldHideInput(v, event)) {//点击editText控件外部
                KeyboardUtils.hideSoftInput(v);
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public void isScanListener(boolean scanListener) {
        ScanGunEvent.setScan(scanListener);
    }

    protected void onScanGunEvent(String code){

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mAutoDelayed);
        cancelTimer();
        isScanListener(false);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        cancelTimer();
    }

    public BaseDialog view(int resView) {
        view = LayoutInflater.from(context).inflate(resView, null);
        return this;
    }

    public BaseDialog view(View view) {
        this.view = view;
        return this;
    }

    public BaseDialog height(int val) {
        if (val != ViewGroup.LayoutParams.MATCH_PARENT && val != ViewGroup.LayoutParams.WRAP_CONTENT)
            height = SizeUtils.dp2px(val);
        else
            height = val;
        return this;
    }

    public BaseDialog heightPx(int val) {
        height = val;
        return this;
    }

    public BaseDialog width(int val) {
        if (val != ViewGroup.LayoutParams.MATCH_PARENT && val != ViewGroup.LayoutParams.WRAP_CONTENT)
            width = SizeUtils.dp2px(val);
        else
            width = val;
        return this;
    }

    public BaseDialog widthPx(int val) {
        width = val;
        return this;
    }

    public BaseDialog heightDimen(@DimenRes int dimenRes) {
        height = context.getResources().getDimensionPixelOffset(dimenRes);
        return this;
    }

    public BaseDialog widthDimen(@DimenRes int dimenRes) {
        width = context.getResources().getDimensionPixelOffset(dimenRes);
        return this;
    }



    public int getGravity() {
        return this.gravity;
    }

    protected void bindView(View v) {

    }


    public boolean getCanceledOnTouchOutside() {
        return false;
    }

    public boolean getCancelable() {
        return false;
    }

    public View getView(@IdRes int id) {
        return view.findViewById(id);
    }

    public interface CountDownListener {
        void onTick(long time);

        void onFinish();
    }

}
