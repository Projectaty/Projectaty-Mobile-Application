package com.projectaty.activities.taskmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.projectaty.R;
import com.projectaty.data.TaskRequest;
import com.projectaty.data.VolleySingleton;
import com.projectaty.model.TaskAdapter;
import com.projectaty.model.Task;

import org.json.JSONException;

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
        Boolean isSearch  = getIntent().getBooleanExtra("isSearch", false);

        setFind(findViewById(R.id.find));
        setAdd(findViewById(R.id.add));
        setTaskListView(findViewById(R.id.taskListView));

        getTasksData(projectID, status,isSearch, keyword, month);

        /*
            Button Handlers
            - based on status, and to a specific project ID
         */
        handle_add(getAdd(), status,  projectID);
        hadnle_find(getFind(), status,  projectID);
    }

    private List<Task> getTasksData(int projectID, String status,Boolean isSearch,  String keyword, String month) {
        /* Make a volley request to show tasks within a specific status fpr specific id  */
        List<Task> tasks = new ArrayList<>();
        if(!isSearch){
            TaskRequest.getByStatus(VolleySingleton.getInstance(this),
                    new TaskRequest.TaskResponseCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            List<Task> tasks = (List<Task>) response;
                            updateTaskAdapter(tasks);
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Log.d("error", errorMessage);
                        }
                    }, status,projectID
            );
        } if(isSearch){
            //String keyword, String month
            TaskRequest.findByKeyOrMonth(VolleySingleton.getInstance(this),
                    new TaskRequest.TaskResponseCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            List<Task> tasks = (List<Task>) response;
                            updateTaskAdapter(tasks);
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Log.d("error", errorMessage);
                        }
                    }, projectID, keyword, month
            );
        }
        return tasks;
    }

    private void updateTaskAdapter(List<Task> tasks) {
        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        getTaskListView().setAdapter(taskAdapter);
    }

    /*
        Buttons Handlers */
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