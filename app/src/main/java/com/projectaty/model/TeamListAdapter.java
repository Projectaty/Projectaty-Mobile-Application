package com.projectaty.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectaty.R;
import com.projectaty.activities.taskmanagement.UpdateDelTask;
import com.projectaty.model.Team;

import java.util.List;

public class TeamListAdapter extends ArrayAdapter<Team> {

    public TeamListAdapter(Context context, List<Team> teams) {
        super(context, 0, teams);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Team team = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_list_item, parent, false);
        }

        TextView teamName = convertView.findViewById(R.id.teamItemName);

        if (team != null) {
            teamName.setText(team.getTeamName());
        }

        RelativeLayout teamLayout = convertView.findViewById(R.id.teamLayout);
        teamLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UpdateDelTask.class);
            intent.putExtra("TeamID", position);
            intent.putExtra("TeamName",team.getTeamName() );
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
