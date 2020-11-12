package com.tetravalstartups.anandsuper.common;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.modules.course.CourseFragment;
import com.tetravalstartups.anandsuper.modules.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout lvHome;
    private LinearLayout lvProfile;

    private ImageView ivHome;
    private ImageView ivProfile;

    private TextView tvHome;
    private TextView tvProfile;

    private FrameLayout mainContent;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        lvHome = findViewById(R.id.lvHome);
        lvProfile = findViewById(R.id.lvProfile);
        mainContent = findViewById(R.id.mainContent);

        ivHome = findViewById(R.id.ivHome);
        ivProfile = findViewById(R.id.ivProfile);
        lvProfile = findViewById(R.id.lvProfile);

        tvHome = findViewById(R.id.tvHome);
        tvProfile = findViewById(R.id.tvProfile);

        lvHome.setOnClickListener(this);
        lvProfile.setOnClickListener(this);

        homeSelected();
        profileUnSelected();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainContent, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        if (v == lvHome) {
            homeSelected();
            profileUnSelected();
        }
        if (v == lvProfile) {
            profileSelected();
            homeUnselected();
        }
    }

    private void homeSelected() {
        fragment = new CourseFragment();
        loadFragment(fragment);
        ivHome.setBackground(getResources().getDrawable(R.drawable.menu_enable));
        ivHome.setImageDrawable(getResources().getDrawable(R.drawable.home_enable));
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private void homeUnselected() {
        ivHome.setBackground(getResources().getDrawable(R.drawable.menu_disable));
        ivHome.setImageDrawable(getResources().getDrawable(R.drawable.home_disable));
        tvHome.setTextColor(getResources().getColor(R.color.colorTextH2));
    }

    private void profileSelected() {
        fragment = new ProfileFragment();
        loadFragment(fragment);
        ivProfile.setBackground(getResources().getDrawable(R.drawable.menu_enable));
        ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.profile_enable));
        tvProfile.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private void profileUnSelected() {
        ivProfile.setBackground(getResources().getDrawable(R.drawable.menu_disable));
        ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.profile_disable));
        tvProfile.setTextColor(getResources().getColor(R.color.colorTextH2));
    }
}