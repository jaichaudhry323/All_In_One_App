package org.o7planning.simplelistview.DB.RoomDB.RoomDB;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import org.o7planning.simplelistview.DB.RoomDB.Classes.User;
import org.o7planning.simplelistview.DB.RoomDB.Classes.Word;
import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.utils.SnackbarUtil;
import org.o7planning.simplelistview.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ActivityRoomDB extends AppCompatActivity {

    Button mViewWordsButton;
    Button mEnterWordDataButton;
    Button mViewUsersButton;
    Button mEnterUserDataButton;
    Button mDeleteUserDataButton;
    Button mDeleteWordDataButton;

    Button mAddUserAttributeToListButton;
    Button mAddUserToListButton;
    Button mFindUserByIdButton;
    Button mEnterUserListDataButton;

    TextView mWordDataTextView;
    TextView mFirstNameTextView;
    TextView mLastNameTextView;
    TextView mUserAttributeTextView;
    TextView mFindUserByIdTextView;

    CoordinatorLayout mCoordinatorLayout;

    // at first wordviewmodel below variable was used to get data from db via word repository
    WordViewModel mWordViewModel;     // can use this as well to
    WordRepository mWordRepository;   // useless from data insertion point pf view

    // converted the wordRepository and WordViewModel into mRoomDbAPI
    RoomDbAPI mRoomDbAPI;             // WordRepository + WordViewModel combined better / simplier version

    ArrayList<String> mUserAttributes;
    ArrayList<User> mUsersList;

    Gson gson=new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_db);

        mViewWordsButton = findViewById(R.id.view_word_button);
        mViewUsersButton = findViewById(R.id.view_user_button);
        mEnterWordDataButton = findViewById(R.id.enter_word_data_button);
        mEnterUserDataButton = findViewById(R.id.enter_user_data_button);
        mDeleteUserDataButton = findViewById(R.id.delete_user_data_button);
        mDeleteWordDataButton = findViewById(R.id.delete_word_data_button);
        mAddUserAttributeToListButton = findViewById(R.id.add_to_list_button);
        mAddUserToListButton = findViewById(R.id.add_user_to_list_button);
        mFindUserByIdButton = findViewById(R.id.find_user_by_id_button);
        mEnterUserListDataButton = findViewById(R.id.enter_user_list_data_button);

        mFirstNameTextView = findViewById(R.id.firstname_roomdb_textview);
        mLastNameTextView = findViewById(R.id.lastname_roomdb_textview);
        mWordDataTextView = findViewById(R.id.word_textview);
        mUserAttributeTextView = findViewById(R.id.user_attribute_textview);
        mFindUserByIdTextView = findViewById(R.id.find_user_by_id_textview);

        mCoordinatorLayout = findViewById(R.id.coordinator_room_db);

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordRepository = new WordRepository(getApplication());
        mRoomDbAPI = new RoomDbAPI(getApplication());

        mUserAttributes = new ArrayList<>();
        mUsersList = new ArrayList<>();

        setupWord();
        setupUser();

        Log.i("ActivityWord", "\n n \n n \n n \n" + Thread.currentThread().toString() + "\n n \n n \n n");

        mRoomDbAPI.getAllUsers().observe(this,users -> {
            if(users!=null && !users.isEmpty()){
                showMessage("From onCreate",gson.toJson(users.get(0)));
            }
        });
    }

    private void setupUser() {

        mEnterUserDataButton.setOnClickListener(v -> {
            String firstname = mFirstNameTextView.getText().toString();
            String lastname = mLastNameTextView.getText().toString();
            String attribute = mUserAttributeTextView.getText().toString();

            if (firstname.equals("") || lastname.equals("")) {
                ToastUtil.makeShortToast(getApplicationContext(), "Cannot insert empty user");
                return;
            }
            if (!attribute.equals("")) {
                mUserAttributes.add(attribute);
            }
            mUserAttributeTextView.setText("");

            ArrayList<String> attr = new ArrayList<>();
            // ALL ATTEMPTS TO DEEP COPY STRING ARRAY FAILED
//            for(String data : mUserAttributes) {
//                attr.add(data+"");
//            }
//            Collections.copy(attr,mUserAttributes);    // no use
//            attr=mUserAttributes.clone();              // error since it is meant for objects and not list or arrays

//            showMessage("Attrs",attr.toString());

            User user = new User(firstname, lastname, attr);
            user.setUserAttributes(mUserAttributes);
            mRoomDbAPI.insertUser(user);

            // the below line gets executed first before addition of data to db since its asynchronous
            // hence the list since its passed by reference becomes empty and hence the list isnt inserted
            // so this was transferred to the observe of livedata of user in this function itself
//            mUserAttributes.clear();
        });

        mViewUsersButton.setOnClickListener(v -> {
            LiveData<List<User>> usersList = mRoomDbAPI.getAllUsers();
            List<User> users = usersList.getValue();

            StringBuilder stringBuffer = new StringBuilder();   // StringBuffer too can be used , search the diff between the two
            int i = 0;
            assert users != null;
            for (User w : users) {
                i++;
                List<String> list = w.getUserAttributes();
                stringBuffer.append(i + ") " + "DBid= " + w.getId() + " " + w.getFirstName() + " " + w.getLastName() + "\n");
                stringBuffer.append(w.getUserAttributes() + "\n");
            }
            showMessage("Users", stringBuffer.toString());
        });

        mRoomDbAPI.getAllUsers().observe(this, users -> {
            StringBuilder stringBuffer = new StringBuilder();   // StringBuffer too can be used , search the diff between the two
            int i = 0;
            for (User w : users) {
                i++;
                stringBuffer.append(i + ") " + "DBid= " + w.getId() + " " + w.getFirstName() + " " + w.getLastName() + "\n");
                stringBuffer.append(w.getUserAttributes() + "\n");
            }
            showMessage("Users", stringBuffer.toString());
            SnackbarUtil.makeShortSnack(mCoordinatorLayout, "User Inserted Successfully");
            mUserAttributes.clear();
        });

        mDeleteUserDataButton.setOnClickListener(v -> {
            mRoomDbAPI.deleteAllUsers();
        });

        mAddUserAttributeToListButton.setOnClickListener(v -> {
            String attribute = mUserAttributeTextView.getText().toString();
            if (attribute.equals("")) {
                ToastUtil.makeShortToast(this, "Cannot add an empty attribute");
                return;
            }
            mUserAttributeTextView.setText("");
            mUserAttributes.add(attribute);
        });

        mAddUserToListButton.setOnClickListener(v -> {
            String firstname = mFirstNameTextView.getText().toString();
            String lastname = mLastNameTextView.getText().toString();
            String attribute = mUserAttributeTextView.getText().toString();

            if (firstname.equals("") || lastname.equals("")) {
                ToastUtil.makeShortToast(getApplicationContext(), "Cannot insert empty user");
                return;
            }
            if (!attribute.equals("")) {
                mUserAttributes.add(attribute);
            }
            mUserAttributeTextView.setText("");
            User user = new User(firstname, lastname, mUserAttributes);
            mUserAttributes.clear();
            mUsersList.add(user);
        });

        mEnterUserListDataButton.setOnClickListener(v -> {
            mRoomDbAPI.insertAllUsers(mUsersList);
        });

        mFindUserByIdButton.setOnClickListener(v->{
            int id;
            try{
                id= Integer.parseInt(mFindUserByIdTextView.getText().toString());
                User user=mRoomDbAPI.getUserById(id);
                showMessage("USER",gson.toJson(user));
            } catch (Exception e) {
                ToastUtil.makeShortToast(this,"Please enter numeric id");
                e.printStackTrace(); }

        });
    }

    private void setupWord() {

        mEnterWordDataButton.setOnClickListener(v -> {
            String data = mWordDataTextView.getText().toString();

            if (data.equals("")) {
                ToastUtil.makeShortToast(getApplicationContext(), "Cannot insert empty word");
                return;
            }
            Word word = new Word(data);
            mRoomDbAPI.insertWord(word);
            mWordDataTextView.setText("");
        });

        mViewWordsButton.setOnClickListener(v -> {
            LiveData<List<Word>> wordsList = mRoomDbAPI.getAllWords();
            List<Word> words = wordsList.getValue();

            StringBuilder stringBuffer = new StringBuilder();   // StringBuffer too can be used , search the diff between the two
            int i = 0;
            assert words != null;
            for (Word w : words) {
                i++;
                stringBuffer.append(i + ") " + w.getWord() + "\n");
            }
            showMessage("Words", stringBuffer.toString());
        });

        // A listener for when database changes
        mRoomDbAPI.getAllWords().observe(this, words -> {
            StringBuilder stringBuffer = new StringBuilder();   // StringBuffer too can be used , search the diff between the two
            int i = 0;
            for (Word w : words) {
                i++;
                stringBuffer.append(i + ") " + w.getWord() + "\n");
            }
//            showMessage("Words", stringBuffer.toString());
            SnackbarUtil.makeShortSnack(mCoordinatorLayout, "Word Inserted Successfully");
        });

        mDeleteWordDataButton.setOnClickListener(v -> {
            mRoomDbAPI.deleteAllWords();
        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(Message);
        alert.show();
    }
}
