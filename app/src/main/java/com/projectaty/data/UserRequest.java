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

public class UserRequest {
    private static final String TAG = UserRequest.class.getSimpleName();
    private RequestQueue requestQueue;
    private Context context;

    public UserRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void addStudent(int studentId, String username, String password, String email, String profilePic) {
        String url = URLs.ADD_USER_URL;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("StudentID", studentId);
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            jsonObject.put("profile_pic", profilePic);
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

    public void deleteStudent(int studentId) {
        String url = URLs.DELETE_USER_URL + studentId;
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

    public void updateStudent(int studentId, String username, String password, String email, String profilePic) {
        String url = URLs.UPDATE_USER_URL;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("StudentID", studentId);
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            jsonObject.put("profile_pic", profilePic);
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

    public void getAllStudents() {
        String url = URLs.GET_USERS_URL;
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

    public interface StudentByIdListener {
        void onSuccess(int studentId, String username, String password, String email, String profilePic);
        void onError(VolleyError error);
    }

    public void getStudentById(int studentId, StudentByIdListener listener) {
        String url = URLs.GET_ONE_USER_URL + studentId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("StudentID");
                            String username = response.getString("username");
                            String password = response.getString("password");
                            String email = response.getString("email");
                            String profilePic = response.getString("profile_pic");

                            listener.onSuccess(id, username, password, email, profilePic);
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

    public interface StudentByNameAndPasswordListener {
        void onSuccess(int studentId, String username, String password, String email, String profilePic);
        void onError(VolleyError error);
    }


    public void getStudentByNameAndPassword(String username, String password, StudentByNameAndPasswordListener listener) {
        String url = URLs.GET_USER_BY_NAME_AND_PASSWORD_URL;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("StudentID");
                            String name = response.getString("username");
                            String password = response.getString("password");
                            String email = response.getString("email");
                            String profilePic = response.getString("profile_pic");

                            listener.onSuccess(id, name, password, email, profilePic);
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
