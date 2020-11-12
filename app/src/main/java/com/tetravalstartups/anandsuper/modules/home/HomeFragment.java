package com.tetravalstartups.anandsuper.modules.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tetravalstartups.anandsuper.R;

public class HomeFragment extends Fragment {

    private View view;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return view;
    }

    private void initView() {

    }
}