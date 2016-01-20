package com.bluelife.test.filedesccrashtest;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by slomka.jin on 2016/1/20.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900018372", true);
    }
}
