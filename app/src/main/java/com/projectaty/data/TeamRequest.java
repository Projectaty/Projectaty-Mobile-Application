package com.projectaty.data;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.projectaty.model.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamRequest {

    public interface TeamResponseCallback {
        void onSuccess(Object respons);
        void onError(String error);
    }

    public static void addTeam(VolleySingleton volleySingleton, final TeamResponseCallback callback, String jsonTeam) throws JSONException {
        JSONObject teamJsonObject = new JSONObject(jsonTeam);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, URLs.ADD_TEAM_URL,
                teamJsonObject,
                response -> {
                    callback.onSuccess("added");
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void updateTeamByID(VolleySingleton volleySingleton, final TeamResponseCallback callback, int teamID, String jsonTeam) throws JSONException {
        JSONObject teamJsonObject = new JSONObject(jsonTeam);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URLs.UPDATE_TEAM_URL+teamID+"",
                teamJsonObject,
                response -> {
                    callback.onSuccess("updated");
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void getTeamByID(VolleySingleton volleySingleton, final TeamResponseCallback callback, int teamID) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_ONE_TEAM_URL+teamID, null,
                response -> {
                    Team team = parseTeam(response);
                    callback.onSuccess(team);
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void deleteTeamByID(VolleySingleton volleySingleton, final TeamResponseCallback callback,  int teamID) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, URLs.DELETE_TEAM_URL+ teamID, null,
                response -> {
                    callback.onSuccess("deleted");
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

//    public static void getIsPrivate(VolleySingleton volleySingleton, final TeamResponseCallback callback, int teamID) {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_IS_PRIVATE_URL+ teamID, null,
//                response -> {
//                    List<Team> teams = parseItems(response);
//                    callback.onSuccess(teams);
//                },
//                error -> callback.onError(error.getMessage()));
//        volleySingleton.addToRequestQueue(jsonObjectRequest);
//    }
//    public static void getTODO(VolleySingleton volleySingleton, final TeamResponseCallback callback,  int projectid) {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_TODO+ projectid, null,
//                response -> {
//                    List<Team> teams = parseItems(response);
//                    callback.onSuccess(teams);
//                },
//                error -> callback.onError(error.getMessage()));
//        volleySingleton.addToRequestQueue(jsonObjectRequest);
//    }

//    public static void getINProgress(VolleySingleton volleySingleton, final TeamResponseCallback callback,  int projectid) {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_INPROGRESS+ projectid, null,
//                response -> {
//                    List<Team> teams = parseItems(response);
//                    callback.onSuccess(teams);
//                },
//                error -> callback.onError(error.getMessage()));
//        volleySingleton.addToRequestQueue(jsonObjectRequest);
//    }

    public static void findByIDOrName(VolleySingleton volleySingleton, final TeamResponseCallback callback,  int teamID,String IDKey, String teamName, String nameKey) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.FIND_TEAM_BY_ID_OR_NAME_URL+ teamID+"/"+IDKey+"/"+teamName+"/"+nameKey+"/" , null,
                response -> {
                    List<Team> teams = parseItems(response);
                    callback.onSuccess(teams);
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    private static List<Team> parseItems(JSONObject response ) {

        List<Team> teams = new ArrayList<>();
        try {
            JSONArray teamsArray = response.names();

            for (int i = 0; i < teamsArray.length(); i++) {
                JSONObject teamObject = teamsArray.getJSONObject(i);
                int TeamID = teamObject.optInt("TeamID",0);
                String TeamName = teamObject.optString("TeamName", "");
                String Description = teamObject.optString("Description", "");
                String isPrivate = teamObject.optString("Private","");

                Team team = new Team(TeamID, TeamName, Description, isPrivate );
                teams.add(team);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Team parseTeam(JSONObject teamObject) {
        int TeamID = teamObject.optInt("TeamID",0);
        String teamName = teamObject.optString("TeamName", "");
        String Description = teamObject.optString("Description", "");
        String isPrivate = teamObject.optString("Private","");
        return new Team(TeamID,teamName, Description, isPrivate );
    }


    /**
     *
     *     public void deleteTeamMember(int studentID) {
     *         String url = URLs.DELETE_TEAM_MEMBER_URL + studentID;
     *         StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
     *                 new Response.Listener<String>() {
     *                     @Override
     *                     public void onResponse(String response) {
     *                         Log.d(TAG, "Response: " + response);
     *                     }
     *                 },
     *                 new Response.ErrorListener() {
     *                     @Override
     *                     public void onErrorResponse(VolleyError error) {
     *                         Log.e(TAG, "Error: " + error.toString());
     *                     }
     *                 });
     *
     *         requestQueue.add(stringRequest);
     *     }
     *
     */

    /**
     *
     public void deleteTeamProject(int projectID) {
     String url = URLs.DELETE_TEAM_PROJECT_URL + projectID;
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
     *
     */


//    public void addTeamMember(int studentID, int teamID) {
//        String url = URLs.ADD_TEAM_MEMBER_URL;
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("StudentID", studentID);
//            jsonObject.put("TeamID", teamID);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error: " + error.toString());
//                    }
//                });
//
//        requestQueue.add(jsonObjectRequest);
//    }

//    public void addTeamProject(int projectID, int teamID) {
//        String url = URLs.ADD_TEAM_PROJECT_URL;
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("ProjectID", projectID);
//            jsonObject.put("TeamID", teamID);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error: " + error.toString());
//                    }
//                });
//
//        requestQueue.add(jsonObjectRequest);
//    }






//    public void getAllTeams() {
//        String url = URLs.GET_TEAMS_URL;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error: " + error.toString());
//                    }
//                });
//
//        requestQueue.add(jsonObjectRequest);
//    }

//    public void getAllTeamMembers() {
//        String url = URLs.GET_TEAM_MEMBERS_URL;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error: " + error.toString());
//                    }
//                });
//
//        requestQueue.add(jsonObjectRequest);
//    }



//    public void getAllTeamProjects() {
//        String url = URLs.GET_TEAM_PROJECTS_URL;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error: " + error.toString());
//                    }
//                });
//
//        requestQueue.add(jsonObjectRequest);
//    }

}