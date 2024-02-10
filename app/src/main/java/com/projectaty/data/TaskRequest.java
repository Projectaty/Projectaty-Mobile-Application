package com.projectaty.data;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.projectaty.model.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void updateTaskByID(VolleySingleton volleySingleton, final TaskResponseCallback callback, int taskId, String jsonTask) throws JSONException {
        JSONObject taskJsonObject = new JSONObject(jsonTask);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URLs.PUT_TASK_BY_ID+taskId+"",
                taskJsonObject,
                response -> {
                    callback.onSuccess("updated");
                },
                error -> callback.onError(error.getMessage()));
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
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void getDone(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_DONE+ projectid, null,
                response -> {
                    List<Task> tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }
    public static void getTODO(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_TODO+ projectid, null,
                response -> {
                    List<Task> tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void getINProgress(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.GET_INPROGRESS+ projectid, null,
                response -> {
                    List<Task> tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }

    public static void findByKeyOrMonth(VolleySingleton volleySingleton, final TaskResponseCallback callback,  int projectid,String key, String month) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLs.FIND_BY_KEYorMONTH+ projectid+"/"+key+"/"+month , null,
                response -> {
                    List<Task> tasks = parseItems(response);
                    callback.onSuccess(tasks);
                },
                error -> callback.onError(error.getMessage()));
        volleySingleton.addToRequestQueue(jsonObjectRequest);
    }
    private static List<Task> parseItems(JSONObject response ) {

        List<Task> tasks = new ArrayList<>();
        try {
            JSONArray tasksArray = response.names();

            for (int i = 0; i < tasksArray.length(); i++) {
                JSONObject taskObject = tasksArray.getJSONObject(i);
                int ProjectID = taskObject.optInt("ProjectID",0);
                int TaskID = taskObject.optInt("TaskID",0);
                String title = taskObject.optString("Title", "");
                String Description = taskObject.optString("Description", "");
                String status = taskObject.optString("Status","");
                int assigneTo = taskObject.optInt("AssignedTo", 0);
                LocalDate date = LocalDate.parse(taskObject.optString("Date", ""));

                Task task = new Task(TaskID,ProjectID, title, Description, status, assigneTo,date );
                tasks.add(task);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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