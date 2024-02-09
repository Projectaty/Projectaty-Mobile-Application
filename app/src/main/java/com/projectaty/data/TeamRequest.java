package com.projectaty.data;

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

public class TeamRequest {
    private static final String TAG = TeamRequest.class.getSimpleName();
    private RequestQueue requestQueue;
    private Context context;

    public TeamRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void addTeam(int teamID, String teamName, String description, boolean isPrivate) {
        String url = URLs.ADD_TEAM_URL;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("TeamID", teamID);
            jsonObject.put("Team Name", teamName);
            jsonObject.put("Description", description);
            jsonObject.put("Private", isPrivate);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
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

    public void addTeamMember(int studentID, int teamID) {
        String url = URLs.ADD_TEAM_MEMBER_URL;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("StudentID", studentID);
            jsonObject.put("TeamID", teamID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
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

    public void addTeamProject(int projectID, int teamID) {
        String url = URLs.ADD_TEAM_PROJECT_URL;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ProjectID", projectID);
            jsonObject.put("TeamID", teamID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
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

    public void deleteTeam(int teamID) {
        String url = URLs.DELETE_TEAM_URL + teamID;
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

//    public void deleteTeamMember(int studentID, int teamID) {
//        String url = URLs.DELETE_TEAM_MEMBER_URL + studentID + teamID;
//        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, "Response: " + response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error: " + error.toString());
//                    }
//                });
//
//        requestQueue.add(stringRequest);
//    }

//    public void deleteTeamProject(int projectID, int teamID) {
//        String url = URLs.DELETE_TEAM_PROJECT_URL + projectID + teamID;
//        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, "Response: " + response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error: " + error.toString());
//                    }
//                });
//
//        requestQueue.add(stringRequest);
//    }

    public void updateTeam(int teamID, String teamName, String description, boolean isPrivate) {
        String url = URLs.UPDATE_TEAM_URL;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("TeamID", teamID);
            jsonObject.put("Team Name", teamName);
            jsonObject.put("Description", description);
            jsonObject.put("Private", isPrivate);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
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

    public void getAllTeams() {
        String url = URLs.GET_TEAMS_URL;
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

    public void getAllTeamMembers() {
        String url = URLs.GET_TEAM_MEMBERS_URL;
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

    public void getAllTeamProjects() {
        String url = URLs.GET_TEAM_PROJECTS_URL;
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

    public interface TeamByIdListener {
        void onSuccess(int teamID, String teamName, String description, boolean isPrivate);
        void onError(VolleyError error);
    }

    public void getTeamById(int teamID, TeamByIdListener listener) {
        String url = URLs.GET_ONE_TEAM_URL + teamID;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("TeamID");
                            String name = response.getString("Team Name");
                            String description = response.getString("Description");
                            boolean isPrivate = response.getBoolean("Private");

                            listener.onSuccess(id, name, description, isPrivate);
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