package com.projectaty.activities.taskmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.model.Task;

import java.time.LocalDate;
import java.util.Calendar;

public class CreateTask extends AppCompatActivity {
    EditText titleEditText;
    EditText descriptionEditText;
    Button pickDate;
    EditText dateEditText;
    Spinner asssignSpinner;
    Button createTaskButton;
    EditText warning;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);
        initilaize();
    }

    private void initilaize() {
        setTitleEditText(findViewById(R.id.titleEditText));
        setDescriptionEditText(findViewById(R.id.descriptionEditText));
        setPickDate(findViewById(R.id.pickDate));
        setDateEditText(findViewById(R.id.dateEditText));
        setAsssignSpinner(findViewById(R.id.asssignSpinner));
        setCreateTaskButton(findViewById(R.id.createTaskButton));
        setWarning(findViewById(R.id.warning));

        handle_pick_date(getPickDate());
        handle_create_task(getCreateTaskButton());
    }
    /*
    Buttons Handlers
     */
    private void handle_create_task(Button createTaskButton) {
        createTaskButton.setOnClickListener(e->{
            String title = getTitleEditText().getText().toString().trim();
            String description = getDescriptionEditText().getText().toString().trim();

            /*
            Handling the date as a string
             */
            String dateStr = getDateEditText().getText().toString().trim();
            int year;
            int month;
            int day;
            LocalDate date;

            if(!dateStr.isEmpty()){
                String[] dateElements = dateStr.split("-");
                year= Integer.parseInt(dateElements[2]);
                month = Integer.parseInt(dateElements[1]);
                day = Integer.parseInt(dateElements[0]);
                date = LocalDate.of(year, month, day);
            }else{
                date = null;
            }

            String assignee = getAsssignSpinner().getSelectedItem().toString().trim();

            if(!title.isEmpty()){
//                Task newTask = new Task(title, description, assignee, date, false);
//
//                Intent intent1 = new Intent(this, StatusInform.class);
//                intent1.putExtra("status", "added");
//                startActivity(intent1);
            }else{
                // At least the title should be added
                getWarning().setVisibility(View.VISIBLE);
            }
        });
    }

    private void handle_pick_date(Button pickDate) {
        pickDate.setOnClickListener(e->{
            /*
                https://www.geeksforgeeks.org/datepicker-in-android/
             */
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    CreateTask.this,
                    /*
                        replaced by lamda suggested by android studio
                     */
                    (view1, year1, month1, dayOfMonth) -> {
                        String datestr = dayOfMonth + "-" + (month1 + 1) + "-" + year1 ;
                        getDateEditText().setText(datestr);
                    }
                    ,year, month, day);
            datePickerDialog.show();
        });
    }

    /*
        Getters & setters
     */
    public EditText getTitleEditText() {
        return titleEditText;
    }
    public void setTitleEditText(EditText titleEditText) {
        this.titleEditText = titleEditText;
    }
    public EditText getDescriptionEditText() {
        return descriptionEditText;
    }
    public void setDescriptionEditText(EditText descriptionEditText) {
        this.descriptionEditText = descriptionEditText;
    }
    public Button getPickDate() {
        return pickDate;
    }
    public void setPickDate(Button pickDate) {
        this.pickDate = pickDate;
    }
    public EditText getDateEditText() {
        return dateEditText;
    }
    public void setDateEditText(EditText dateEditText) {
        this.dateEditText = dateEditText;
    }
    public Spinner getAsssignSpinner() {
        return asssignSpinner;
    }
    public void setAsssignSpinner(Spinner asssignSpinner) {
        this.asssignSpinner = asssignSpinner;
    }
    public Button getCreateTaskButton() {
        return createTaskButton;
    }
    public void setCreateTaskButton(Button createTaskButton) {
        this.createTaskButton = createTaskButton;
    }
    public EditText getWarning() {
        return warning;
    }
    public void setWarning(EditText warning) {
        this.warning = warning;
    }
}