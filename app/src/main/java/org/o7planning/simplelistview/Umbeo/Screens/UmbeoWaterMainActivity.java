package org.o7planning.simplelistview.Umbeo.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.o7planning.simplelistview.R;

public class UmbeoWaterMainActivity extends AppCompatActivity {

    LinearLayout m50ml_LinearLayout;
    LinearLayout m100ml_LinearLayout;
    LinearLayout m150ml_LinearLayout;
    LinearLayout m200ml_LinearLayout;
    LinearLayout m250ml_LinearLayout;
    LinearLayout mCustom_ml_LinearLayout;

    ConstraintLayout mMainConstraintLayout;

    FloatingActionButton mAddWaterIntakeButton;
    FloatingActionButton mEnableDisableNotificationButton;
    FloatingActionButton mWaterIntakeStatsButton;

    TextView mCurrentIntakeTextView;
    TextView mTargetIntakeTextView;

    ImageView mSettingsImageView;

    int index = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_umbeo_water_main);

        m50ml_LinearLayout = findViewById(R.id.umbeo_water_50ml_layout);
        m100ml_LinearLayout = findViewById(R.id.umbeo_water_100ml_layout);
        m150ml_LinearLayout = findViewById(R.id.umbeo_water_150ml_layout);
        m200ml_LinearLayout = findViewById(R.id.umbeo_water_200ml_layout);
        m250ml_LinearLayout = findViewById(R.id.umbeo_water_250ml_layout);
        mCustom_ml_LinearLayout = findViewById(R.id.umbeo_custom_water_intake_layout);

        mAddWaterIntakeButton = findViewById(R.id.umbeo_water_intake_button);
        mEnableDisableNotificationButton = findViewById(R.id.umbeo_water_notification_button);
        mWaterIntakeStatsButton = findViewById(R.id.umbeo_water_stats_button);

        mMainConstraintLayout = findViewById(R.id.umbeo_activity_water_main_coordinator_layout);

        mCurrentIntakeTextView = findViewById(R.id.umbeo_current_intake_water_main_textview);
        mTargetIntakeTextView = findViewById(R.id.umbeo_target_intake_water_main_textview);

        mSettingsImageView = findViewById(R.id.umbeo_water_main_settings_imageview);

        mSettingsImageView.setOnClickListener(v->{
            BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogFragment();
//            bottomSheetDialogFragment.setTargetFragment();

        });

        mAddWaterIntakeButton.setOnClickListener(v->{

        });

        mEnableDisableNotificationButton.setOnClickListener(v->{

        });

        mWaterIntakeStatsButton.setOnClickListener(v->{
            Intent intent = new Intent(this,UmbeoWaterStatsActivity.class);
            startActivity(intent);
        });

        setupListeners();

    }

    private void setupListeners()
    {
        mMainConstraintLayout.setOnClickListener(v->{
            index=0;
            m50ml_LinearLayout.setBackgroundResource(0);
            m100ml_LinearLayout.setBackgroundResource(0);
            m150ml_LinearLayout.setBackgroundResource(0);
            m200ml_LinearLayout.setBackgroundResource(0);
            m250ml_LinearLayout.setBackgroundResource(0);
            mCustom_ml_LinearLayout.setBackgroundResource(0);
        });

        m50ml_LinearLayout.setOnClickListener(v -> {
            index = 1;
            setup(1);
        });

        m100ml_LinearLayout.setOnClickListener(v -> {
            index = 2;
            setup(2);
        });

        m150ml_LinearLayout.setOnClickListener(v -> {
            index = 3;
            setup(3);
        });

        m200ml_LinearLayout.setOnClickListener(v -> {
            index = 4;
            setup(4);
        });

        m250ml_LinearLayout.setOnClickListener(v -> {
            index = 5;
            setup(5);
        });

        mCustom_ml_LinearLayout.setOnClickListener(v -> {
            index = 6;
            setup(6);
        });

    }

    private void setup(int i) {
        m50ml_LinearLayout.setBackgroundResource(0);
        m100ml_LinearLayout.setBackgroundResource(0);
        m150ml_LinearLayout.setBackgroundResource(0);
        m200ml_LinearLayout.setBackgroundResource(0);
        m250ml_LinearLayout.setBackgroundResource(0);
        mCustom_ml_LinearLayout.setBackgroundResource(0);

        if(i==1)
        {
            m50ml_LinearLayout.setBackgroundResource(R.drawable.option_select_bg);
        }
        if(i==2)
        {
            m100ml_LinearLayout.setBackgroundResource(R.drawable.option_select_bg);
        }
        if(i==3)
        {
            m150ml_LinearLayout.setBackgroundResource(R.drawable.option_select_bg);
        }
        if(i==4)
        {
            m200ml_LinearLayout.setBackgroundResource(R.drawable.option_select_bg);
        }
        if(i==5)
        {
            m250ml_LinearLayout.setBackgroundResource(R.drawable.option_select_bg);
        }
        if(i==6)
        {
            mCustom_ml_LinearLayout.setBackgroundResource(R.drawable.option_select_bg);
        }
    }


}
