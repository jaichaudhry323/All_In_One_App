package org.o7planning.simplelistview.Umbeo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.Umbeo.AppUsageStats.AppUsageActivity;
import org.o7planning.simplelistview.Umbeo.Charts.ListViewMultiChartActivity;
import org.o7planning.simplelistview.Umbeo.Fragments.UmbeoUpdateInfoFragment;
import org.o7planning.simplelistview.Umbeo.Screens.UmbeoCompleteProfileActivity;
import org.o7planning.simplelistview.Umbeo.Screens.UmbeoLoginActivity;
import org.o7planning.simplelistview.Umbeo.Screens.UmbeoProfileActivity;
import org.o7planning.simplelistview.Umbeo.Screens.UmbeoStepsProgressbarActivity;
import org.o7planning.simplelistview.Umbeo.Screens.UmbeoSwipeActivity;
import org.o7planning.simplelistview.Umbeo.Screens.UmbeoWaterMainActivity;
import org.o7planning.simplelistview.Umbeo.Screens.UmbeoWaterStatsActivity;

import java.util.Arrays;
import java.util.List;

public class UmbeoActivity extends AppCompatActivity {

    Button mLoginScreenButton;
    Button mSwipeScreenButton;
    Button mSlidingImageButton;
    Button mScrollChartsButton;
    Button mSignInActivityButton;
    Button mProfileActivityButton;
    Button mAppUsageStatsButton;
    Button mWaterMainActivity;
    Button mWaterStatsActivity;
    Button mStepsProgressBarActivityButton;
    Button mCompleteProfileFromActivityButton;
    Button mBottomFragmentActivityButton;

    private int RC_SIGN_IN = 1822;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_umbeo);

        mLoginScreenButton = findViewById(R.id.login_screen_button);
        mSwipeScreenButton = findViewById(R.id.swipe_screen_button);
        mSlidingImageButton = findViewById(R.id.sliding_image_activity_button);
        mScrollChartsButton = findViewById(R.id.scroll_charts_button);
        mSignInActivityButton = findViewById(R.id.signin_activity_button);
        mProfileActivityButton = findViewById(R.id.profile_activity_button);
        mAppUsageStatsButton = findViewById(R.id.app_usage_activity_button);

        mWaterMainActivity = findViewById(R.id.water_main_umbeo_activity_button);
        mWaterStatsActivity = findViewById(R.id.water_stats_umbeo_activity_button);

        mStepsProgressBarActivityButton = findViewById(R.id.steps_progressbar_umbeo_activity_button);
        mCompleteProfileFromActivityButton = findViewById(R.id.complete_profile_umbeo_activity_button);

        mBottomFragmentActivityButton = findViewById(R.id.umbeo_bottom_fragment_button);

        mBottomFragmentActivityButton.setOnClickListener(v->{
            BottomSheetDialogFragment bottomSheetDialogFragment = new UmbeoUpdateInfoFragment();
            bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
        });

        mCompleteProfileFromActivityButton.setOnClickListener(v->{
            Intent intent = new Intent(this, UmbeoCompleteProfileActivity.class);
            startActivity(intent);
        });

        mStepsProgressBarActivityButton.setOnClickListener(v->{
            Intent intent = new Intent(this, UmbeoStepsProgressbarActivity.class);
            startActivity(intent);
        });

        mWaterStatsActivity.setOnClickListener(v -> {
            Intent intent = new Intent(this, UmbeoWaterStatsActivity.class);
            startActivity(intent);
        });

        mWaterMainActivity.setOnClickListener(v -> {
            Intent intent = new Intent(this, UmbeoWaterMainActivity.class);
            startActivity(intent);
        });

        mLoginScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UmbeoLoginActivity.class);
            startActivity(intent);
        });

        mSwipeScreenButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UmbeoSwipeActivity.class);
            startActivity(intent);
        });

        mSlidingImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SlidingImageActivity.class);
            startActivity(intent);
        });

        mScrollChartsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListViewMultiChartActivity.class);
            startActivity(intent);
        });

        mProfileActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, UmbeoProfileActivity.class);
            startActivity(intent);
        });

        mAppUsageStatsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AppUsageActivity.class);
            startActivity(intent);
        });


        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        String url1 = "https://joebirch.co/terms.html";
        String url2 = "https://joebirch.co/privacy.html";

        mSignInActivityButton.setOnClickListener(v -> {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.ic_launcher_foreground)
                            .setTheme(R.style.LoginTheme)
                            .setTosAndPrivacyPolicyUrls(url1, url2)
                            .build(),
                    RC_SIGN_IN
            );
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == Activity.RESULT_OK) {
                Log.d(this.getClass().getName(), "This user signed in with " + response.getProviderType());
//                startUpTasks();
//                updateInfo();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this, "Signin cancelled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "Check network connection and try again", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(this, "Unexpected Error, we are trying to resolve the issue. Please check back soon", Toast.LENGTH_LONG).show();
//                Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }

    public void getSleep()
    {

        int age=0;
        int sleepIntake = 0;
//        age=Integer.parseInt(getAGE);
        if(age<=1)
            sleepIntake=14;
        else if(age>1&&age<=2)
            sleepIntake=13;
        else if(age>2&&age<=3)
            sleepIntake=12;
        else if(age>3&&age<=5)
            sleepIntake=11;
        else if(age>5&&age<=13)
            sleepIntake=10;
        else if(age>13&&age<17)
            sleepIntake=9;
        else if(age>17&&age<=25)
            sleepIntake=8;
        else if(age>25&&age<=64)
            sleepIntake=7;
        else if(age>64)
            sleepIntake=7;
    }

    public void getWaterIntake()
    {
        int acwV=0;
        double waterIntake_W = 0;
        if (acwV <= 30)
            waterIntake_W = 1.0;
        else if (acwV > 30 && acwV <= 45)
            waterIntake_W = 1.5;
        else if (acwV > 45 && acwV <= 49.9)
            waterIntake_W = 1.98;
        else if (acwV > 49.9 && acwV <= 54.9)
            waterIntake_W = 2.1;
        else if (acwV > 54.9 && acwV <= 58.9)
            waterIntake_W = 2.5;
        else if (acwV > 58.9 && acwV <= 63.5)
            waterIntake_W = 2.7;
        else if (acwV > 63.5 && acwV <= 68.9)
            waterIntake_W = 2.9;
        else if (acwV > 68.9 && acwV <= 72.57)
            waterIntake_W = 3.1;
        else if (acwV > 72.57 && acwV <= 77)
            waterIntake_W = 3.3;
        else if (acwV > 77 && acwV <= 81.6)
            waterIntake_W = 3.5;
        else if (acwV > 81.6 && acwV <= 86)
            waterIntake_W = 3.75;
        else if (acwV > 86 && acwV <= 90.7)
            waterIntake_W = 3.9;
        else if (acwV > 90.7 && acwV <= 95.25)
            waterIntake_W = 4.1;
        else if (acwV > 95.25 && acwV <= 99.79)
            waterIntake_W = 4.3;
        else if (acwV > 99.79 && acwV <= 104.3)
            waterIntake_W = 4.55;
        else if (acwV > 104.3 && acwV <= 108.86)
            waterIntake_W = 4.76;
        else if (acwV > 108.86 && acwV <= 115)
            waterIntake_W = 4.96;
        else if (acwV > 115 && acwV <= 150)
            waterIntake_W = 5;
        else if (acwV > 150)
            waterIntake_W = 6;
        else
            Toast.makeText(getApplicationContext(), "Enter Proper Details", Toast.LENGTH_SHORT).show();
    }

}
