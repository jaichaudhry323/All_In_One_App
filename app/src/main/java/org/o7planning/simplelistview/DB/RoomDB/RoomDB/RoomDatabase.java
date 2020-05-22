package org.o7planning.simplelistview.DB.RoomDB.RoomDB;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.o7planning.simplelistview.DB.RoomDB.Classes.Converter;
import org.o7planning.simplelistview.DB.RoomDB.Classes.User;
import org.o7planning.simplelistview.DB.RoomDB.Dao.UserDao;
import org.o7planning.simplelistview.DB.RoomDB.Classes.Word;
import org.o7planning.simplelistview.DB.RoomDB.Dao.WordDao;
import org.o7planning.simplelistview.utils.ToastUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//What is a Room database?
//Room is a database layer on top of an SQLite database.
//Room takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
//Room uses the DAO to issue queries to its database.
//By default, to avoid poor UI performance, Room doesn't allow you to issue queries on the main thread.
//When Room queries return LiveData, the queries are automatically run asynchronously on a background thread.

// Entity: Represents a table within the database.
// DAO: Contains the methods used for accessing the database.

@Database(entities = {Word.class, User.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract WordDao getWordDao();
    public abstract UserDao getUserDao();
    private static volatile RoomDatabase INSTANCE;

//# We've created an ExecutorService with a fixed thread pool that you will use
//# to run database operations asynchronously on a background thread.
//# when you dont want to save db by using migration data after changing the schema
//# then add fallbacktodestructivemigration() before build()

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            ToastUtil.makeShortToast(context,"Database created");
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "database")
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        else{
            ToastUtil.makeShortToast(context,"Database Already exists");
        }
        return INSTANCE;
    }

    private static androidx.room.RoomDatabase.Callback sRoomDatabaseCallback = new androidx.room.RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                WordDao dao = INSTANCE.getWordDao();
//                dao.deleteAll();
//
//                Word word = new Word("Hello");
//                dao.insert(word);
//                word = new Word("World");
//                dao.insert(word);
//            });
        }
    };
}
