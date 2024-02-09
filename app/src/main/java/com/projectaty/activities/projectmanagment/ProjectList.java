package com.projectaty.activities.projectmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.projectaty.R;
import com.projectaty.model.Project;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectList extends AppCompatActivity  {
    Button find;
    Button add;
    ListView PrjListView;

    private RequestQueue queue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_list);
        initialize();

        queue = Volley.newRequestQueue(this);
    }
    private void initialize(){
        setPrjListView(findViewById(R.id.prjRecyclerView));
        setFind(findViewById(R.id.find));
        setAdd(findViewById(R.id.add));

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

    public void addPrjList (View view) {


        String url = "";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<String> projects = new ArrayList<>();
                try {
                    JSONObject Title = response.getJSONObject("Title");
                    Iterator<String> keys = Title.keys();
                    while (keys.hasNext()) {

                        String activity = response.getString("Title");
                        projects.add(activity);

                    }
                } catch (JSONException exception) {
                    Log.d("volley_error", exception.toString());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProjectList.this,
                        android.R.layout.simple_list_item_1, projects);
                PrjListView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);


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
