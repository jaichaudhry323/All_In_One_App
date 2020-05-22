package org.o7planning.simplelistview.ArrayAdapterExample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.utils.ToastUtil;

import java.util.ArrayList;

public class BasicArrayAdapter extends AppCompatActivity {

    ListView mHarryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);

        mHarryList = findViewById(R.id.api_listview);

        ArrayList<String> cars = new ArrayList<>();
        cars.add("Lamborghini Aventador");
        cars.add("Koenigsegg Agera R");
        cars.add("Ferrari");
        cars.add("Bugatti");
        cars.add("Pagani");
        cars.add("Hummer");
        cars.add("Mercedes SLS AMG");
        cars.add("Audi R8");
        cars.add("Lamborghini Veneno");
        cars.add("SSC Tuatara");
        cars.add("Lamborghini Huracan");
        cars.add("Koenigsegg Regera");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, cars);
        mHarryList.setAdapter(arrayAdapter);

        mHarryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = "Item" + position + " " + ((TextView) view).getText().toString();
                ToastUtil.makeLongToast(BasicArrayAdapter.this, text);
            }
        });

    }

}
