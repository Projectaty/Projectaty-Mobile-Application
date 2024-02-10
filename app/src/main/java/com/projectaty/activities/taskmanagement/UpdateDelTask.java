package com.projectaty.activities.taskmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.projectaty.R;
import com.projectaty.data.TaskRequest;
import com.projectaty.data.VolleySingleton;
import com.projectaty.model.Task;

import org.json.JSONException;

import java.time.LocalDate;
import java.util.Calendar;

public class UpdateDelTask extends AppCompatActivity {
    EditText titleEditTextUpdate;
    EditText descriptionEditTextUpdate;
    Button uodateDate;
    CheckBox isDone;
    EditText dateEditTextUpdate;
    Spinner assignSpinnerUpdate;
    Button updateTaskButton;
    Button delete;
    EditText warning;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_del_task);
        initialize();
    }

    private void initialize(){
        String taskID = getIntent().getStringExtra("taskID");
        int taskidint = Integer.parseInt(taskID);

        setTitleEditTextUpdate(findViewById(R.id.titleEditTextUpdate));
        setDescriptionEditTextUpdate(findViewById(R.id.descriptionEditTextUpdate));
        setUodateDate(findViewById(R.id.uodateDate));
        setIsDone(findViewById(R.id.isDone));
        setDateEditTextUpdate(findViewById(R.id.dateEditTextUpdate));
        setAssignSpinnerUpdate(findViewById(R.id.assignSpinnerUpdate));
        setUpdateTaskButton(findViewById(R.id.updateTaskButton));
        setDelete(findViewById(R.id.delete));

        setOldValues(taskidint);

        /*
            Handlers
         */
        int projectID  =getIntent().getIntExtra("projectID", 0);
        String status = getIntent().getStringExtra("status");
        handle_update(getUpdateTaskButton(), taskidint, status, projectID);
        handle_delete(getDelete(), taskidint, status, projectID);
        handle_edit_date(getUodateDate());
    }

    private void setOldValues(int taskidint) {
        /* Make volley request and fill the data into the textfeilds */
        TaskRequest.getTaskByID(
                VolleySingleton.getInstance(this),
                new TaskRequest.TaskResponseCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        Task task = (Task) response;
                        getTitleEditTextUpdate().setText(task.getTitle());
                        getDescriptionEditTextUpdate().setText(task.getDescription());
                        getDateEditTextUpdate().setText(task.getDate()+"");
                        // TODO set the selected item
//                        getAssignSpinnerUpdate(). task.getAssignedTo()+""
                        // Status **
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.d("error", errorMessage);
                    }
                }
                , taskidint
        );
    }


    /*
    Buttons Handlers
     */
    private void handle_delete(Button delete, int taskId, String status, int projectID) {
        delete.setOnClickListener(e->{
            /*
                Make a voelly Request to delet by ID
             */
            TaskRequest.deleteTaskByID(VolleySingleton.getInstance(this),
                    new TaskRequest.TaskResponseCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            String res = (String) response;
                            if (res.equals("deleted")) {
                                Toast.makeText(UpdateDelTask.this, "Task deleted successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateDelTask.this, TaskList.class);
                                intent.putExtra("projectID", projectID);
                                intent.putExtra("status", status);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Log.d("error", errorMessage);
                        }
                    }
                    , taskId);
        });
    }

    private void handle_edit_date(Button uodateDate) {
        uodateDate.setOnClickListener(e->{
            /*
                https://www.geeksforgeeks.org/datepicker-in-android/
             */
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    UpdateDelTask.this,
                    /*
                        replaced by lamda suggested by android studio
                     */
                    (view1, year1, month1, dayOfMonth) -> {
                        String datestr = dayOfMonth + "-" + (month1 + 1) + "-" + year1 ;
                        getDateEditTextUpdate().setText(datestr);
                    }
                    ,year, month, day);
            datePickerDialog.show();
        });
    }

    private void handle_update(Button updateTaskButton, int taskidint, String status, int projectID) {
        updateTaskButton.setOnClickListener(e-> {
            String title = getTitleEditTextUpdate().getText().toString().trim();
            String description = getDescriptionEditTextUpdate().getText().toString().trim();

            /*
            Handling the date as a string
             */
            String dateStr = getDateEditTextUpdate().getText().toString().trim();
            int year;
            int month;
            int day;
            LocalDate date;

            if (!dateStr.isEmpty()) {
                String[] dateElements = dateStr.split("-");
                year = Integer.parseInt(dateElements[2]);
                month = Integer.parseInt(dateElements[1]);
                day = Integer.parseInt(dateElements[0]);
                date = LocalDate.of(year, month, day);
            } else {
                date = null;
            }

            String assignee = getAssignSpinnerUpdate().getSelectedItem().toString().trim();

            if (!title.isEmpty()) { /* Make  a volley request to update the data */
                Task newTask = new Task(projectID, title, description, status, Integer.parseInt(assignee), date);
                try {
                    TaskRequest.updateTaskByID(VolleySingleton.getInstance(this),
                            new TaskRequest.TaskResponseCallback() {
                                @Override
                                public void onSuccess(Object response) {
                                    String res = (String) response;
                                    if (res.equals("updated")) {
                                        Toast.makeText(UpdateDelTask.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UpdateDelTask.this, TaskList.class);
                                        intent.putExtra("projectID", 0);
                                        intent.putExtra("status", status);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onError(String errorMessage) {
                                    Log.d("error", errorMessage);
                                }
                            }
                            , taskidint, new Gson().toJson(newTask));
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                setWarning(findViewById(R.id.warningC));
                // At least the title should be added
                getWarning().setVisibility(View.VISIBLE);
            }
        });
    }
    /*
        Getters & setters
     */
    public EditText getTitleEditTextUpdate() {
        return titleEditTextUpdate;
    }
    public void setTitleEditTextUpdate(EditText titleEditTextUpdate) {
        this.titleEditTextUpdate = titleEditTextUpdate;
    }
    public EditText getDescriptionEditTextUpdate() {
        return descriptionEditTextUpdate;
    }
    public void setDescriptionEditTextUpdate(EditText descriptionEditTextUpdate) {
        this.descriptionEditTextUpdate = descriptionEditTextUpdate;
    }
    public Button getUodateDate() {
        return uodateDate;
    }
    public void setUodateDate(Button uodateDate) {
        this.uodateDate = uodateDate;
    }
    public CheckBox getIsDone() {
        return isDone;
    }
    public void setIsDone(CheckBox isDone) {
        this.isDone = isDone;
    }
    public EditText getDateEditTextUpdate() {
        return dateEditTextUpdate;
    }
    public void setDateEditTextUpdate(EditText dateEditTextUpdate) {
        this.dateEditTextUpdate = dateEditTextUpdate;
    }
    public Spinner getAssignSpinnerUpdate() {
        return assignSpinnerUpdate;
    }
    public void setAssignSpinnerUpdate(Spinner assignSpinnerUpdate) {
        this.assignSpinnerUpdate = assignSpinnerUpdate;
    }
    public Button getUpdateTaskButton() {
        return updateTaskButton;
    }
    public void setUpdateTaskButton(Button updateTaskButton) {
        this.updateTaskButton = updateTaskButton;
    }
    public Button getDelete() {
        return delete;
    }
    public void setDelete(Button delete) {
        this.delete = delete;
    }
    public EditText getWarning() {
        return warning;
    }
    public void setWarning(EditText warning) {
        this.warning = warning;
    }
}