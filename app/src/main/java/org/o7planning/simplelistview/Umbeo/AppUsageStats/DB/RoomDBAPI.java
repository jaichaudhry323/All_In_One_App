package org.o7planning.simplelistview.Umbeo.AppUsageStats.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.o7planning.simplelistview.Umbeo.AppUsageStats.DB.Dao.AppItemDao;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.DB.Dao.HistoryItemDao;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.DB.Dao.IgnoreItemDao;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.HistoryItem;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.IgnoreItem;

import java.util.ArrayList;
import java.util.List;

public class RoomDBAPI extends AndroidViewModel {

//    private static HomePageResponseDao mHomePageResponseDao;

    private static AppItemDao mAppItemDao;
    private static IgnoreItemDao mIgnoreItemDao;
    private static HistoryItemDao mHistoryItemDao;

    public RoomDBAPI(@NonNull Application application) {
        super(application);
        RoomDatabase roomDatabase = RoomDatabase.getDatabase(application);

        mAppItemDao = roomDatabase.getAppItemDao();
        mHistoryItemDao = roomDatabase.getHistoryItemDao();
        mIgnoreItemDao = roomDatabase.getAIgnoreItemDao();

    }


    public static void insertIgnoreItem(IgnoreItem ignoreItem)
    {
        RoomDatabase.databaseWriteExecutor.execute(()->{
            mIgnoreItemDao.insert(ignoreItem);
        });
    }

    public static void insertHistoryItem(HistoryItem historyItem)
    {
        RoomDatabase.databaseWriteExecutor.execute(()->{
            mHistoryItemDao.insert(historyItem);
        });
    }

    public static void insertIgnoreItemList(List<IgnoreItem> ignoreItems)
    {
        RoomDatabase.databaseWriteExecutor.execute(()->{
            mIgnoreItemDao.insertAll(ignoreItems);
        });
    }

    public static void insertHistoryItemList(List<HistoryItem> historyItems)
    {
        RoomDatabase.databaseWriteExecutor.execute(()->{
            mHistoryItemDao.insertAll(historyItems);
        });
    }

    public static List<IgnoreItem> getIgnoreItemList()
    {
        return mIgnoreItemDao.getData();
    }

    public static List<HistoryItem> getHistoryItemList()
    {
        return mHistoryItemDao.getData();
    }

}

