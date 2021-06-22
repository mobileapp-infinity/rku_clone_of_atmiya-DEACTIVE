package com.infinity.infoway.rkuniversity.intro_screen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infinity.infoway.rkuniversity.R;

public class FirstIntroFragment extends Fragment {


    public FirstIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_intro, container, false);
    }
}