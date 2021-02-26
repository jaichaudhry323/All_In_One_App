package org.o7planning.simplelistview.Umbeo.AppUsageStats.app;

import android.app.Application;
import android.content.Intent;

import org.o7planning.simplelistview.BuildConfig;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.DB.RoomDBAPI;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.IgnoreItem;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Service.AppService;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.data.DataManager;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.AppItem;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.util.PreferencesManager;

import java.util.ArrayList;
import java.util.List;


/**
 * My Application
 * Created by zb on 18/12/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesManager.init(this);
        getApplicationContext().startService(new Intent(getApplicationContext(), AppService.class));
//        zz
//        DbIgnoreExecutor.init(getApplicationContext());
//        DbHistoryExecutor.init(getApplicationContext());
        DataManager.init(getApplicationContext());
        addDefaultIgnoreAppsToDB();
//        if (AppConst.CRASH_TO_FILE) CrashHandler.getInstance().init();
    }

    private void addDefaultIgnoreAppsToDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> mDefaults = new ArrayList<>();
                mDefaults.add("com.android.settings");
                mDefaults.add(BuildConfig.APPLICATION_ID);
                for (String packageName : mDefaults) {

//                    AppItem item = new AppItem();
                    IgnoreItem item = new IgnoreItem();

                    item.mPackageName = packageName;
                    item.mCreated = System.currentTimeMillis();
//                    zz
//                    DbIgnoreExecutor.getInstance().insertItem(item);
                    RoomDBAPI.insertIgnoreItem(item);
                }
            }
        }).run();
    }
}
