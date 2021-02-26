package org.o7planning.simplelistview.Umbeo.Screens;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.R;

import me.itangqi.waveloadingview.WaveLoadingView;

public class UmbeoWaterStatsActivity extends AppCompatActivity {

    WaveLoadingView mWaveloadingView;
    TextView mCurrentIntakeTextview;
    TextView mTargetIntakeTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_umbeo_water_stats);

        mWaveloadingView = findViewById(R.id.umbeo_water_stats_progress_wave_loading_view);
        mCurrentIntakeTextview = findViewById(R.id.umbeo_water_stats_current_intake_textview);
        mTargetIntakeTextView = findViewById(R.id.umbeo_water_stats_target_intake_textview);

        mCurrentIntakeTextview.setText("0 ml");
        mTargetIntakeTextView.setText("2500 ml");
        mWaveloadingView.setProgressValue(20);
        mWaveloadingView.setCenterTitle("20%");

    }
}
