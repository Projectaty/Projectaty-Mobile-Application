package com.projectaty.activities.taskmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.activities.usermanagement.CreateAccount;

public class TaskList extends AppCompatActivity {
    Button find;
    Button add;
    ListView taskListView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);
        initialize();
    }

    private void initialize() {
        setFind(findViewById(R.id.find));
        setAdd(findViewById(R.id.add));
        setTaskListView(findViewById(R.id.taskListView));
        handle_add(getAdd());
        hadnle_find(getFind());
    }
    /*
    Buttons Handlers
     */
    private void hadnle_find(Button find) {
        find.setOnClickListener(e->{
            Intent intent = new Intent(this, SearchTask.class);
            startActivity(intent);
        });
    }

    private void handle_add(Button add) {
        add.setOnClickListener(e->{
            Intent intent = new Intent(this, CreateAccount.class);
            startActivity(intent);
        });
    }

    /*
    Getters & setters
     */

    public Button getFind() {
        return find;
    }
    public void setFind(Button find) {
        this.find = find;
    }
    public Button getAdd() {
        return add;
    }
    public void setAdd(Button add) {
        this.add = add;
    }
    public ListView getTaskListView() {
        return taskListView;
    }
    public void setTaskListView(ListView taskListView) {
        this.taskListView = taskListView;
    }
}