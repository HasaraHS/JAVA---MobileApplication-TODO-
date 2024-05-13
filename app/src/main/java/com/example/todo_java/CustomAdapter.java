package com.example.todo_java;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> task_id, task_title, task_priority, task_deadline, task_description;

    // Constructor
    public CustomAdapter(Activity activity, Context context, ArrayList<String> task_id, ArrayList<String> task_title, ArrayList<String> task_priority, ArrayList<String> task_deadline, ArrayList<String> task_description) {
        this.activity = activity;
        this.context = context;
        this.task_id = task_id;
        this.task_title = task_title;
        this.task_priority = task_priority;
        this.task_deadline = task_deadline;
        this.task_description = task_description;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
        holder.task_title_txt.setText(String.valueOf(task_title.get(position)));
        holder.task_priority_txt.setText(String.valueOf(task_priority.get(position)));
        holder.task_deadline_txt.setText(String.valueOf(task_deadline.get(position)));
        holder.task_description_txt.setText(String.valueOf(task_description.get(position)));

        String priority = task_priority.get(position);
        int backgroundColor = getBackgroundColorForPriority(priority);
        holder.itemView.setBackgroundColor(backgroundColor);
        // RecyclerView onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("title", String.valueOf(task_title.get(position)));
                intent.putExtra("priority", String.valueOf(task_priority.get(position)));
                intent.putExtra("deadline", String.valueOf(task_deadline.get(position)));
                intent.putExtra("description", String.valueOf(task_description.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    // ViewHolder class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView task_id_txt, task_title_txt, task_priority_txt, task_deadline_txt, task_description_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_id_txt = itemView.findViewById(R.id.task_id_txt);
            task_title_txt = itemView.findViewById(R.id.task_title_txt);
            task_priority_txt = itemView.findViewById(R.id.task_priority_txt);
            task_deadline_txt = itemView.findViewById(R.id.task_deadLine_txt);
            task_description_txt = itemView.findViewById(R.id.task_description_txt);
        }
    }

    // Method to get background color based on priority
    private int getBackgroundColorForPriority(String priority) {
        switch (priority) {
            case "High":
                return ContextCompat.getColor(context, R.color.highPriorityColor);
            case "Medium":
                return ContextCompat.getColor(context, R.color.mediumPriorityColor);
            case "Low":
                return ContextCompat.getColor(context, R.color.lowPriorityColor);
            default:
                return ContextCompat.getColor(context, R.color.defaultPriorityColor);
        }
    }
}
