package com.projectaty.activities.projectmanagment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projectaty.R;

import java.util.Calendar;

public class CreateProject extends AppCompatActivity {

    EditText titleEditText;
    EditText descriptionEditText;
    EditText dateEditText;
    EditText memberEditText;
    FloatingActionButton floatingActionButton;
    Button pickDate;
    Button createTaskButton;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);
        initilaize();
    }
    private void initilaize(){

        setTitleEditText(findViewById(R.id.titleEditText));
        setDescriptionEditText(findViewById(R.id.descriptionEditText));
        setDateEditText(findViewById(R.id.dateEditText));
        setPickDate(findViewById(R.id.pickDate));
        setCreateTaskButton(findViewById(R.id.createPrjButton));
        setMemberEditText(findViewById(R.id.editTextNumber));
        setFloatingActionButton(findViewById(R.id.addBtn));
        handle_pick_date(getPickDate());

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
                    CreateProject.this,
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

    public EditText getTitleEditText() {
        return titleEditText;
    }

    public void setTitleEditText(EditText titleEditText) {
        this.titleEditText = titleEditText;
    }

    public EditText getDateEditText() {
        return dateEditText;
    }

    public void setDateEditText(EditText dateEditText) {
        this.dateEditText = dateEditText;
    }

    public EditText getDescriptionEditText() {
        return descriptionEditText;
    }
    public void setDescriptionEditText(EditText descriptionEditText) {
        this.descriptionEditText = descriptionEditText;
    }
    public EditText getMemberEditText() {
        return memberEditText;
    }

    public void setMemberEditText(EditText memberEditText) {
        this.memberEditText = memberEditText;
    }

    public FloatingActionButton getFloatingActionButton() {
        return floatingActionButton;
    }

    public void setFloatingActionButton(FloatingActionButton floatingActionButton) {
        this.floatingActionButton = floatingActionButton;
    }

    public Button getPickDate() {
        return pickDate;
    }

    public void setPickDate(Button pickDate) {
        this.pickDate = pickDate;
    }

    public Button getCreateTaskButton() {
        return createTaskButton;
    }

    public void setCreateTaskButton(Button createTaskButton) {
        this.createTaskButton = createTaskButton;
    }
}