package com.projectaty.activities.taskmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.model.TaskAdapter;
import com.projectaty.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        int projectID = getIntent().getIntExtra("projectID",0);
        String status = getIntent().getStringExtra("status");
        String keyword= getIntent().getStringExtra("keyword");
        String month = getIntent().getStringExtra("month");

        setFind(findViewById(R.id.find));
        setAdd(findViewById(R.id.add));
        setTaskListView(findViewById(R.id.taskListView));

        TaskAdapter taskAdapter = new TaskAdapter(this, getTasksData(projectID, status, keyword, month));
        getTaskListView().setAdapter(taskAdapter);

        /*
            Button Handlers
            - based on status, and to a specific project ID
         */
        handle_add(getAdd(), status,  projectID);
        hadnle_find(getFind(), status,  projectID);
    }

    private List<Task> getTasksData(int projectID, String status, String keyword, String month) {
        /* Make a volley request to show tasks within a specific status fpr specific id  */
        List<Task> tasks = new ArrayList<>();
        if(status.equals("todo")){
            //gettodo
        }else if(status.equals("done")){
            // getDone
        }else{
            // in progress
        }

        return tasks;
    }

    /*
    Buttons Handlers

     */
    private void hadnle_find(Button find, String status, int projectID) {
        find.setOnClickListener(e->{
            Intent intent = new Intent(this, SearchTask.class);
            intent.putExtra("projectID",projectID);
            intent.putExtra("status",status );
            startActivity(intent);
        });
    }

    private void handle_add(Button add, String status, int projectID) {
        add.setOnClickListener(e->{
            Intent intent = new Intent(this, CreateTask.class);
            intent.putExtra("projectID",projectID );
            intent.putExtra("status",status );
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