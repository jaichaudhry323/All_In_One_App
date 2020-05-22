package org.o7planning.simplelistview.DB.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// Press ctrl + click on db.oprtnFuntn to get to know about its functionality
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

        // create an instance of sqllite db
//        SQLiteDatabase db=this.getWritableDatabase();  // was created to view te database creation and nothing else
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // we create table on creation of this class
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER  PRIMARY KEY AUTOINCREMENT , NAME TEXT , SURNAME TEXT ,MARKS INTEGER)");
    }

    @Override   // when is this called ??
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists "+ TABLE_NAME);
    onCreate(db);
    }

    public Boolean insertData(String name,String surname,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
//        contentValues.put(COL_1);
        long result =db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("select * from "+ TABLE_NAME,null);
    }

    public Boolean updateData(String id,String name,String surname,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

        // CHECK FOR A BETTER/EASY MEHTOD or find a good explanation for the last parameter
        // querying on id
        db.update(TABLE_NAME,contentValues,"id = ?",new String[] {id});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"id = ?",new String[] {id});
        // return the number of rows affected or can say deleted
    }

}
