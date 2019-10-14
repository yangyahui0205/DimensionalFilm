package com.baway.dimensionalfilm;

import android.app.Application;
import android.widget.Button;

import com.liulishuo.filedownloader.FileDownloader;

/*
 *@Auther:Helen
 *@Date: 19.8.8
 *@Time: 20:29:26
 *@Description:
 * */public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileDownloader.setupOnApplicationOnCreate(this);

    }
}
