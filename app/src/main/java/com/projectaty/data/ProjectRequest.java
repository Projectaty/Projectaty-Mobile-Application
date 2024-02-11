package com.projectaty.data;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectRequest {
    private static final String TAG = ProjectRequest.class.getSimpleName();
    private RequestQueue requestQueue;
    private Context context;

    public ProjectRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }


    public void addProjects( String Title, String Description, int Deadline, boolean Privacy) {
        String url = URLs.ADD_PROJECT_URL;
        JSONObject jsonParams = new JSONObject();
        try {
          //  jsonParams.put("ProjectID", ProjectID);
            jsonParams.put("Title", Title);
            jsonParams.put("Description", Description);
            jsonParams.put("Deadline", Deadline);
            jsonParams.put("Privacy", Privacy);

        } catch (JSONException e) {
            e.printStackTrace();

        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }


    public void deleteProject(int projectID) {
        String url = URLs.DELETE_PROJECT_URL + projectID;
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.toString());
                    }
                });

        requestQueue.add(stringRequest);
    }


    public void updateProject( String Title, String Description, int Deadline, boolean Privacy) {
        String url = URLs.UPDATE_PROJECT_URL;
        JSONObject jsonParams = new JSONObject();
        try {
            //jsonParams.put("ProjectID", ProjectID);
            jsonParams.put("Title", Title);
            jsonParams.put("Description", Description);
            jsonParams.put("Deadline", Deadline);
            jsonParams.put("Privacy", Privacy);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    public void getAllProjects() {
        String url = URLs.GET_PROJECTS_URL;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
    public interface PROJECTByIdListener {
        void onSuccess(int ProjectID , String Title, String Description, int Deadline, boolean Privacy) ;
        void onError(VolleyError error);
    }

    public void geProjectById(int projectID, PROJECTByIdListener listener) {
        String url = URLs.GET_ONE_PROJECT_URL + projectID;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("ProjectID");
                            String name = response.getString("Title");
                            String description = response.getString("Description");
                            int deadline = response.getInt("Deadline");
                            boolean Privacy = response.getBoolean("Privacy");

                            listener.onSuccess(id, name, description,deadline, Privacy);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError(new VolleyError("Error parsing JSON response"));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.toString());
                        listener.onError(error);
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }


}
