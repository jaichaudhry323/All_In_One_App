package org.o7planning.simplelistview.DB.RoomDB.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.o7planning.simplelistview.DB.RoomDB.Classes.User;

import java.util.List;

@Dao
public interface UserDao {

    // TRY OUT THIS
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void insertUsers(User... users);

    // another option
    // (onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);                          // A method to insert a word
    // get all rows

    @Insert
    void insertAllUsers(List<User>users);

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    // get selective users by their userId
    @Query("SELECT * FROM user_table WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    // getting a specific user acc. to first and last name
    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("Select * from user_table where id = :id limit 1")
    User getUserById(int id);

    @Delete
    void delete(User user);

    @Query("Delete from user_table")
    void deleteAllUsers();

}