package com.chinamobile.onenet.mymvp.arms.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name: ${CrashHanlderExcetpion}
 * @Description: 记录崩溃日志 只需要在app中调用CrashHanlderExcetpion.getInstance().init(this);
 * @Author: chenhao
 * @Create Date: 2017-03-06
 */

public class CrashHanlderExcetpion implements Thread.UncaughtExceptionHandler {
    public static final String TAG = CrashHanlderExcetpion.class.getSimpleName();

    private Context mContext;

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private Map<String, String> infos = new HashMap<String, String>();

    private static CrashHanlderExcetpion INSTANCE = new CrashHanlderExcetpion();

    private CrashHanlderExcetpion() {
    }

    public static CrashHanlderExcetpion getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "抱歉，出现异常,即将退出", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collectDeviceInfo(mContext);
        dumpCrashInfo2File(ex, "crash");
        dumpHprof2File(ex, "crash");
        return true;
    }

    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    private void dumpCrashInfo2File(Throwable ex, String dir) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + dir;
                File fileDir = new File(path);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                File crashFile = new File(fileDir, "crash-" + getTimeString() + ".txt");
                FileOutputStream fos = new FileOutputStream(crashFile);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
    }

    private void dumpHprof2File(Throwable ex, String dir) {
        if (null == ex)
            return;

        Throwable thr = ex;
        while (null != thr) {
            String cls = thr.getClass().getName();
            if (null != cls && cls.contains("OutOfMemoryError")) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + dir;
                File fileDir = new File(path);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                File hprofFile = new File(fileDir, "hprof-" + getTimeString() + ".hprof");
                try {
                    Debug.dumpHprofData(hprofFile.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            thr = thr.getCause();
        }
    }

    private String getTimeString() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }
}
