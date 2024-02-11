package com.projectaty.activities.teamsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.model.TeamListAdapter;
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
        /*
        Sample
         */
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1, "Team A", "Description A", "Public"));
        teams.add(new Team(2, "Team B", "Description B", "Private"));
        teams.add(new Team(3, "Team C", "Description C", "Public"));
        teams.add(new Team(4, "Team D", "Description D", "Private"));
        teams.add(new Team(5, "Team E", "Description E", "Public"));

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
