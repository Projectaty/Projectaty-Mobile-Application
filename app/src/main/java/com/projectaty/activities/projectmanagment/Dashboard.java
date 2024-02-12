package com.projectaty.activities.projectmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.projectaty.data.URLs;
import com.projectaty.model.Project;
import com.projectaty.model.StaggeredAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    Button Team;
    Button User;
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
        setPrjRecyclerView(findViewById(R.id.StaggerdPrjView));
        getPrjRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        ArrayList<Project> projectList = new ArrayList<>();
        Project project1 = new Project(1, "Installation", "Creating an immersive art experience to evoke empathy", LocalDate.of(2024, 4, 15), "Public", 101);
        Project project2 = new Project(2, "Journaling Platform", "Developing a platform for users to practice mindfulness through journaling", LocalDate.of(2024, 5, 10), "Private", 102);
        Project project3 = new Project(3, "Community", "Connecting neighbors to share gardening tips and resources", LocalDate.of(2024, 6, 20), "Protected", 103);
        Project project4 = new Project(4, "Mental Awareness", "Designing a campaign to raise awareness about mental health issues", LocalDate.of(2024, 7, 5), "Public", 104);
        Project project5 = new Project(5, "Educational Game", "Creating an interactive game to make learning fun for young children", LocalDate.of(2024, 8, 15), "Private", 105);
        Project project6 = new Project(6, "Workshop Series", "Organizing workshops to promote sustainable living practices", LocalDate.of(2024, 9, 30), "Public", 106);

        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);
        projectList.add(project4);
        projectList.add(project5);
        projectList.add(project6);

        StaggeredAdapter adapter = new StaggeredAdapter(projectList, this);
        getPrjRecyclerView().setAdapter(adapter);
    }

    public void ViewProj(View view) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLs.GET_PROJECTS_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray projsArray = response.getJSONArray("projects");
                    if (projsArray.length() > 0) {
                        ArrayList<Project> projects = new ArrayList<>();
                        for (int i = 0; i < projsArray.length(); i++) {
                            try {
                                JSONObject obj = projsArray.getJSONObject(i);

                                int id = obj.optInt("ProjectID");
                                String title = obj.optString("Title");
                                String des = obj.getString("Description");
                                LocalDate date = LocalDate.parse(obj.getString("Deadline"));
                                String privacy = obj.optString("privacySetting");
                                int creatorID = obj.optInt("creatorID", 0);
                                Project proj = new Project(id, title, des, date, privacy, creatorID);
                                projects.add(proj);
                            } catch (JSONException exception) {
                                Log.d("volley_error", exception.toString());
                            }
                        }
                        StaggeredAdapter adapter = new StaggeredAdapter(projects, Dashboard.this);
                        getPrjRecyclerView().setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                }
            },
            new Response.ErrorListener() {
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