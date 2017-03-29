package com.wxhl.core.utils;

import android.util.Log;

/**
 * Created by wuxiaohui on 17/2/23.
 */
public class LogUtil {
    private static final String TAG="--Main--";

    public static void e(Object obj){
        Log.e(TAG, obj.toString());
    }
    public static void i(Object obj){
        Log.i(TAG, obj.toString());
    }
    public static void w(Object obj){
        Log.w(TAG, obj.toString());
    }
    public static void d(Object obj){
        Log.d(TAG, obj.toString());
    }
}
