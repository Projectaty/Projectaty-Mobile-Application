package com.projectaty.activities.taskmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class TasksDashboard extends AppCompatActivity{
    /*
    This is the tasks dashoborad, for each project there are three types of tasks
    - done, to do, in progress
    the taskList activity will show them based on what they chose
     */
    LinearLayout lyTodo;//todol
    LinearLayout lyDone;//done
    LinearLayout lyProg;//in progress

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_dashborad);
        initialize();
    }

    private void initialize() {
        setLyDone(findViewById(R.id.donely));
        setLyProg(findViewById(R.id.progly));
        setLyTodo(findViewById(R.id.toDoly));
        /*
            Will be showing the extra project ID and use it show the tasks based on it
        */
        int projectID = getIntent().getIntExtra("projectID", 0);
        handle_done(getLyDone(), projectID);
        handle_todo(getLyTodo(), projectID);
        hadnle_progress(getLyProg(), projectID);
    }
    /*
    Handlers when clicking the layouts
     */
    private void hadnle_progress(LinearLayout lyProg, int projectID) {
        lyProg.setOnClickListener(e->{
            Intent intent = new Intent(this, TaskList.class);
            intent.putExtra("projectID",projectID );
            intent.putExtra("status","inprogress" );
            startActivity(intent);
        });
    }
    private void handle_todo(LinearLayout lyTodo, int projectID) {
        lyTodo.setOnClickListener(e->{
            Intent intent = new Intent(this, TaskList.class);
            intent.putExtra("projectID",projectID );
            intent.putExtra("status","todo" );
            startActivity(intent);
        });
    }
    private void handle_done(LinearLayout lyDone, int projectID) {
        lyDone.setOnClickListener(e->{
            Intent intent = new Intent(this, TaskList.class);
            intent.putExtra("projectID",projectID );
            intent.putExtra("status","done" );
            startActivity(intent);
        });
    }
    /*
    Getters & Setters
     */
    public LinearLayout getLyTodo() {
        return lyTodo;
    }
    public void setLyTodo(LinearLayout lyTodo) {
        this.lyTodo = lyTodo;
    }
    public LinearLayout getLyDone() {
        return lyDone;
    }
    public void setLyDone(LinearLayout lyDone) {
        this.lyDone = lyDone;
    }
    public LinearLayout getLyProg() {
        return lyProg;
    }
    public void setLyProg(LinearLayout lyProg) {
        this.lyProg = lyProg;
    }
}