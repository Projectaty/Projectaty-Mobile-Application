package com.projectaty.activities.taskmanagement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

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
        handle_update(getUpdateTaskButton(), taskidint);
        handle_delete(getDelete(), taskidint);
        handle_edit_date(getUodateDate());
    }

    private void setOldValues(int taskidint) {
        /* Make volley request and fill the data into the textfeilds */

    }


    /*
    Buttons Handlers
     */
    private void handle_delete(Button delete, int taskId) {
        delete.setOnClickListener(e->{
            /*
                Make a voelly Request to delet by ID
             */

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

    private void handle_update(Button updateTaskButton, int taskidint) {
        updateTaskButton.setOnClickListener(e->{
            /*
                Make  a volley request to update the data
             */
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