package org.o7planning.simplelistview.DataPassing.ActivityToActivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.R;

public class ProfileActivityData extends AppCompatActivity {

    public String name = "Not Entered";      // NOTE : static is necessary ,idk why i wrote this
    public String address = "Not Entered";
    public String message = "Not Entered";
    TextView mName;
    TextView mAddress;
    TextView mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_data);

        mName = findViewById(R.id.entered_name);               // NOTE : Dont forget to set the EditText property to true of these textviews
        mAddress = findViewById(R.id.entered_address);
        mMessage = findViewById(R.id.entered_message);

        setText();
        setFragment();
    }

    private void setText() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (!bundle.getString("name").equals("")) {
                name = bundle.getString("name");
            }
            if (!bundle.getString("address").equals("")) {
                address = bundle.getString("address");
            }
            if (!bundle.getString("message").equals("")) {
                message = bundle.getString("message");
            }
        }
        mName.setText(name);
        mAddress.setText(address);
        mMessage.setText(message);

    }

    private void setFragment() {


    }

}
