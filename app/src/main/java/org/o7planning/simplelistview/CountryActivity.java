package org.o7planning.simplelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    Button mPrevButton;
    ListView mCountryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counrty);

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(v -> {
//            Intent intent =new Intent(this,MainActivity.class);
//            startActivity(intent);
            onNavigateUp();
        });

        List<Country> country_details = getListData();                            // create a list
        mCountryList = findViewById(R.id.listView2);
        mCountryList.setAdapter(new CountryListAdapter(this, country_details));

        // When the user clicks on any ListItem
        mCountryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                System.out.println(id);
                Object obj = mCountryList.getItemAtPosition(position);
                Country country = (Country) obj;
                Toast.makeText(CountryActivity.this, "Selected :" + " " + country, Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country vietnam = new Country("Vietnam", "vn", 98000000);
        Country usa = new Country("United States", "us", 320000000);
        Country russia = new Country("Russia", "ru", 142000000);

        list.add(vietnam);
        list.add(usa);
        list.add(russia);

        return list;
    }

}