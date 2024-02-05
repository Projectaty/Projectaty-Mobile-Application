package com.projectaty.activities.teamsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.projectaty.R;
import com.projectaty.activities.projectmanagment.ProjectList;

public class TeamDashboard extends AppCompatActivity {

    LinearLayout membersly;
    LinearLayout projectly;
    LinearLayout editly;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_dashboard);
        initialize();
    }

    private void initialize() {
        setLyMembers(findViewById(R.id.membersly));
        setLyProj(findViewById(R.id.projectly));
        setLyEdit(findViewById(R.id.editly));

        handle_member(getLyMembers());
        handle_proj(getLyProj());
        handle_edit(getLyEdit());
    }
    /*
    Handlers when clicking the layouts
     */
    private void handle_member(LinearLayout membersly) {
        membersly.setOnClickListener(e->{
            Intent intent = new Intent(this, MembersList.class);
            startActivity(intent);
        });
    }
    private void handle_proj(LinearLayout projectly) {
        projectly.setOnClickListener(e->{
            Intent intent = new Intent(this, ProjectList.class);
            startActivity(intent);
        });
    }
    private void handle_edit(LinearLayout editly) {
        editly.setOnClickListener(e->{
            Intent intent = new Intent(this, UpdateDelTeam.class);
            startActivity(intent);
        });
    }
    /*
    Getters & Setters
     */
    public LinearLayout getLyMembers() {
        return membersly;
    }
    public void setLyMembers(LinearLayout membersly) {
        this.membersly = membersly;
    }
    public LinearLayout getLyProj() {
        return projectly;
    }
    public void setLyProj(LinearLayout projectly) {
        this.projectly = projectly;
    }
    public LinearLayout getLyEdit() {
        return editly;
    }
    public void setLyEdit(LinearLayout editly) {
        this.editly = editly;
    }
}