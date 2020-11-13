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

public class VideoListActivity extends AppCompatActivity {
    private RecyclerView videoListRecyclerView;
    private VideoListAdapter videoListAdapter;
    private static final String TAG = "VideoListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        initViews();
    }

    private void initViews() {
        videoListRecyclerView = findViewById(R.id.videoListRecyclerView);

        Intent intent = getIntent();

        String topic_id = intent.getStringExtra("topic_id");

        videoListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserLogin userLogin = ApiClient.getRetrofitInstance().create(UserLogin.class);
        Call<GetVideoList> call = userLogin.getVideoList(topic_id);
        call.enqueue(new Callback<GetVideoList>() {
            @Override
            public void onResponse(Call<GetVideoList> call, Response<GetVideoList> response) {
                Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                if (response.body().getStatus().equals("success")) {
                    videoListAdapter = new VideoListAdapter(VideoListActivity.this, response.body().getData());
                    videoListRecyclerView.setAdapter(videoListAdapter);
                    videoListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GetVideoList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}