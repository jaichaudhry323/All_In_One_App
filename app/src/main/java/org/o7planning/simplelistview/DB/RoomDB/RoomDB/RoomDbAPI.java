package org.o7planning.simplelistview.DB.RoomDB.RoomDB;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;

import org.o7planning.simplelistview.DB.RoomDB.Classes.User;
import org.o7planning.simplelistview.DB.RoomDB.Dao.UserDao;
import org.o7planning.simplelistview.DB.RoomDB.Classes.Word;
import org.o7planning.simplelistview.DB.RoomDB.Dao.WordDao;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RoomDbAPI extends AndroidViewModel {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    public RoomDbAPI(@NonNull Application application) {
        super(application);
        RoomDatabase roomDatabase = RoomDatabase.getDatabase(application);
        mWordDao = roomDatabase.getWordDao();
        mUserDao = roomDatabase.getUserDao();

        mAllWords = mWordDao.getAlphabetizedWords();
        mAllUsers = mUserDao.getAllUsers();
    }

    LiveData<List<Word>> getAllWords() {
        Log.i("ROOM_API", "\n n \n n \n n \n" + Thread.currentThread().toString() + "\n n \n n \n n");
//        return mWordDao.getAlphabetizedWords();    // this wont work
        return mAllWords;
    }

    void insertWord(Word word) {
        Log.i("ROOM_API", "\n n \n n \n n \n" + Thread.currentThread().toString() + "\n n \n n \n n");
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }

    void deleteAllWords() {
        // WRONG Since it is a database operation so need to e inside database executor thing
//        mWordDao.deleteAllWords();
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.deleteAllWords();
        });
    }

    //-------------------------------------------------------------------
    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //-------------------------------------------------------------------


    // Users from here onwards
    LiveData<List<User>> getAllUsers() {
//        return mWordDao.getAlphabetizedWords();    // this wont work
        return mAllUsers;
    }

    void insertAllUsers(List<User> users) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insertAllUsers(users);
        });
    }

    void insertUser(User user) {
        Log.i("ROOM_API", "\n n \n n \n n \n" + Thread.currentThread().toString() + "\n n \n n \n n");
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    User getUserById(int id) {
        final User[] user = new User[1];
        user[0] = mUserDao.getUserById(id);

//        RoomDatabase.databaseWriteExecutor.execute(()->{
//                    user[0] =mUserDao.getUserById(id);
//        });

//        Timer timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                RoomDatabase.databaseWriteExecutor.execute(()->{
////                    user[0] =mUserDao.getUserById(id);
//                });
//            }
//        }, 1000);
        return user[0];
    }

    void deleteAllUsers() {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.deleteAllUsers();
        });
    }

}
