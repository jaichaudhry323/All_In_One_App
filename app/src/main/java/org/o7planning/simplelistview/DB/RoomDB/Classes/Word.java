package org.o7planning.simplelistview.DB.RoomDB.Classes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    // auto generation of primary key
  /*  @PrimaryKey(autoGenerate = true)     //
    private int id;*/

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")  // if we want the column name to be different from variable name i.e mWord
    private String mWord;

    public Word(String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}
}