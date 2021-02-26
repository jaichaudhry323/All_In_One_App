package org.o7planning.simplelistview.Umbeo.AppUsageStats.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Locale;

/**
 * Ignore
 * Created by zb on 19/12/2017.
 */
@Entity(tableName = "HistoryItem_table")
public class HistoryItem implements Serializable {

    @NonNull
    @PrimaryKey
    public String mPackageName;

    public String mName;
    public String mDate;
    public int mIsSystem;
    public long mDuration;
    public long mTimeStamp;
    public long mMobileTraffic;

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s %s %s %d %d %d %d", mPackageName, mName, mDate, mIsSystem, mDuration, mTimeStamp, mMobileTraffic);
    }

    public String getmPackageName() {
        return mPackageName;
    }

    public void setmPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getmIsSystem() {
        return mIsSystem;
    }

    public void setmIsSystem(int mIsSystem) {
        this.mIsSystem = mIsSystem;
    }

    public long getmDuration() {
        return mDuration;
    }

    public void setmDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public long getmTimeStamp() {
        return mTimeStamp;
    }

    public void setmTimeStamp(long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public long getmMobileTraffic() {
        return mMobileTraffic;
    }

    public void setmMobileTraffic(long mMobileTraffic) {
        this.mMobileTraffic = mMobileTraffic;
    }
}
