package org.o7planning.simplelistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;

import org.o7planning.simplelistview.API.MainActivity_API;
import org.o7planning.simplelistview.ArrayAdapterExample.BasicArrayAdapter;
import org.o7planning.simplelistview.DB.MainActivity_DB;
import org.o7planning.simplelistview.DataPassing.ActivityToFrag.FragmentDataPassingActivity;
import org.o7planning.simplelistview.DataPassing.ActivityToActivity.ProfileActivity;
import org.o7planning.simplelistview.utils.Popup;
import org.o7planning.simplelistview.utils.SnackbarUtil;

// Firebase project name -> JC-simplelistview

// Context is required by android to know about the place from where u invoked something
// Press alt + insert to get options of functions , like getters and setters functions in class
// Press Ctrl then click on any inbuilt function to open its code to see its parameters and return value and etc etc
// Alt+insert to insert functions and amongst them click with ctrl pressed to multi select functions to insert
//
public class MainActivity extends AppCompatActivity {

    Button mToastButton;
    Button mNextActivityButton;
    Button mPopupButton;
    Button mSnackButton;
    Button mEmptyActivityButton;
    Button mProfileButton;
    Button mInternetButton;
    Button mApiButton;
    Button mBasicAdapterButton;
    Button mDatabaseButton;
    ListView mListView;
    View mCoordinatorLayout;

    FirebaseAnalytics mFirebaseAnalytics;

    public class UserDetails {
        private String name;
        private String email;
        private int age;
        private long phone;
        private String city;
        private boolean hasCreditCard;

        public UserDetails(String name, String email, int age, long phone, String city, boolean b) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.phone = phone;
            this.city = city;
            this.hasCreditCard = b;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseCrashlytics firebaseCrashlytics= FirebaseCrashlytics.getInstance();
        firebaseCrashlytics.setCrashlyticsCollectionEnabled(true);

        firebaseCrashlytics.log("Hello from Main");
///----USING GSON-----///
        UserDetails user = new UserDetails("John", "john.doe@gmail.com", 29, 5168161922L, "NewYork", false);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println(json);
////////////////////////////////////////
        mToastButton = findViewById(R.id.toast_button);
        mNextActivityButton = findViewById(R.id.NextActivity_Button);
        mPopupButton = findViewById(R.id.popup_button);
        mSnackButton = findViewById(R.id.snack_button);
        mProfileButton = findViewById(R.id.profile_button);
        mEmptyActivityButton = findViewById(R.id.empty_button);
        mInternetButton = findViewById(R.id.internet_button);
        mApiButton = findViewById(R.id.api_button);
        mBasicAdapterButton = findViewById(R.id.basic_adapter_button);
        mDatabaseButton=findViewById(R.id.database_button);

        mFirebaseAnalytics=FirebaseAnalytics.getInstance(MainActivity.this);
        //Set whether analytics collection is enabled for this app on this device.
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
//        //Set the minimum engagement time required before starting a session.
//        mFirebaseAnalytics.setMinimumSessionDuration(20000);
//
//        //Set the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
//        mFirebaseAnalytics.setSessionTimeoutDuration(3000);

        mFirebaseAnalytics.setUserProperty("GENDER","MALE");

        Bundle bundle1 = new Bundle();
        bundle1.putString(FirebaseAnalytics.Param.ITEM_ID, "2");
        bundle1.putString(FirebaseAnalytics.Param.ITEM_NAME, "MAIN");
        bundle1.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "API_BUTTON");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, bundle1);


        mBasicAdapterButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MAIN");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ADAPTER_BUTTON");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            BasicAdapterActivity();
        });

        mApiButton.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "2");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MAIN");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "API_BUTTON");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, bundle);
            ApiActivity();

        });
        mNextActivityButton.setOnClickListener(v -> NextActivity());
        mPopupButton.setOnClickListener(v -> PopupListener());
        mInternetButton.setOnClickListener(v -> checkNet());

        mListView = (ListView) findViewById(R.id.listView);   // for textview we would have written TextView so this is okay
        if (mListView != null) {

            // create 3 instance of the class UserAccount
            UserAccount tom = new UserAccount("Tom", "admin");
            UserAccount jerry = new UserAccount("Jerry", "user");
            UserAccount donald = new UserAccount("Donald", "guest", false);

            // put those instances into an array
            UserAccount[] users = new UserAccount[]{tom, jerry, donald};

            // android.R.layout.simple_list_item_1 is a constant predefined layout of Android.
            // used to create a ListView with simple ListItem (Only one TextView).
            // with simple_list_item_checked the following options are available // CHOICE_MODE_NONE: (Default) // (mListView.setItemChecked(..) doest not work with CHOICE_MODE_NONE). // CHOICE_MODE_SINGLE: // CHOICE_MODE_MULTIPLE: // CHOICE_MODE_MULTIPLE_MODAL:
            mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);        // in case u are gonna use simple_list_item_checked (this will enable the green tick)

            // instead of simple_list_item_1 below
            ArrayAdapter<UserAccount> arrayAdapter
                    = new ArrayAdapter<UserAccount>(this, android.R.layout.simple_list_item_checked, users);   //create an adapter

            // if mListView will be null then below code will obviously give error
            mListView.setAdapter(arrayAdapter);            // set the adapter
        } else {
            System.out.println("\n \n listview is null \n \n");
        }

        mDatabaseButton.setOnClickListener(v->{
            Intent intent =new Intent(this, MainActivity_DB.class);
            startActivity(intent);
        });


        setSnackAndToast();
        setProfileActivity();
        setEmptyActivity();
        setFirebaseAnalytics();
     ////////////////////////////////////////////////////////
    }

    private void setFirebaseAnalytics() {

    }

    private void BasicAdapterActivity() {

        Intent intent = new Intent(MainActivity.this, BasicArrayAdapter.class);
        startActivity(intent);
    }

    private void ApiActivity() {
        Intent intent = new Intent(MainActivity.this, MainActivity_API.class);
        startActivity(intent);
    }

    public void NextActivity()   // created an onclicklistener to be used with any buttonclick
    {
        Intent intent = new Intent(this, CountryActivity.class);
        startActivity(intent);
    }

    public void PopupListener() {
        Intent intent = new Intent(this, Popup.class);
        startActivity(intent);
    }

    public void setSnackAndToast() {
        mToastButton.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, 588);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, bundle);

            Toast.makeText(getApplicationContext(), "Toast Text", Toast.LENGTH_SHORT).show();
        });

        mCoordinatorLayout = findViewById(R.id.context_view);
        // NOTE : Snackbar takes in a View or CoordinatorLayout and not a context
        //CoordinatorLayout mContextView = (CoordinatorLayout) findViewById(R.id.context_view);  // too will work

        mSnackButton.setOnClickListener(v -> {

            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, "Message is deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", view -> {
                        Snackbar snackbar1 = Snackbar.make(mCoordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    });
            snackbar.show();
        });
    }

    public void setProfileActivity() {
        mProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    public void setEmptyActivity() {
        mEmptyActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, FragmentDataPassingActivity.class);
            startActivity(intent);
        });
    }

    public void checkNet() {
        if (isInternetAvailable()) {
            SnackbarUtil.makeLongSnack(mCoordinatorLayout, "Net Available");
        } else {
            SnackbarUtil.makeLongSnack(mCoordinatorLayout, "Net Not Available");
        }
    }

    public boolean isInternetAvailable() {
        return false;
    }

}