package com.projectaty.activities.projectmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.projectaty.R;
import com.projectaty.activities.teamsmanagement.TeamList;
import com.projectaty.activities.usermanagement.StudentProfile;
import com.projectaty.model.Project;

import java.util.ArrayList;

public class ProjectDashboard extends AppCompatActivity {

    Button Team;
    Button User;
    Button project;
    ListView prjRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        ArrayList<Project> projects = new ArrayList<>();

        RecyclerView rc = findViewById(R.id.StaggerdPrjView);
        rc.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));



        initialize();
    }



    private void initialize(){

        setUser(findViewById(R.id.User));
        setTeam(findViewById(R.id.Team));


    }


    public void addProject (View view){
        Intent intent=new Intent(this , CreateProject.class);
        startActivity(intent);
    }
    public void addTeam (View view){
        Intent intent=new Intent(this , TeamList.class);
        startActivity(intent);
    }
    public void addProfile (View view){
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