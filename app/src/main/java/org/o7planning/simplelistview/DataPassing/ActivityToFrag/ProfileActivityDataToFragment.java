package org.o7planning.simplelistview.DataPassing.ActivityToFrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.o7planning.simplelistview.R;

public class ProfileActivityDataToFragment extends Fragment {

    public static String Name = "user_name";               // NOTE : user_name parameter is present in bundle and not name
    public static String Address = "user_address";         // This initialization is very very necessary
    public static String Message = "user_message";         // otherwise the key will be null
    private TextView mNameTextView;
    private TextView mAddressTextView;
    private TextView mMessageTextView;

    public ProfileActivityDataToFragment() {
        // Fragment constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = inflater.inflate(R.layout.fragment_profile_details, container, false);

        mNameTextView = fragment.findViewById(R.id.name_fragment);
        mAddressTextView = fragment.findViewById(R.id.address_fragment);
        mMessageTextView = fragment.findViewById(R.id.message_fragment);

        // Initialization by the data that was passed
        Bundle bundle = this.getArguments();

//        Gson gson = new Gson();
//        String obj = gson.toJson(bundle);
//        System.out.println("\n n \n Data=" + obj);

        if (bundle != null) {

            String name = bundle.getString("user_name");
            String address = bundle.getString(Address);
            String message = bundle.getString(Message);

            mNameTextView.setText(name);
            mAddressTextView.setText(address);                       // NOTE : You always forget to change
            mMessageTextView.setText(message);                       //        the editable property to true

        }
        return fragment;
    }

}
