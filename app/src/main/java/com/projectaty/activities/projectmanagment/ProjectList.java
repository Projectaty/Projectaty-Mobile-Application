package com.projectaty.activities.projectmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.activities.taskmanagement.CreateTask;
import com.projectaty.activities.taskmanagement.SearchTask;
import com.projectaty.data.TaskAdapter;
import com.projectaty.model.Project;
import com.projectaty.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectList extends AppCompatActivity  {
    Button find;
    Button add;
    ListView PrjListView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_list);
        initialize();
    }
    private void initialize(){
        setPrjListView(findViewById(R.id.prjRecyclerView));
        setFind(findViewById(R.id.find));
        setAdd(findViewById(R.id.add));

        List<Project> projects = new ArrayList<>();
        handle_add(getAdd());

     //   hadnle_find(getFind());

    }
/*    private void hadnle_find(Button find) {
        find.setOnClickListener(e->{
            Intent intent = new Intent(this, SearchTask.class);
            startActivity(intent);
        });
    }*/
    private void handle_add(Button add) {
        add.setOnClickListener(e->{
            Intent intent = new Intent(this, CreateProject.class);
            startActivity(intent);
        });
    }
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

    public ListView getPrjListView() {
        return PrjListView;
    }

    public void setPrjListView(ListView prjListView) {
        PrjListView = prjListView;
    }
}
