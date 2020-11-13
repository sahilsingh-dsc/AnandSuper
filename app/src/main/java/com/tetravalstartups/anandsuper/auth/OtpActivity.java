package com.tetravalstartups.anandsuper.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.api.ApiClient;
import com.tetravalstartups.anandsuper.api.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etOTP1;
    private EditText etOTP2;
    private EditText etOTP3;
    private EditText etOTP4;
    private String number;
    private String userId;
    private TextView tvVerify;
    private TextView tvUserNumber;
    private ProgressBar progress_circular;
    private static final String TAG = "OtpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        initViews();
    }

    private void initViews() {
        etOTP1 = findViewById(R.id.etOTP1);
        etOTP2 = findViewById(R.id.etOTP2);
        etOTP3 = findViewById(R.id.etOTP3);
        etOTP4 = findViewById(R.id.etOTP4);

        progress_circular = findViewById(R.id.progress_circular);

        setUpOTPInputs();
        tvVerify = findViewById(R.id.tvVerify);
        tvUserNumber = findViewById(R.id.tvUserNumber);
        Intent intent = getIntent();
        number = intent.getStringExtra("number");
        userId = intent.getStringExtra("user_id");

        tvUserNumber.setText(number);

        tvVerify.setOnClickListener(this);
    }

    private void setUpOTPInputs() {
        etOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    etOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    etOTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    etOTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == tvVerify) {
            doUiValidation();
        }
    }

    private void doUiValidation() {
        if (etOTP1.getText().toString().trim().isEmpty()
                || etOTP2.getText().toString().trim().isEmpty()
                || etOTP3.getText().toString().trim().isEmpty()
                || etOTP4.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter vaild otp", Toast.LENGTH_SHORT).show();
        }
        String otp =
                etOTP1.getText().toString() +
                        etOTP2.getText().toString() +
                        etOTP3.getText().toString() +
                        etOTP4.getText().toString();
        doUserVerify(userId, otp);
    }

    private void doUserVerify(String userId, String otp) {
        tvVerify.setVisibility(View.GONE);
        progress_circular.setVisibility(View.VISIBLE);
        UserLogin userLogin = ApiClient.getRetrofitInstance().create(UserLogin.class);
        Call<OTPResponse> call = userLogin.verifyUser(userId, otp);
        call.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                if (response.body().getStatus().equals("success")) {
                    tvVerify.setVisibility(View.VISIBLE);
                    progress_circular.setVisibility(View.GONE);
                    Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if (response.body().getStatus().equals("error")){
                    tvVerify.setVisibility(View.VISIBLE);
                    progress_circular.setVisibility(View.GONE);
                    Toast.makeText(OtpActivity.this, "Invalid OTP", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "onResponse: Enter Valid OTP" );
                }
                else{
                    tvVerify.setVisibility(View.VISIBLE);
                    progress_circular.setVisibility(View.GONE);
                    Log.e(TAG, "onResponse: Somthing Went Wrong");
                }
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                tvVerify.setVisibility(View.VISIBLE);
                progress_circular.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}