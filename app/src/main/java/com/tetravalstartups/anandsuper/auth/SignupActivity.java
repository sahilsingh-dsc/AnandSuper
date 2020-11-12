package com.tetravalstartups.anandsuper.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.api.ApiClient;
import com.tetravalstartups.anandsuper.api.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etContactNumber;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView tvLogin;
    private TextView tvBackToLogin;
    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etContactNumber = findViewById(R.id.etContactNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvLogin = findViewById(R.id.tvLogin);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        tvLogin.setOnClickListener(this);
        tvBackToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvLogin) {
            doUiValidation();
        }
        if (v == tvBackToLogin) {
            backToLogin();
        }
    }

    private void backToLogin() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void doUiValidation() {
        String name = etName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            etName.requestFocus();
            etName.setError("Name Required");
            return;
        }
        String number = etContactNumber.getText().toString();
        if (TextUtils.isEmpty(number)) {
            etContactNumber.requestFocus();
            etContactNumber.setError("Number Required");
            return;
        }
        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.requestFocus();
            etEmail.setError("Email Required");
            return;
        }
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.requestFocus();
            etPassword.setError("Password Required");
            return;
        }
        String confirmPassword = etConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.requestFocus();
            etConfirmPassword.setError("Confirm Password");
            return;
        }

        doRegister(name, number, email, password);
    }

    private void doRegister(String name, String number, String email, String password) {
        UserLogin userLogin = ApiClient.getRetrofitInstance().create(UserLogin.class);
        Call<Register> call = userLogin.doUserRegister(name, number, email, password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                Log.e(TAG, "onResponse: " + response.code() + " " + response.message() + " " + response.body().getUserId());
                String user_id = response.body().getUserId().toString();
                if (response.code() == 200) {
                    Log.e(TAG, "onResponse: Success");
                    Intent intent = new Intent(SignupActivity.this, OtpActivity.class);
                    intent.putExtra("number", number);
                    intent.putExtra("user_id", user_id);
                    Log.e(TAG, "onResponse: " + user_id);
                    startActivity(intent);
                    finish();
                } else if (response.code() == 400) {
                    Log.e(TAG, "onResponse: Something went Wrong");
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}