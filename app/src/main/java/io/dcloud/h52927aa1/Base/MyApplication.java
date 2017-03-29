package io.dcloud.h52927aa1.Base;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wuxiaohui on 17/3/14.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);   //初始化xutils
        x.Ext.setDebug(false); // 是否输出debug日志
    }
}
