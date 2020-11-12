package com.tetravalstartups.anandsuper.modules.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.auth.LoginActivity;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btnLogout;
    private SharedPreferences pref;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView();
        return view;
    }

    private void initView() {
        pref = getActivity().getSharedPreferences("login", 0);
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogout) {
            logOut();
        }
    }

    private void logOut() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("state", 0);
        editor.apply();
        gotoLogin();
    }

    private void gotoLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getContext().startActivity(intent);
        getActivity().finish();

    }
}