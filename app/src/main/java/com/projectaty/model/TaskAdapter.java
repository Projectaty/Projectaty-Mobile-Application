package com.projectaty.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.projectaty.R;
import com.projectaty.activities.taskmanagement.UpdateDelTask;
import com.projectaty.model.Task;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    /*
        In this class I create a custom Array Adapter for the books listveiw
        Inspired by:
            https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
     */
    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);
        }

        TextView taskdate = convertView.findViewById(R.id.taskdate);
        TextView taskTitle = convertView.findViewById(R.id.taskTitle);
        TextView assignee = convertView.findViewById(R.id.assignee);
        ImageView isDoneimg = convertView.findViewById(R.id.isDoneimg);

        if (task != null) {
//            if(task.getStatus().equals("done")){
//                isDoneimg.setImageDrawable(R.drawable.done);
//            }else  if(task.getStatus().equals("todo")){
//                isDoneimg.setImageDrawable(R.drawable.notDone);
//            }else {
//                // in progress
//            }
            taskTitle.setText(task.getTitle());
            taskdate.setText(task.getDate().toString());
            assignee.setText(task.getAssignedTo()+"");
        }

        RelativeLayout relLay = convertView.findViewById(R.id.relLaytask);
        relLay.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UpdateDelTask.class);
            intent.putExtra("taskID", task.getTaskID());
            intent.putExtra("projectID",task.getProjectID());
            intent.putExtra("status",task.getStatus() );
            getContext().startActivity(intent);
        });
        return convertView;
    }
}