package com.projectaty.activities.taskmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class TasksDashboard extends AppCompatActivity{
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

        handle_done(getLyDone());
        handle_todo(getLyTodo());
        hadnle_progress(getLyProg());
    }
    /*
    Handlers when clicking the layouts
     */
    private void hadnle_progress(LinearLayout lyProg) {
        lyProg.setOnClickListener(e->{
            Intent intent = new Intent(this, TaskList.class);
            startActivity(intent);
        });
    }
    private void handle_todo(LinearLayout lyTodo) {
        lyTodo.setOnClickListener(e->{
            Intent intent = new Intent(this, TaskList.class);
            startActivity(intent);
        });
    }
    private void handle_done(LinearLayout lyDone) {
        lyDone.setOnClickListener(e->{
            Intent intent = new Intent(this, TaskList.class);
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