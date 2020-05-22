package org.o7planning.simplelistview.DB.RoomDB.RoomDB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.o7planning.simplelistview.DB.RoomDB.Classes.Word;
import org.o7planning.simplelistview.DB.RoomDB.Dao.WordDao;

import java.util.List;

// NOTE Returning the LiveData wrapper around the Member type
// automatically signals to Room that the query can/should be performed asynchronously.
// Per https://developer.android.com/training/data-storage/room/accessing-data (my emphasis):

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mWordDao = db.getWordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Word word) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }

}
