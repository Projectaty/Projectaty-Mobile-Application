package com.projectaty.activities.projectmanagment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.projectaty.R;
import com.projectaty.activities.taskmanagement.TasksDashboard;
import com.projectaty.model.Project;

import java.util.ArrayList;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private ArrayList<Project> projects;
    private Context context;

    public StaggeredAdapter(ArrayList<Project> projects, Context context) {
        this.projects = projects;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_prj, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView projectName = cardView.findViewById(R.id.viewName);
        projectName.setText(projects.get(position).getTitle());

        TextView projectDate = cardView.findViewById(R.id.viewdate);
        projectDate.setText(projects.get(position).getDeadline() + "");
        ImageButton edit = cardView.findViewById(R.id.editButton);
        edit.setOnClickListener(e->{
            Intent intent = new Intent(context, UpdateDelProject.class);
            intent.putExtra("projectID", position);
            context.startActivity(intent);
        });
        setCardClickListener(cardView, projects.get(position).getProjectID());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    private void setCardClickListener(CardView cardView, final int position) {
        cardView.setOnClickListener(e->{
            Intent intent = new Intent(context, TasksDashboard.class);
            intent.putExtra("projectID", position);
            context.startActivity(intent);
        });
    }
}