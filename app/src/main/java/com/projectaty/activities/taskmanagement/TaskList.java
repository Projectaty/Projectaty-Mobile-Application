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
        setTaskListView(findViewById(R.id.taskListView));

        List<Task> tasks = new ArrayList<>();

        Task task1 = new Task("Download The Requirements", "Description for Task 1", 1, LocalDate.now());
        Task task2 = new Task("Sent the Email Submit", "Description for Task 2", 2, LocalDate.now().plusDays(1));
        Task task3 = new Task("Create ERD", "Description for Task 3", 3, LocalDate.now().plusDays(2));

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        getTaskListView().setAdapter(taskAdapter);

        setFind(findViewById(R.id.find));
        setAdd(findViewById(R.id.add));
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
            Intent intent = new Intent(this, CreateTask.class);
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