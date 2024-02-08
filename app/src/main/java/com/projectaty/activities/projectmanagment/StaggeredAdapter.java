package com.projectaty.activities.projectmanagment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.projectaty.R;
import com.projectaty.activities.taskmanagement.TasksDashboard;

public class StaggeredAdapter
        extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private String[] Projects;

    public StaggeredAdapter(String[] projects) {
        Projects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_prj,
                parent,
                false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView txt = (TextView) cardView.findViewById(R.id.txtName);
        txt.setText(Projects[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TasksDashboard.class);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return Projects.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

}
