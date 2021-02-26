package org.o7planning.simplelistview.D_ARES;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;
import com.google.android.material.snackbar.Snackbar;

import org.o7planning.simplelistview.D_ARES.Adapters.AdapterGridTwoLine;
import org.o7planning.simplelistview.D_ARES.data.DataGenerator;
import org.o7planning.simplelistview.D_ARES.model.Image;
import org.o7planning.simplelistview.D_ARES.utils.Tools;
import org.o7planning.simplelistview.R;

import java.util.List;

public class GridTwoLineActivity extends AppCompatActivity {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterGridTwoLine mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.z_activity_grid_two_line);

        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Two Line");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_1000);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 3)));
        recyclerView.setHasFixedSize(true);

        List<Image> items = DataGenerator.getImageDate(this);
        items.addAll(DataGenerator.getImageDate(this));
        items.addAll(DataGenerator.getImageDate(this));
        items.addAll(DataGenerator.getImageDate(this));

        //set data and list adapter
        mAdapter = new AdapterGridTwoLine(this, items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridTwoLine.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Image obj, int position) {
                Snackbar.make(parent_view, obj.name + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.z_menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
