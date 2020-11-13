package com.tetravalstartups.anandsuper.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.api.ApiClient;
import com.tetravalstartups.anandsuper.api.UserLogin;
import com.tetravalstartups.anandsuper.common.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSignUp;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvLogin;
    private ProgressBar progress_circular;
    private static final String TAG = "LoginActivity";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        tvSignUp = findViewById(R.id.tvSignUp);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        tvLogin = findViewById(R.id.tvLogin);
        progress_circular = findViewById(R.id.progress_circular);
        sharedpreferences = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);
        tvSignUp.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvSignUp) {
            goToSignUp();
        }
        if (v == tvLogin) {
            doUIValidation();
        }
    }

    private void doUIValidation() {
        String userName = etEmail.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            etEmail.requestFocus();
            etEmail.setError("Username/Phone Required");
            return;
        }
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.requestFocus();
            etPassword.setError("Password Required");
            return;
        }
        login(userName, password);
    }

    private void login(String userName, String password) {
        tvLogin.setVisibility(View.GONE);
        progress_circular.setVisibility(View.VISIBLE);
        UserLogin userLogin = ApiClient.getRetrofitInstance().create(UserLogin.class);
        Call<LoginResponse> call = userLogin.doLogin(userName, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                if (response.body().getStatus().equals("success")) {
                    tvLogin.setVisibility(View.VISIBLE);
                    progress_circular.setVisibility(View.GONE);
                    SharedPreferences pref = getSharedPreferences("login", 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("state", 1);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);
                    finish();
                } else if (response.code() == 400) {
                    tvLogin.setVisibility(View.VISIBLE);
                    progress_circular.setVisibility(View.GONE);
                    Log.e(TAG, "onResponse: Invalid Username Or Password");
                } else {
                    tvLogin.setVisibility(View.VISIBLE);
                    progress_circular.setVisibility(View.GONE);
                    Log.e(TAG, "onResponse: Something Went Wrong");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                tvLogin.setVisibility(View.VISIBLE);
                progress_circular.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void goToSignUp() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }
}