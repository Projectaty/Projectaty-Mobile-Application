package com.projectaty.activities.projectmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projectaty.R;
import com.projectaty.activities.teamsmanagement.TeamList;
import com.projectaty.activities.usermanagement.StudentProfile;
import com.projectaty.model.Project;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    Button Team;
    Button User;
    FloatingActionButton project;
    RecyclerView prjRecyclerView;



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
        setProject(findViewById(R.id.addBtn));
        setUser(findViewById(R.id.User));
        setTeam(findViewById(R.id.Team));
        setPrjRecyclerView(findViewById(R.id.prjRecyclerView));
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

    public FloatingActionButton getProject() {
        return project;
    }

    public void setProject(FloatingActionButton project) {
        this.project = project;
    }

    public RecyclerView getPrjRecyclerView() {
        return prjRecyclerView;
    }

    public void setPrjRecyclerView(RecyclerView prjRecyclerView) {
        this.prjRecyclerView = prjRecyclerView;
    }
}