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

import com.projectaty.R;
import com.projectaty.activities.taskmanagement.UpdateDelTask;
import com.projectaty.activities.teamsmanagement.UpdateDelTeam;
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
        teamName.setText(team.getTeamName());

        RelativeLayout relLay = convertView.findViewById(R.id.teamLayout);
        relLay.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UpdateDelTeam.class);
            intent.putExtra("teamID", team.getTeamID());
            intent.putExtra("teamName",team.getTeamName());
            intent.putExtra("describtion",team.getDescription() );
            getContext().startActivity(intent);
        });
        return convertView;
    }
}