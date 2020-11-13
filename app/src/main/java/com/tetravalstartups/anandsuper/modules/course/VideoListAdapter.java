package com.tetravalstartups.anandsuper.modules.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tetravalstartups.anandsuper.R;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {
    private Context context;
    private List<GetVideoList.Datum> datumList;

    public VideoListAdapter(Context context, List<GetVideoList.Datum> datumList) {
        this.context = context;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public VideoListAdapter.VideoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_get_video_list, parent, false);
        return new VideoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.VideoListViewHolder holder, int position) {
        Glide.with(context).load("http://vedanshtechnologies.com/AnandSuper100/" + datumList.get(position).getVImage()).into(holder.ivVideoImage);
        holder.tvVideoTitle.setText("" + datumList.get(position).getVTitle());
        holder.tvVideoDescription.setText("" + datumList.get(position).getVDescription());
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class VideoListViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivVideoImage;
        private TextView tvVideoTitle;
        private TextView tvVideoDescription;

        public VideoListViewHolder(@NonNull View itemView) {
            super(itemView);

            ivVideoImage = itemView.findViewById(R.id.ivVideoImage);
            tvVideoTitle = itemView.findViewById(R.id.tvVideoTitle);
            tvVideoDescription = itemView.findViewById(R.id.tvVideoDescription);
        }
    }
}
