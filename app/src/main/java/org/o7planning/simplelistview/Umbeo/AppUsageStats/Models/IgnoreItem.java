package org.o7planning.simplelistview.Umbeo.AppUsageStats.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Ignore
 * Created by zb on 19/12/2017.
 */
@Entity(tableName = "IgnoreItem_table")
public class IgnoreItem implements Serializable {

    @NonNull
    @PrimaryKey
    public String mPackageName;

    public long mCreated;
    public String mName;

    public String getmPackageName() {
        return mPackageName;
    }

    public void setmPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public long getmCreated() {
        return mCreated;
    }

    public void setmCreated(long mCreated) {
        this.mCreated = mCreated;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
