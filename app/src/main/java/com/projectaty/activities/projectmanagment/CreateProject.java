package com.projectaty.activities.projectmanagment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projectaty.R;

import java.time.LocalDate;
import java.util.Calendar;

import com.projectaty.data.ProjectRequest;


public class CreateProject extends AppCompatActivity {

    EditText titleEditText;
    EditText descriptionEditText;
    EditText dateEditText;
    EditText PrivacyEditText;
    FloatingActionButton floatingActionButton;
    Button pickDate;
    Button createTaskButton;


    private RequestQueue queue;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);
        initilaize();

        queue = Volley.newRequestQueue(this);
    }

    private void initilaize() {

        setTitleEditText(findViewById(R.id.titleEditText));
        setDescriptionEditText(findViewById(R.id.descriptionEditText));
        setDateEditText(findViewById(R.id.dateEditText));
        setPickDate(findViewById(R.id.pickDate));
        setCreateTaskButton(findViewById(R.id.createPrjButton));
        setPrivacyEditText(findViewById(R.id.PrivacyEditTextUpdate));
        setFloatingActionButton(findViewById(R.id.addBtn));


        handle_pick_date(getPickDate());

    }

    private void handle_pick_date(Button pickDate) {
        pickDate.setOnClickListener(e -> {
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
                        String datestr = dayOfMonth + "-" + (month1 + 1) + "-" + year1;
                        getDateEditText().setText(datestr);
                    }
                    , year, month, day);
            datePickerDialog.show();
        });
    }

    public void AddProjectOnClick(View view) {
        String Title = titleEditText.getText().toString();
        String Description = descriptionEditText.getText().toString();

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

        boolean PrivacyText = Boolean.parseBoolean(PrivacyEditText.getText().toString());

        if (!Title.isEmpty() && !Description.isEmpty() && !dateStr.isEmpty()) {
            ProjectRequest projectRequest = new ProjectRequest(this);
            projectRequest.addProjects( Title, Description, Integer.parseInt(dateStr), PrivacyText);
        } else {
            Toast.makeText(getApplicationContext(), "Fill all required fields!", Toast.LENGTH_SHORT).show();
        }

    }

   /* private void addProjects(String Title, String Description, String Deadline, String Privacy) {

        String url = "";

        RequestQueue queue = Volley.newRequestQueue(CreateProject.this);

        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("Title", Title);
            jsonParams.put("Description", Description);
            jsonParams.put("Deadline", Deadline);
            jsonParams.put("Privacy", Privacy);


        } catch (JSONException e) {
            e.printStackTrace();

        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String str = "";
                        try {
                            str = response.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(CreateProject.this, str,
                                Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", error.toString());
                    }
                }
        );
        // below line is to make
        // a json object request.
        queue.add(request);
    }*/

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
    public EditText getPrivacyEditText() {
        return PrivacyEditText;
    }

    public void setPrivacyEditText(EditText privacyEditText) {
        this.PrivacyEditText = privacyEditText;
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