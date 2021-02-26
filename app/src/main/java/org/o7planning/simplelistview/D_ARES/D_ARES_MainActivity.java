package org.o7planning.simplelistview.D_ARES;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.R;

public class D_ARES_MainActivity extends AppCompatActivity {

    Button mActivityGridTwoLine;
    Button mNewsImageListActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.z_activity_main_d_ares);

        mActivityGridTwoLine = findViewById(R.id.d_ares_grid_two_line_button);
        mNewsImageListActivity = findViewById(R.id.d_ares_news_image_list_activity_button);

        mNewsImageListActivity.setOnClickListener(v->{
            Intent intent = new Intent(this,ListNewsImageActivity.class);
            startActivity(intent);
        });

        mActivityGridTwoLine.setOnClickListener(v->{
            Intent intent = new Intent(this,GridTwoLineActivity.class);
            startActivity(intent);
        });

    }
}
