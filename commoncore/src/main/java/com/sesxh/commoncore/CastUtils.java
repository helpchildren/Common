package com.sesxh.commoncore;

/**
 * @author LYH
 * @date 2021/1/5
 * @time 19:10
 * @desc 类型强转
 */

public class CastUtils {

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

}
