package org.o7planning.simplelistview.Umbeo.AppUsageStats.DB.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.HistoryItem;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.IgnoreItem;

import java.util.List;

@Dao
public interface IgnoreItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IgnoreItem ignoreItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<IgnoreItem> ignoreItems);

    @Query("select * from IgnoreItem_table")
    List<IgnoreItem> getData();

//    @Query("update EventDetailsResponse_table set inviteMessage = :newInviteMessage where id = :id")
//    void updateInviteMessage(String newInviteMessage, UUID id);
//

//    @Query("select * from EventDetailsResponse_table")
//    List<EventDetailsResponse> getData();
//
//    @Query("select * from EventDetailsResponse_table where id = :id")
//    EventDetailsResponse getDataById(UUID id);
//
//    @Query("SELECT COUNT(*) from EventDetailsResponse_table")
//    LiveData<String> isDataAvailableLiveData();
//
//    @Query("SELECT COUNT(*) from EventDetailsResponse_table")
//    String isDataAvailable();
//
//    @Query("Delete from EventDetailsResponse_table")
//    void delete();
//
//    @Query("Delete from EventDetailsResponse_table where id = :uuid")
//    void deleteById(UUID uuid);

}
