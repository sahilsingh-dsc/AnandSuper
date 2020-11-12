package com.tetravalstartups.anandsuper.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tetravalstartups.anandsuper.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout lvHome;
    private LinearLayout lvProfile;

    private ImageView ivHome;
    private ImageView ivProfile;

    private TextView tvHome;
    private TextView tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lvHome = findViewById(R.id.lvHome);
        lvProfile = findViewById(R.id.lvProfile);

        ivHome = findViewById(R.id.ivHome);
        ivProfile = findViewById(R.id.ivProfile);

        tvHome = findViewById(R.id.tvHome);
        tvProfile = findViewById(R.id.tvProfile);
    }
}