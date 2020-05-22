package org.o7planning.simplelistview.DB.RoomDB.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.o7planning.simplelistview.DB.RoomDB.Classes.Word;

import java.util.List;

//A DAO (data access object) validates your SQL at compile-time and associates it with a method.
// In your Room DAO, you use handy annotations, like @Insert, to represent the most common database operations!
// Room uses the DAO to create a clean API for your code.
//The DAO must be an interface or abstract class.
// By default, all queries must be executed on a separate thread.

@Dao
public interface WordDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);   // A method to insert a word

    @Query("DELETE FROM word_table")
    void deleteAllWords();              // A method to delete all words

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();

    @Query("SELECT * FROM word_table")
    LiveData<List<Word>> getAll();

}
