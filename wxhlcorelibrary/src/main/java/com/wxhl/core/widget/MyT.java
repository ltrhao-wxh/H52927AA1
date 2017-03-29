package com.wxhl.core.widget;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wxhl.core.R;

/**
 * 自定义Toast  可以在左上角设置图片
 * Created by wuxiaohui on 17/3/2.
 */

public class MyT {
    /**
     * 自定义Toast
     * @param activity  类所在的Activity对象
     * @param massage   要显示的信息
     * @param show_length  显示时间的长短, 常用Toast.LENGTH_LONG ,  Toast.LENGTH_SHORT
     */
    public static  void makeshow(Activity activity, String massage, int show_length){
        //使用布局加载器，将编写的toast_layout布局加载进来
        View view = LayoutInflater.from(activity).inflate(R.layout.toast_layout, null);
        //获取ImageView
        ImageView image = (ImageView) view.findViewById(R.id.toast_iv);
        //设置图片
        image.setImageResource(R.drawable.image_nor);
        //获取TextView
        TextView title = (TextView) view.findViewById(R.id.toast_tv);
        //设置显示的内容
        title.setText(massage);
        Toast toast = new Toast(activity);
        //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移200个单位，
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 200);
        //设置显示时间
        toast.setDuration(show_length);

        toast.setView(view);
        toast.show();
    }


    /**
     * 短时间show
     * @param activity
     * @param massage
     */
    public static  void makeshowShort(Activity activity, String massage){
        //使用布局加载器，将编写的toast_layout布局加载进来
        View view = LayoutInflater.from(activity).inflate(R.layout.toast_layout, null);
        //获取ImageView
        ImageView image = (ImageView) view.findViewById(R.id.toast_iv);
        //设置图片
        image.setImageResource(R.drawable.image_nor);
        //获取TextView
        TextView title = (TextView) view.findViewById(R.id.toast_tv);
        //设置显示的内容
        title.setText(massage);
        Toast toast = new Toast(activity);
        //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移200个单位，
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 200);
        //设置显示时间
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setView(view);
        toast.show();
    }


    /**
     * 长时间show
     * @param activity
     * @param massage
     */
    public static  void makeshowLong(Activity activity, String massage){
        //使用布局加载器，将编写的toast_layout布局加载进来
        View view = LayoutInflater.from(activity).inflate(R.layout.toast_layout, null);
        //获取ImageView
        ImageView image = (ImageView) view.findViewById(R.id.toast_iv);
        //设置图片
        image.setImageResource(R.drawable.image_nor);
        //获取TextView
        TextView title = (TextView) view.findViewById(R.id.toast_tv);
        //设置显示的内容
        title.setText(massage);
        Toast toast = new Toast(activity);
        //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移70个单位，
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 200);
        //设置显示时间
        toast.setDuration(Toast.LENGTH_LONG);

        toast.setView(view);
        toast.show();
    }

}
