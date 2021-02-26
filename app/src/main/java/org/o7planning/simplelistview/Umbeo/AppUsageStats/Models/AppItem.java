package org.o7planning.simplelistview.Umbeo.AppUsageStats.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Locale;

/**
 * App Item
 * Created by zb on 18/12/2017.
 */

@Entity(tableName = "AppItem_table")
public class AppItem implements Serializable {

    public String mName;

    @NonNull
    @PrimaryKey
    public String mPackageName;

    public long mEventTime;
    public long mUsageTime;
    public int mEventType;
    public int mCount;
    public long mMobile;
    public boolean mCanOpen;
    public boolean mIsSystem;

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "name:%s package_name:%s time:%d total:%d type:%d system:%b count:%d",
                mName, mPackageName, mEventTime, mUsageTime, mEventType, mIsSystem, mCount);
    }

    public AppItem copy() {
        AppItem newItem = new AppItem();
        newItem.mName = this.mName;
        newItem.mPackageName = this.mPackageName;
        newItem.mEventTime = this.mEventTime;
        newItem.mUsageTime = this.mUsageTime;
        newItem.mEventType = this.mEventType;
        newItem.mIsSystem = this.mIsSystem;
        newItem.mCount = this.mCount;
        return newItem;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPackageName() {
        return mPackageName;
    }

    public void setmPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public long getmEventTime() {
        return mEventTime;
    }

    public void setmEventTime(long mEventTime) {
        this.mEventTime = mEventTime;
    }

    public long getmUsageTime() {
        return mUsageTime;
    }

    public void setmUsageTime(long mUsageTime) {
        this.mUsageTime = mUsageTime;
    }

    public int getmEventType() {
        return mEventType;
    }

    public void setmEventType(int mEventType) {
        this.mEventType = mEventType;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public long getmMobile() {
        return mMobile;
    }

    public void setmMobile(long mMobile) {
        this.mMobile = mMobile;
    }

    public boolean ismCanOpen() {
        return mCanOpen;
    }

    public void setmCanOpen(boolean mCanOpen) {
        this.mCanOpen = mCanOpen;
    }

    public boolean ismIsSystem() {
        return mIsSystem;
    }

    public void setmIsSystem(boolean mIsSystem) {
        this.mIsSystem = mIsSystem;
    }
}
