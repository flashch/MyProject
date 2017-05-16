package com.chinamobile.onenet.mymvp.arms.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 专门访问和设置SharePreference的工具类, 保存和配置一些设置信息
 * 
 * @author chenhao
 * 
 */
public class SpUtils {

	private static final String SHARE_PREFS_NAME = "oneNet";
	private static SharedPreferences pref;

	public static void putBoolean(Context ctx, String key, boolean value) {
		pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		pref.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context ctx, String key,
									 boolean defaultValue) {
		pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		return pref.getBoolean(key, defaultValue);
	}

	public static void putString(Context ctx, String key, String value) {
		pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		pref.edit().putString(key, value).commit();
	}

	public static String getString(Context ctx, String key, String defaultValue) {
		pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		return pref.getString(key, defaultValue);
	}

	public static void putInt(Context ctx, String key, int value) {
		pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		pref.edit().putInt(key, value).commit();
	}

	public static int getInt(Context ctx, String key, int defaultValue) {
		pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		return pref.getInt(key, defaultValue);
	}

}
