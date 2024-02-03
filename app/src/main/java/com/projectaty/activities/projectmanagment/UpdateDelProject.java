package com.projectaty.activities.projectmanagment;

import static com.projectaty.R.id.titlePrjUpdate;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.activities.taskmanagement.UpdateDelTask;

import java.util.Calendar;

public class UpdateDelProject extends AppCompatActivity {
    EditText titleEditTextUpdate;
    EditText descriptionEditTextUpdate;
    EditText dateEditTextUpdate;
    EditText memberEditTextUpdate;
    Button uodateDate;
    Button updatePrjButton;
    Button delete;
    CheckBox isDone;

    EditText warning;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initialize();
        handle_delete(getDelete());
        handle_edit_date(getUodateDate());
        handle_update(getUpdatePrjButton());


    }
    private void initialize(){
        setContentView(R.layout.update_del_project);

        setTitleEditTextUpdate(findViewById(R.id.titlePrjUpdate));
        setDescriptionEditTextUpdate(findViewById(R.id.descriptionEditTextUpdate));
        setUodateDate(findViewById(R.id.uodateDate));
        setIsDone(findViewById(R.id.isDone));
        setDateEditTextUpdate(findViewById(R.id.dateEditTextUpdate));
        setMemberEditTextUpdate(findViewById(R.id.editTextNumberUpdate));
        setDelete(findViewById(R.id.delete));
    }

    private void handle_delete(Button delete) {
        delete.setOnClickListener(e->{

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
                    UpdateDelProject.this,
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
    private void handle_update(Button updateTaskButton) {
        updateTaskButton.setOnClickListener(e->{

        });
    }
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

    public EditText getDateEditTextUpdate() {
        return dateEditTextUpdate;
    }

    public void setDateEditTextUpdate(EditText dateEditTextUpdate) {
        this.dateEditTextUpdate = dateEditTextUpdate;
    }

    public EditText getMemberEditTextUpdate() {
        return memberEditTextUpdate;
    }

    public void setMemberEditTextUpdate(EditText memberEditTextUpdate) {
        this.memberEditTextUpdate = memberEditTextUpdate;
    }

    public Button getUodateDate() {
        return uodateDate;
    }

    public void setUodateDate(Button uodateDate) {
        this.uodateDate = uodateDate;
    }

    public Button getUpdatePrjButton() {
        return updatePrjButton;
    }

    public void setUpdatePrjButton(Button updatePrjButton) {
        this.updatePrjButton = updatePrjButton;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public CheckBox getIsDone() {
        return isDone;
    }

    public void setIsDone(CheckBox isDone) {
        this.isDone = isDone;
    }

    public EditText getWarning() {
        return warning;
    }

    public void setWarning(EditText warning) {
        this.warning = warning;
    }
}