package com.projectaty.activities.projectmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projectaty.R;
import com.projectaty.activities.taskmanagement.TasksDashboard;
import com.projectaty.activities.teamsmanagement.TeamList;
import com.projectaty.activities.usermanagement.StudentProfile;
import com.projectaty.model.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Dashboard extends AppCompatActivity {
    Button Team;
    Button User;

    Button editButton;
    FloatingActionButton project;
    RecyclerView prjRecyclerView;

    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        initialize();

        queue = Volley.newRequestQueue(this);


    }

    private void initialize() {
        setProject(findViewById(R.id.addBtn));
        setUser(findViewById(R.id.User));
        setTeam(findViewById(R.id.Team));
        RecyclerView rc = findViewById(R.id.StaggerdPrjView);
        rc.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


    }


    public void ViewProj(View view) {


        String url = "https://";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> projects = new ArrayList<>();
                for (int i = 0; i < prjRecyclerView.getItemDecorationCount(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        projects.add(obj.getString("Title"));
                    }catch(JSONException exception){
                        Log.d("volley_error", exception.toString());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Dashboard.this,
                        android.R.layout.simple_list_item_1, projects);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);


    }



    /*
        On click Handlers
     */
    public void addProject (View view){
        Intent intent=new Intent(this , CreateProject.class);
        startActivity(intent);
    }

    public void editPrj (View view, int projId){
        Intent intent=new Intent(this , UpdateDelProject.class);
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

    public void goToTasks (View view){
        Intent intent=new Intent(this , TasksDashboard.class);
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