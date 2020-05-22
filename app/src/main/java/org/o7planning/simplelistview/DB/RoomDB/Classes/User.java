package org.o7planning.simplelistview.DB.RoomDB.Classes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "user_table")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public ArrayList<String> userAttributes;

    @Ignore
    public User() {

    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getUserAttributes() {
        return userAttributes;
    }

    public void setUserAttributes(ArrayList<String> userAttributes) {
        this.userAttributes = userAttributes;
    }

    // NOTE you need to have the parameters passed in the class's constructor which is being inserted in Room DB
    // to have the names same as that of the class's data members
    public User(String firstName, String lastName, ArrayList<String> userAttributes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAttributes = new ArrayList<>();
        this.userAttributes = new ArrayList<>(userAttributes);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}