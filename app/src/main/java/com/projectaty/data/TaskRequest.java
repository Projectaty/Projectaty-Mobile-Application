package com.projectaty.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.projectaty.model.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskRequest {
    /*
     In this class I make diffrent requests using Volley and the URls prepeared
     */
    public interface TaskResponseCallback {
        void onSuccess(Object respons); // and then I will cast it
        void onError(String error);
    }

    /*
    Make a request on the URL return the response
    - and make the parsing logic
     */
    public static void addTask(VolleySingleton volleySingleton, final TaskResponseCallback callback, String jsonTask) throws JSONException {
        JSONObject taskJsonObject = new JSONObject(jsonTask);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, URLs.POST_TASK,
                taskJsonObject,
                response -> {
                    callback.onSuccess("added");
                },
                error -> callback.onError(error.toString()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void updateTaskByID(VolleySingleton volleySingleton, final TaskResponseCallback callback, int taskId, String jsonTask) throws JSONException {
        JSONObject taskJsonObject = new JSONObject(jsonTask);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URLs.PUT_TASK_BY_ID+taskId+"",
                taskJsonObject,
                response -> {
                    callback.onSuccess("updated");
                },
                error -> callback.onError(error.toString()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void getTaskByID(VolleySingleton volleySingleton, final TaskResponseCallback callback, int id) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_TASKS_BY_ID+id, null,
                response -> {
                    Task task = parseTask(response);
                    callback.onSuccess(task);
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void deleteTaskByID(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int id) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, URLs.DELETE_TASK_BY_ID+ id, null,
                response -> {
                    callback.onSuccess("deleted");
                },
                error -> callback.onError(error.toString()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void getDone(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_DONE+ projectid, null,
                response -> {
                    List<Task> tasks = null;
                    tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.toString()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }
    public static void getTODO(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_TODO+ (projectid+1), null,
                response -> {
                    List<Task> tasks = null;
                    tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.toString()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void getINProgress(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_INPROGRESS+ projectid, null,
                response -> {
                    List<Task> tasks = null;
                    tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.toString()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void findByKeyOrMonth(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid,String key, String month) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.FIND_BY_KEYorMONTH+ projectid+"/"+key+"/"+month , null,
                response -> {
                    List<Task> tasks = null;
                    tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.toString()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }
    private static List<Task> parseItems(JSONObject response) {
        List<Task> tasks = new ArrayList<>();
        try {
            if (response.has("tasks")) {
                JSONArray array = response.getJSONArray("tasks");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject taskObject = array.getJSONObject(i);
                    int ProjectID = taskObject.optInt("ProjectID", 0);
                    int TaskID = taskObject.optInt("TaskID", 0);
                    String title = taskObject.optString("Title", "");
                    String description = taskObject.optString("Description", "");
                    String status = taskObject.optString("Status", "");
                    int assignedTo = taskObject.optInt("AssignedTo", 0);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
                    LocalDate date = LocalDate.parse(taskObject.optString("Deadline", ""), formatter);
                    Task task = new Task(TaskID, ProjectID, title, description, status, assignedTo, date);
                    Log.d("task", task.toString());
                    tasks.add(task);
                }
            } else {
                Log.e("parseItems", "Key 'donetasks' not found in the JSON response");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private static Task parseTask(JSONObject taskObject) {
        int ProjectID = taskObject.optInt("ProjectID",0);
        int TaskID = taskObject.optInt("TaskID",0);
        String title = taskObject.optString("Title", "");
        String Description = taskObject.optString("Description", "");
        String status = taskObject.optString("Status","");
        int assigneTo = taskObject.optInt("AssignedTo", 0);
        LocalDate date = LocalDate.parse(taskObject.optString("Date", ""));
       return new Task(TaskID,ProjectID, title, Description, status, assigneTo,date );
    }
}