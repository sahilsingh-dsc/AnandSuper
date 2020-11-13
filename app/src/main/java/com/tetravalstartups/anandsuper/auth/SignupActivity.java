package com.tetravalstartups.anandsuper.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView ivGoBack;
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
        ivGoBack = findViewById(R.id.ivGoBack);

        tvLogin.setOnClickListener(this);
        tvBackToLogin.setOnClickListener(this);
        ivGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvLogin) {
            doUiValidation();
        }
        if (v == tvBackToLogin) {
            backToLogin();
        }
        if (v == ivGoBack) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
//            onBackPressed();
        }
    }

    private void backToLogin() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void doUiValidation() {
        String name = etName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            etName.requestFocus();
            etName.setError("Name Required");
            return;
        }
        String number = etContactNumber.getText().toString();
        if (TextUtils.isEmpty(number) ) {
            etContactNumber.requestFocus();
            etContactNumber.setError("Enter Correct Number");
            return;
        }
        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email) || !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
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
        if (!confirmPassword.equals(password)){
            Toast.makeText(this, "Password Didn't Matched", Toast.LENGTH_SHORT).show();
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
                if (response.body().getStatus().equals("success")) {
                    Log.e(TAG, "onResponse: Success");
                    Intent intent = new Intent(SignupActivity.this, OtpActivity.class);
                    intent.putExtra("number", number);
                    intent.putExtra("user_id", user_id);
                    Log.e(TAG, "onResponse: " + user_id);
                    startActivity(intent);
                    finish();
                } else if (response.body().getStatus().equals("error")) {
                    Toast.makeText(SignupActivity.this, "The contact is already registered", Toast.LENGTH_LONG).show();
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