package com.sesxh.commoncore;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.sesxh.commoncore.base.Utils;

/**
 * @auther LYH
 * @date 2020/12/4
 * @time 9:13
 * @desc 阿里巴巴矢量图工具类
 **/
public class IconFontUtil {

    private static final Context context= Utils.getApp();

    public static void  setText(TextView tv, @StringRes int text){
       setText("iconfont.ttf",tv,text);
    }
    public static void  setText(String iconPath, TextView tv, @StringRes int text){
        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), iconPath);
        tv.setTypeface(iconfont);
        tv.setText(context.getString(text));
    }
}
