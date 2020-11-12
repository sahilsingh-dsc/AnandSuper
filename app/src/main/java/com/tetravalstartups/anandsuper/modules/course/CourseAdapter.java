package com.tetravalstartups.anandsuper.modules.course;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tetravalstartups.anandsuper.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context context;
    private List<CourseResponse.Datum> list;

    public CourseAdapter(Context context, List<CourseResponse.Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_cources, parent, false);

        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {


        Glide.with(context).load("http://vedanshtechnologies.com/AnandSuper100/" + list.get(position).getPImage()).into(holder.ivCategory);
        holder.tvCategory.setText("" + list.get(position).getProductName());
        String course_id = list.get(position).getId();
        holder.lvCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseTopicActivity.class);
                intent.putExtra("course_id", course_id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        private ImageView ivCategory;
        private LinearLayout lvCourse;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            ivCategory = itemView.findViewById(R.id.ivCategory);
            lvCourse = itemView.findViewById(R.id.lvCourse);
        }
    }
}
