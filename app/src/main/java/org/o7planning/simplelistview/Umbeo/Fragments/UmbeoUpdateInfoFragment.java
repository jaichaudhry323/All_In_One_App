package org.o7planning.simplelistview.Umbeo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.o7planning.simplelistview.R;

public class UmbeoUpdateInfoFragment extends BottomSheetDialogFragment {

    public UmbeoUpdateInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_umbeo_user_data_update_bottom_sheet,container,false);
    }
}
