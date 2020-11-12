package com.tetravalstartups.anandsuper.modules.course;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.api.ApiClient;
import com.tetravalstartups.anandsuper.api.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CourseFragment extends Fragment {
    private View view;
    private RecyclerView coursesRecyclerView;
    private static final String TAG = "CourseFragment";
    private CourseAdapter courseAdapter;

    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_course, container, false);
        initView();
        return view;
    }

    private void initView() {
        coursesRecyclerView = view.findViewById(R.id.coursesRecyclerView);

        UserLogin userLogin = ApiClient.getRetrofitInstance().create(UserLogin.class);
        Call<CourseResponse> call = userLogin.courses();
        call.enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                if (response.code() == 200) {
                    courseAdapter = new CourseAdapter(getContext(), response.body().getData());
                    coursesRecyclerView.setAdapter(courseAdapter);
                    courseAdapter.notifyDataSetChanged();
                }else if (response.code() == 400) {
                    Log.e(TAG, "onResponse: " + response.message());
                } else {
                    Log.e(TAG, "onResponse: Something Went Wrong");
                }
            }

            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}