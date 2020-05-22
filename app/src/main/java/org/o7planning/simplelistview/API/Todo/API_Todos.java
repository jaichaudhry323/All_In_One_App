package org.o7planning.simplelistview.API.Todo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.utils.ToastUtil;

import java.util.ArrayList;

public class API_Todos extends AppCompatActivity {

    String mTodoUrl = "https://jsonplaceholder.typicode.com/todos";
    ProgressBar mProgressBar;
    ArrayList<API_Todo> mTodoList = new ArrayList<API_Todo>();
    ListView mTodoListView;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.empty_activity);

        mProgressBar = findViewById(R.id.api_progressbar);
        mProgressBar.setVisibility(View.VISIBLE);
        mTodoListView = findViewById(R.id.api_listview);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

//        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
//                mTodoUrl, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                mProgressBar.setVisibility(View.GONE);
//                try {
//                    ToastUtil.makeLongToast(getApplicationContext(), "Received");
//                    System.out.println("Response is = "+response.getString("title"));
//                } catch (JSONException e) {
//                    ToastUtil.makeLongToast(getApplicationContext(), "there is an error wrt json");
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                ToastUtil.makeLongToast(getApplicationContext(), "there is an error in response");
//                error.printStackTrace();
//            }
//        });
//
//        requestQueue.add(jsonObjectRequest);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, mTodoUrl, response -> {

            mProgressBar.setVisibility(View.GONE);

            ToastUtil.makeLongToast(API_Todos.this, "Response Received");
//            System.out.println("Response=" + response);
            System.out.println("Response=");

//            JSONObject jsonObject = null;
            try {
                JSONArray jsonArray = new JSONArray(response);

                System.out.println("Response=" + jsonArray.getJSONObject(0));
                System.out.println("Response Lenght=" + jsonArray.length());

//      JSONArray jsonArray = jsonObject.getJSONArray("todos");           // [  {},{},{}  ]    <-- array
//      JSONObject jsonObject2 = jsonObject.getJSONObject("todos");       //  { {},{},{} }  <-- Object

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                    System.out.println("" + jsonObject1);
//                    API_Todo ntodo =new API_Todo(11,11,"Jai",true);
                    API_Todo todo = new API_Todo(
                            jsonObject1.getInt("userId"),
                            jsonObject1.getInt("id"),
                            jsonObject1.getString("title"),
                            jsonObject1.getBoolean("completed"));
                    mTodoList.add(todo);
                }
            } catch (JSONException e) {
                ToastUtil.makeLongToast(getApplicationContext(), "there is an error wrt json");
                e.printStackTrace();
            }

            API_TodoAdapter adapter = new API_TodoAdapter(getApplicationContext(), mTodoList);
            mTodoListView.setAdapter(adapter);

        },
                error -> {
                    ToastUtil.makeLongToast(getApplicationContext(), "there is an error in response");
                });

        requestQueue.add(stringRequest);

    }

}
