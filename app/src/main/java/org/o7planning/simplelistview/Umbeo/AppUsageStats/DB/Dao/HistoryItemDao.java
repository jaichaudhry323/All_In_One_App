package org.o7planning.simplelistview.Umbeo.AppUsageStats.DB.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.o7planning.simplelistview.Umbeo.AppUsageStats.Models.HistoryItem;

import java.util.List;

@Dao
public interface HistoryItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HistoryItem historyItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<HistoryItem> historyItems);

    @Query("select * from HistoryItem_table")
    List<HistoryItem> getData();

//    @Query("update EventDetailsResponse_table set inviteMessage = :newInviteMessage where id = :id")
//    void updateInviteMessage(String newInviteMessage, UUID id);

//    @Query("select * from EventDetailsResponse_table")
//    LiveData<List<EventDetailsResponse>> getLiveData();


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
