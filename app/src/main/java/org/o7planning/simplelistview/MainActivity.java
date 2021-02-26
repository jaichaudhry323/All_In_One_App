package org.o7planning.simplelistview;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;

import org.o7planning.simplelistview.API.MainActivity_API;
import org.o7planning.simplelistview.D_ARES.D_ARES_MainActivity;
import org.o7planning.simplelistview.ArrayAdapterExample.BasicArrayAdapter;
import org.o7planning.simplelistview.DB.MainActivity_DB;
import org.o7planning.simplelistview.DataPassing.ActivityToActivity.ProfileActivity;
import org.o7planning.simplelistview.DataPassing.ActivityToFrag.FragmentDataPassingActivity;
import org.o7planning.simplelistview.Service4.Restarter;
import org.o7planning.simplelistview.Service4.YourService;
import org.o7planning.simplelistview.Umbeo.UmbeoActivity;
import org.o7planning.simplelistview.retrofit.Activity_Retrofit;
import org.o7planning.simplelistview.utils.NetworkUtil;
import org.o7planning.simplelistview.utils.NotificationUtil;
import org.o7planning.simplelistview.utils.Popup;
import org.o7planning.simplelistview.utils.PreferencesManager;
import org.o7planning.simplelistview.utils.SnackbarUtil;
import org.o7planning.simplelistview.utils.ToastUtil;

import java.util.Timer;

// Firebase project name -> JC-simplelistview

// Context is required by android to know about the place from where u invoked something
// Press alt + insert to get options of functions , like getters and setters functions in class
// Press Ctrl then click on any inbuilt function to open its code to see its parameters and return value and etc etc
// Alt+insert to insert functions and amongst them click with ctrl pressed to multi select functions to insert

public class MainActivity extends AppCompatActivity {

    Button mToastButton;
    Button mNextActivityButton;
    Button mPopupButton;
    Button mSnackButton;
    Button mEmptyActivityButton;
    Button mProfileButton;
    Button mInternetCheckButton;
    Button mApiButton;
    Button mBasicAdapterButton;
    Button mDatabaseButton;
    Button mAdvancedUiButton;
    Button mNotificationButton;
    Button mUmbeoActivityButton;
    Button mReftrofitActivityButton;

    ToggleButton mScreenTrackingToggleButton;

    ListView mListView;
    View mCoordinatorLayout;

    FirebaseAnalytics mFirebaseAnalytics;

    Intent mServiceIntent;
    private YourService mYourService;

    // Notification ID.
    private static final int NOTIFICATION_ID = 0;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotificationManager;

    private static final Intent[] POWERMANAGER_INTENTS = {
            new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
            new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
            new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
            new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity"))};


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

        PreferencesManager.init(getApplicationContext());

        // ----------------------------------------------------------------------------

//        Intent intentservice = new Intent(this, AppService.class);
//        startService(intentservice);

        // ----------------------------------------------------------------------------

//        if (!isMyServiceRunning(mYourService.getClass())) {

        if(PreferencesManager.getInstance().getBoolean("track_screen"))
        {
            this.startService(new Intent(this,YourService.class));
        }

//        }
        // ----------------------------------------------------------------------------

        // ----------------------------------------------------------------------------

//        final SharedPreferences.Editor pref = getSharedPreferences("allow_notify", MODE_PRIVATE).edit();
//        pref.apply();
//        final SharedPreferences sp = getSharedPreferences("allow_notify", MODE_PRIVATE);
//        if (!sp.getBoolean("protected", false)) {
//            for (final Intent intent : POWERMANAGER_INTENTS)
//                if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setTitle("Alert Title").setMessage("Alert Body")
//                            .setPositiveButton("Ok", (dialogInterface, i) -> {
//                                startActivity(intent);
//                                sp.edit().putBoolean("protected", true).apply();
//
//                            })
//                            .setCancelable(false)
//                            .setNegativeButton("Cancel", (dialog, which) -> {
//                            })
//                            .create().show();
//                    break;
//                }
//        }

        // ----------------------------------------------------------------------

        mReftrofitActivityButton = findViewById(R.id.activity_retrofit_button);
        mReftrofitActivityButton.setOnClickListener(v->{
            Intent intent = new Intent(this,Activity_Retrofit.class);
            startActivity(intent);
        });

        mScreenTrackingToggleButton = findViewById(R.id.screen_tracking_toggle_button);
        mScreenTrackingToggleButton.setOnCheckedChangeListener(
                ((compoundButton, b) -> {

                    if(b)
                    {
                        PreferencesManager.getInstance().putBoolean("track_screen",true);
                        Intent intent = new Intent(this, YourService.class);
                        startService(intent);
                    }
                    else
                    {
                        PreferencesManager.getInstance().putBoolean("track_screen",false);
                        Intent intent = new Intent(this, YourService.class);
                        intent.putExtra("close",true);
                        startService(intent);
                    }
                })
        );

        FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
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
        mInternetCheckButton = findViewById(R.id.internet_button);
        mApiButton = findViewById(R.id.api_button);
        mBasicAdapterButton = findViewById(R.id.basic_adapter_button);
        mDatabaseButton = findViewById(R.id.database_button);
        mAdvancedUiButton = findViewById(R.id.advanced_ui_button);
        mNotificationButton = findViewById(R.id.notification_button);
        mUmbeoActivityButton = findViewById(R.id.umbeo_activity_button);

        mUmbeoActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UmbeoActivity.class);
            startActivity(intent);
        });

        createChannel();

        mNotificationButton.setOnClickListener(v -> {

            ToastUtil.makeLongToast(getApplicationContext(), "Reminder set");
            NotificationUtil.setNotification(this, "Notification yay", "Using NotificationUtil", 1000 * 5);

//            Intent intent = new Intent(this, ReminderBroadcast.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//            long timeAtButtonClick = System.currentTimeMillis();
//            long tenSecondsInMillis = 1000 * 10;
//
//            alarmManager.set(AlarmManager.RTC_WAKEUP,
//                    timeAtButtonClick + tenSecondsInMillis,
//                    pendingIntent);
//22:DF:5D:5F:61:4D:A6:B6:36:0B:27:54:A5:2D:DE:31:4B:81:55:F6
//22:DF:5D:5F:61:4D:A6:B6:36:0B:27:54:A5:2D:DE:31:4B:81:55:F6
        });

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(MainActivity.this);
        //Set whether analytics collection is enabled for this app on this device.

        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);

//        //Set the minimum engagement time required before starting a session.
//        mFirebaseAnalytics.setMinimumSessionDuration(20000);
//        //Set the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
//        mFirebaseAnalytics.setSessionTimeoutDuration(3000);

        mFirebaseAnalytics.setUserProperty("GENDER", "MALE");

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

        mAdvancedUiButton.setOnClickListener(v -> {
            advancedUiActivity();
        });

        mNextActivityButton.setOnClickListener(v -> NextActivity());
        mPopupButton.setOnClickListener(v -> PopupListener());
        mInternetCheckButton.setOnClickListener(v -> checkNet());

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
            mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);              // in case u are gonna use simple_list_item_checked (this will enable the green tick)

            // instead of simple_list_item_1 below
            ArrayAdapter<UserAccount> arrayAdapter
                    = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, users);   //create an adapter //simple_list_item_checked

            mListView.getSelectedItem();

            // if mListView will be null then below code will obviously give error
            mListView.setAdapter(arrayAdapter);            // set the adapter
        } else {
            System.out.println("\n \n listview is null \n \n");
        }

        mDatabaseButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity_DB.class);
//            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        setSnackAndToast();
        setProfileActivity();
        setEmptyActivity();
//        setFirebaseAnalytics();
        setTimer();
        setRepetitiveTaskTimer();

        ///////////////////////////////////////////////////////
        NetworkUtil.setConnectivityTracking(getBaseContext());
        NetworkUtil.getMutableLiveDataNetworkStatus().observe(this, (internet) -> {
            if (internet) {
//                ToastUtil.makeShortToast(this, "From LiveData Internet Avilable");
            } else {
//                ToastUtil.makeShortToast(this, "From LiveData Internet Not Avilable");
            }
        });

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

        // Google one using AlarmReceiver
//        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
//
//        // Set up the Notification Broadcast Intent.
//        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
//
//        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID,
//                notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
//        alarmToggle.setChecked(alarmUp);
//
//        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
//                (this, NOTIFICATION_ID, notifyIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//
//        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        // Set the click listener for the toggle button.
//        alarmToggle.setOnCheckedChangeListener
//                ((buttonView, isChecked) -> {
//                    String toastMessage;
//
//                    if (isChecked) {
//                        long repeatInterval = 500;
////                            long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
//
//                        long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;
//
//                        ToastUtil.makeShortToast(getApplicationContext(), Integer.toString((int) triggerTime));
//
//                        // If the Toggle is turned on, set the repeating alarm with
//                        // a 15 minute interval.
//                        if (alarmManager != null) {
//                            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval,
//                                            notifyPendingIntent);
//                        }
//
//                        // Set the toast message for the "on" case.
//                        toastMessage = "Alarm On";
//                    }
//                    else {
//                        mNotificationManager.cancelAll();
//
//                        if (alarmManager != null) {
//                            alarmManager.cancel(notifyPendingIntent);
//                        }
//                        toastMessage = "Alarm off";
//                    }
//
//                    // Show a toast to say the alarm is turned on or off.
////                        Toast.makeText(MainActivity.this, toastMessage,
////                                Toast.LENGTH_SHORT).show();
//                });
//
////         Create the notification channel.
//        createNotificationChannel();

    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    //    /**  Works      # Google dev
//     * Creates a Notification channel, for OREO and higher.
//     */
//    public void createNotificationChannel() {
//
//        // Create a notification manager object.
//        mNotificationManager =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        // Notification channels are only available in OREO and higher.
//        // So, add a check on SDK version.
//        if (android.os.Build.VERSION.SDK_INT >=
//                android.os.Build.VERSION_CODES.O) {
//
//            // Create the NotificationChannel with all the parameters.
//            NotificationChannel notificationChannel = new NotificationChannel
//                    (PRIMARY_CHANNEL_ID,
//                            "Stand up notification",
//                            NotificationManager.IMPORTANCE_HIGH);
//
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.enableVibration(true);
//            notificationChannel.setDescription("Notifies every 15 minutes to " +
//                    "stand up and walk");
//            mNotificationManager.createNotificationChannel(notificationChannel);
//        }
//    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    // video code
    public void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "primary_notification_channel_133";
            String description = "Channel for Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("primary_notification_channel_133", name, importance);
            channel.setDescription(description);
            channel.enableVibration(true);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    private void advancedUiActivity() {
        Intent intent = new Intent(MainActivity.this, D_ARES_MainActivity.class);
        startActivity(intent);
    }

    private void setRepetitiveTaskTimer() {
        Timer t = new Timer();
    }

    private void setTimer() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                ToastUtil.makeShortToast(getBaseContext(), "One time Timer");
            }
        }, 3000);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void checkNet() {
        if (NetworkUtil.getConnectivityStatus(getBaseContext())) {
            SnackbarUtil.makeLongSnack(mCoordinatorLayout, "Net Available");
        } else {
            SnackbarUtil.makeLongSnack(mCoordinatorLayout, "Net Not Available");
        }
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

    void alert() {
        AlertDialog exitDialog = new AlertDialog.Builder(MainActivity.this).setCancelable(false).create();
        exitDialog.setMessage("LOGOUT");
        exitDialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", (dialog, which) -> {
//
//            Intent logoutIntent = new Intent(MainActivity.this, EntryActivity.class);
//            logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(logoutIntent);
            finish();
        });
        exitDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", (dialog, which) -> {
            exitDialog.dismiss();
        });
        exitDialog.show();
    }

    ///////////////////
    // service5
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("Service status", "Running");
                return true;
            }
        }
        Log.i("Service status", "Not running");
        return false;
    }

    @Override
    protected void onDestroy() {
        //stopService(mServiceIntent);
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, Restarter.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }

}

