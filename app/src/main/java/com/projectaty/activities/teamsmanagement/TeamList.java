package com.projectaty.activities.teamsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.data.TeamListAdapter;
import com.projectaty.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamList extends AppCompatActivity {

//    private static List<Team> teams;
    private TeamListAdapter teamListAdapter;
    private Button findTeamBtn;
    private Button addNewTeam;
    private ListView teamListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list);
        initialize();
    }

    private void initialize() {
        setTeamListView(findViewById(R.id.teamListView));

        List<Team> teams = new ArrayList<>();

        TeamListAdapter teamListAdapter = new TeamListAdapter(this, teams);
        getTeamListView().setAdapter(teamListAdapter);

        setFindTeamBtn(findViewById(R.id.findTeam));
        setAddNewTeamBtn(findViewById(R.id.addNewTeam));
        handle_addTeam(getAddNewTeamBtn());
        hadnle_findTeam(getFindTeamBtn());
    }

    /*
    Buttons Handlers
     */
    private void hadnle_findTeam(Button findTeamBtn) {
        findTeamBtn.setOnClickListener(e -> {
            Intent intent = new Intent(this, SearchTeam.class);
            startActivity(intent);
        });
    }

    private void handle_addTeam(Button addNewTeam) {
        addNewTeam.setOnClickListener(e -> {
            Intent intent = new Intent(this, CreateTeam.class);
            startActivity(intent);
        });
    }

    private void handleTeamListView() {

    }

     /*
    Getters & setters
     */

    public Button getFindTeamBtn() {
        return findTeamBtn;
    }

    public void setFindTeamBtn(Button findTeamBtn) {
        this.findTeamBtn = findTeamBtn;
    }

    public Button getAddNewTeamBtn() {
        return addNewTeam;
    }

    public void setAddNewTeamBtn(Button addNewTeam) {
        this.addNewTeam = addNewTeam;
    }

    public ListView getTeamListView() {
        return teamListView;
    }

    public void setTeamListView(ListView teamListView) {
        this.teamListView = teamListView;
    }
}
