package org.o7planning.simplelistview.API.Heros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.utils.ToastUtil;

import java.util.ArrayList;

public class API_Heros extends AppCompatActivity {

    private static final String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
    ListView mHeroListView;
    ArrayList<Hero> mHeroArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);

        mHeroListView = findViewById(R.id.api_listview);
        mHeroArrayList = new ArrayList<>();                      // NOTE : V Imp Step ,

        loadheroList();
    }

    public void loadheroList() {
        ProgressBar mCircularProgressBar = findViewById(R.id.api_circularprogressbar);
        ProgressBar mProgressBar = findViewById(R.id.api_progressbar);

        mCircularProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);

        // NOTE :
        // Both Response and Error Listener are Compulsory to be made here
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mCircularProgressBar.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("heroes");

//                    System.out.println("\n n \n n \n Response=" + response);
//                    System.out.println("\n n \n n \n jsonObject=" + jsonObject);
//                    System.out.println("\n n \n n \n jsonArray=" + jsonArray);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                        System.out.println("" + jsonObject1);
                        Hero hero = new Hero(jsonObject1.getString("name"), jsonObject1.getString("imageurl"));
                        mHeroArrayList.add(hero);
                    }

                    HeroListAdapter adapter = new HeroListAdapter(getApplicationContext(), mHeroArrayList);
                    mHeroListView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.makeLongToast(getApplicationContext(), "there is an error");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
