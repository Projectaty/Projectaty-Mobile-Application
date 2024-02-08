package com.projectaty.activities.projectmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.projectaty.R;
import com.projectaty.activities.teamsmanagement.TeamList;
import com.projectaty.activities.usermanagement.StudentProfile;

public class Dashboard extends AppCompatActivity {

    Button Team;
    Button User;
    Button project;
    ListView prjRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        initialize();
    }

    private void initialize(){
        setProject(findViewById(R.id.prjRecyclerView));
        setUser(findViewById(R.id.User));
        setTeam(findViewById(R.id.Team));

    }


    public void addProject (View view){
        Intent intent=new Intent(this , CreateProject.class);
        startActivity(intent);
    }
    public void teams (View view){
        Intent intent=new Intent(this , TeamList.class);
        startActivity(intent);
    }
    public void profile (View view){
        Intent intent=new Intent(this , StudentProfile.class);
        startActivity(intent);
    }


    public Button getTeam() {
        return Team;
    }

    public void setTeam(Button team) {
        Team = team;
    }

    public Button getUser() {
        return User;
    }

    public void setUser(Button user) {
        User = user;
    }

    public Button getProject() {
        return project;
    }

    public void setProject(Button project) {
        this.project = project;
    }

    public ListView getPrjRecyclerView() {
        return prjRecyclerView;
    }

    public void setPrjRecyclerView(ListView prjRecyclerView) {
        this.prjRecyclerView = prjRecyclerView;
    }
}