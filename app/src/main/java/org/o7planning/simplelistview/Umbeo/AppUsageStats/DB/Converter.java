package org.o7planning.simplelistview.Umbeo.AppUsageStats.DB;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.AppItem;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.HistoryItem;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.IgnoreItem;

public class Converter {

    @TypeConverter
    public static String saveAppItem(AppItem appItem)
    {
     return new Gson().toJson(appItem);
    }

    @TypeConverter
    public static AppItem restoreAppItem(String appItem)
    {
        return new Gson().fromJson(appItem, new TypeToken<AppItem>() {}.getType());
    }

    @TypeConverter
    public static String saveHistoryItem(HistoryItem historyItem)
    {
        return new Gson().toJson(historyItem);
    }

    @TypeConverter
    public static HistoryItem restoreHistoryItem(String historyItem)
    {
        return new Gson().fromJson(historyItem, new TypeToken<HistoryItem>() {}.getType());
    }

    @TypeConverter
    public static String saveIgnoreItem(IgnoreItem ignoreItem)
    {
        return new Gson().toJson(ignoreItem);
    }

    @TypeConverter
    public static IgnoreItem restoreIgnoreItem(String ignoreItem)
    {
        return new Gson().fromJson(ignoreItem, new TypeToken<AppItem>() {}.getType());
    }

}

