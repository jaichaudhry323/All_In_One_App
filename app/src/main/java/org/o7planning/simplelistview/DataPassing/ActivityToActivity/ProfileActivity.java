package org.o7planning.simplelistview.DataPassing.ActivityToActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.R;

// AppCompatActivity contains all the basic functions like onCreate() onResume() onDestroy()
// and many more like this

public class ProfileActivity extends AppCompatActivity {

    private String name = "NAN";
    private String address = "NAN";
    private String message = "NAN";
    TextView mName;
    TextView mAddress;
    TextView mMessage;
    Button mViewEnteredDataButton;
    private ProfileActivityData mProfileActivityData;

//    TextView mName =findViewById(R.id.entered_name);          // NOTE : This is wrong since u are referencing
//    TextView mAddress =findViewById(R.id.entered_address);    //        those elements that haven't yet been created
//    TextView mMessage =findViewById(R.id.entered_message);    //        that is why you need to put this inside onCreate()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        Button mPreviousButtton = findViewById(R.id.previous_button);
        mPreviousButtton.setOnClickListener(v -> {
            onNavigateUp();
        });


        mName = findViewById(R.id.entered_name);
        mAddress = findViewById(R.id.entered_address);
        mMessage = findViewById(R.id.entered_message);

        setEnteredDataButton();
    }

    public void setEnteredDataButton() {       // Passing data to ProfileActivityData activity via bundle
        Bundle args = new Bundle();

        mViewEnteredDataButton = findViewById(R.id.view_word_button);
        mViewEnteredDataButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivityData.class);

            name = mName.getText().toString();          // NOTE : toString(charSequence) is wrong
            address = mAddress.getText().toString();    //        xyz.toString() is right
            message = mMessage.getText().toString();

            args.putString("name", name);
            args.putString("address", address);
            args.putString("message", message);

            intent.putExtras(args);
            startActivity(intent);
        });

    }

}
