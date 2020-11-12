package com.tetravalstartups.anandsuper.modules.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tetravalstartups.anandsuper.R;

import java.util.List;

public class CourseTopicAdapter extends RecyclerView.Adapter<CourseTopicAdapter.CourseTopicViewHolder> {
    private Context context;
    private List<CourseTopic.Datum> datumList;

    public CourseTopicAdapter(Context context, List<CourseTopic.Datum> datumList) {
        this.context = context;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public CourseTopicAdapter.CourseTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_course_topic, parent, false);
        return new CourseTopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseTopicAdapter.CourseTopicViewHolder holder, int position) {
        holder.tvCourseTopic.setText("" + datumList.get(position).getTopic());
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class CourseTopicViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCourseTopic;

        public CourseTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseTopic = itemView.findViewById(R.id.tvCourseTopic);
        }
    }
}
