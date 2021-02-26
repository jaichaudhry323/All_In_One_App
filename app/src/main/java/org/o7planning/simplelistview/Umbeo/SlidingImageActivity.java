package org.o7planning.simplelistview.Umbeo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.simplelistview.R;

import java.util.ArrayList;
import java.util.List;

public class SlidingImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_image);

        setupSlidingImageComponent();

    }

    private void setupSlidingImageComponent() {

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        List<String> mImages = new ArrayList<>();

        mImages.add("1");
        mImages.add("1");
        mImages.add("1");
        mImages.add("1");

        mRecyclerView.setAdapter(new AdapterSlidingImage(this,mImages));

    }

}
