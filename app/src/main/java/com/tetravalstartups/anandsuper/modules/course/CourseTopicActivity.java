package com.tetravalstartups.anandsuper.modules.course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tetravalstartups.anandsuper.R;
import com.tetravalstartups.anandsuper.api.ApiClient;
import com.tetravalstartups.anandsuper.api.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseTopicActivity extends AppCompatActivity {
    private RecyclerView courseTopicRecyclerView;
    private CourseTopicAdapter courseTopicAdapter;
    private static final String TAG = "CourseTopicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_topic);
        initView();
    }

    private void initView() {
        courseTopicRecyclerView = findViewById(R.id.courseTopicRecyclerView);
        courseTopicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();

        String courseId = intent.getStringExtra("course_id");

        UserLogin userLogin = ApiClient.getRetrofitInstance().create(UserLogin.class);
        Call<CourseTopic> call = userLogin.getCourseTopic(courseId, "hindi");
        call.enqueue(new Callback<CourseTopic>() {
            @Override
            public void onResponse(Call<CourseTopic> call, Response<CourseTopic> response) {
                Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                if (response.code() == 200) {
                    courseTopicAdapter = new CourseTopicAdapter(CourseTopicActivity.this, response.body().getData());
                    courseTopicRecyclerView.setAdapter(courseTopicAdapter);
                    courseTopicAdapter.notifyDataSetChanged();
                } else if (response.code() == 400) {
                    Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CourseTopic> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage());
            }
        });
    }
}