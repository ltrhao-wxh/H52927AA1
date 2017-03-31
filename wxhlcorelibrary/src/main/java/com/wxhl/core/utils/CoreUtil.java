package com.wxhl.core.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.wxhl.core.network.NetWorkStateService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stay  hungry , Stay  foolish
 *
 * @author YuanDong Qiao
 * Create on 2016/11/22  20:27
 * Copyright(c) 2016 www.wuxianedu.com Inc. All rights reserved.
 */
public class CoreUtil {

	//加载对话框
	private static ProgressDialog pd;
	private static float sDensity = 0;
	static List<FragmentActivity> list = new ArrayList<FragmentActivity>();

	/**
	 * 把所有打开的activity加进集合
	 * @param fActivity
	 */
	public static void addToActivityList(FragmentActivity fActivity){
		if(!list.contains(fActivity)){
			list.add(fActivity);
		}
	}

	/**
	 * 关闭activity集合
	 */
	public static void finishActivityList(){
		LogUtil.e("activity集合大小-----》》"+list.size());
		for (int i = 0; i < list.size(); i++) {
			LogUtil.e("被关闭fragmentActivity-----》》"+list.get(i));
			list.get(i).finish();
		}
		list.clear();
	}

	/**
	 * 关闭activity 并杀死进程。
	 */
	public static void exitApp(){
		for (FragmentActivity activity : list) {
			activity.finish();
			LogUtil.e("exitApp:-----》》"+activity);
		}
		list.clear();
		LogUtil.e("android.os.Process.killProcess(android.os.Process.myPid());");
		android.os.Process.killProcess(android.os.Process.myPid());
	}


	/**
	 * 开启加载提示对话款
	 */
	public static void showProDialog(Context context) {
		if(pd == null ){
			pd = new ProgressDialog(context);
			pd.setMessage("加载中，请稍后...");
			pd.setCancelable(false);
		}
		if(pd.isShowing()){
//			pd.dismiss();
			return;
		}
		pd.show();
	}
	/**
	 * 关闭加载提示对话款
	 */
	public static void dismissProDialog() {
		if(pd != null ){
			pd.dismiss();
		}
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * @param context
	 * @param spValue
	 * @return
	 */
	public static int spToPixel(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * dp转换为像素
	 * @param context
	 * @param nDip
	 * @return
	 */
	public static int dipToPixel(Context context, int nDip) {
		if (sDensity == 0) {
			final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics dm = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(dm);
			sDensity = dm.density;
		}
		return (int) (sDensity * nDip);
	}

	/**
	 * 获取屏幕 宽 高
	 * @param activity
	 * @return 数组  0 为宽度 1 为高度
	 */
	public static int[] getDisplay(Activity activity){
		int[] display = new int[2];
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		display[0] = dm.widthPixels;
		display[1] = dm.heightPixels;
		return display;
	}

	/**
	 * 判断是否为中文
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str){
		Pattern p=Pattern.compile("^[\u4e00-\u9fa5]*$");
		Matcher m=p.matcher(str);
		if(m.matches()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断是否为字母
	 * @param
	 * @return
	 */
	public static boolean isEnglish(String fstrData){
		char   c   =   fstrData.charAt(0);
		if(((c>='a'&&c<='z')   ||   (c>='A'&&c<='Z'))){
			return   true;
		}else{
			return   false;
		}
	}

	public static ArrayList<Activity> ALL_ACTIVITY=new ArrayList<Activity>();
	
	/**
	 * @Description: 添加Activity到列表中
	 * @param activity
	 */
	public static void addAppActivity(Activity activity){
		if(!ALL_ACTIVITY.contains(activity)){
			ALL_ACTIVITY.add(activity);
		}
	}
	
	/**
	 * @Description: 从列表移除Activity
	 * @param activity
	 */
	public static void removeAppActivity(Activity activity){
		if(ALL_ACTIVITY.contains(activity)){
			ALL_ACTIVITY.remove(activity);
		}
	}
	
	/**
	 * @Description: 退出应用程序
	 */
	public static void exitApp(Context context){
		//关闭网络状态监听器
		context.stopService(new Intent(context, NetWorkStateService.class));

		L.e("--- 销毁 Activity size--->>:" + ALL_ACTIVITY.size());
		for (Activity ac : ALL_ACTIVITY) {
			if(!ac.isFinishing()){
				ac.finish();
			}
		}
		ALL_ACTIVITY.clear();

		android.os.Process.killProcess(android.os.Process.myPid());
//		HttpClient httpClient = CoreHttpClient.getInstance();
//		if (httpClient != null && httpClient.getConnectionManager() != null) {
//			httpClient.getConnectionManager().shutdown();
//		}
	}

}
