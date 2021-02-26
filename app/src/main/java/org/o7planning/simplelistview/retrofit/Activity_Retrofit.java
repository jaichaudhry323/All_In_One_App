package org.o7planning.simplelistview.retrofit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.retrofit.Retrofit_Models.UserRequest;
import org.o7planning.simplelistview.retrofit.Retrofit_Models.UserResponse;
import org.o7planning.simplelistview.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Retrofit extends AppCompatActivity {

    TextView mNameTextView;
    TextView mJobTextView;
    TextView mEmailTextView;
    TextView mPasswordTextView;

    Button mPostCreateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrofit);

        mNameTextView = findViewById(R.id.name_textview_retrofit);
        mEmailTextView = findViewById(R.id.email_textview_retrofit);
        mPasswordTextView = findViewById(R.id.password_textview_retrofit);
        mJobTextView = findViewById(R.id.job_textview_retrofit);

        mPostCreateButton = findViewById(R.id.post_create_button_retrofit);

        Gson gson = new Gson();

        mPostCreateButton.setOnClickListener(v->{

            String name = mNameTextView.getText().toString();
            String job = mJobTextView.getText().toString();

            UserRequest userRequest = new UserRequest(name,job);

            ServerApi.createUserByPostMethod(userRequest).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();
                    showMessage("Response",gson.toJson(userResponse));
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    ToastUtil.makeLongToast(getApplicationContext(),"Failed");
                }
            });

        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(Message);
        alert.show();
    }

}

