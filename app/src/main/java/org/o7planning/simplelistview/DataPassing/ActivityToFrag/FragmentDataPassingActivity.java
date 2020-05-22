package org.o7planning.simplelistview.DataPassing.ActivityToFrag;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.o7planning.simplelistview.DataPassing.ActivityToFrag.ProfileActivityDataToFragment;
import org.o7planning.simplelistview.R;

public class FragmentDataPassingActivity extends AppCompatActivity {

    private ProfileActivityDataToFragment mProfileActivityDataToFragment;
    private FragmentManager mFragmentManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);                        // First set  a layout

        mFragmentManager = getSupportFragmentManager();                  // get the object that does fragment transplant

        // create a new fragment
        mProfileActivityDataToFragment = new ProfileActivityDataToFragment();

        // set args data that you may wanna pass
        // ALL 3 METHODS CORRECT
        Bundle bundle = new Bundle();
        bundle.putString(ProfileActivityDataToFragment.Message, "LOL 1");
        bundle.putString(ProfileActivityDataToFragment.Name, "Jai 1");
        bundle.putString(ProfileActivityDataToFragment.Address, "Yojana Vihar 1");

//        bundle.putString(mProfileActivityDataFragment.Message, "LOL 2");
//        bundle.putString(mProfileActivityDataFragment.Name, "Jai 2");
//        bundle.putString(mProfileActivityDataFragment.Address, "Yojana Vihar 2");
//
//        bundle.putString("user_name", "Jai 3");
//        bundle.putString("user_address", "Yojana Vihar 3");
//        bundle.putString("user_message", "LOL 3");
        mProfileActivityDataToFragment.setArguments(bundle);
//
//        Gson gson =new Gson();
//        String obj=gson.toJson(bundle);
//        System.out.println("\n n \n Data In EmptyActivity ="+obj);

        // Set the fragment finally
        // fragment will be placed in fragment_layout container of empty activity view
        mFragmentManager.beginTransaction().add(R.id.fragment_layout, mProfileActivityDataToFragment).commit();
    }

}
