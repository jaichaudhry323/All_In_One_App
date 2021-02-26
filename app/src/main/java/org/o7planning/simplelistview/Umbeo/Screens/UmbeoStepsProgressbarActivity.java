package org.o7planning.simplelistview.Umbeo.Screens;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.o7planning.simplelistview.R;

public class UmbeoStepsProgressbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umbeo_steps_circular_progressview);

        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);

        circularProgressBar.setProgressBarColor(R.color.red_300);
//        circularProgressBar.setProgressBarColorStart(R.color.red_400);
//        circularProgressBar.setProgressBarColorEnd(R.color.red_400);
//        circularProgressBar.setBackgroundProgressBarColor(R.color.red_400);

        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);
        circularProgressBar.setProgressWithAnimation(4000, (long) 50);
        circularProgressBar.setProgressMax(5000f);

    }
}
