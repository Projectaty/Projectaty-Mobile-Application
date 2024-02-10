package com.projectaty.activities.taskmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class SearchTask extends AppCompatActivity {
    /*
        In this activity I let the user chose a keyword and search
        or by a specific month
     */
    EditText keyword;
    Spinner monthSpinner;
    Button search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seatch_task);
        initialize();
    }

    private void initialize() {
        setKeyword(findViewById(R.id.keyword));
        setMonthSpinner(findViewById(R.id.monthSpinner));
        setSearch(findViewById(R.id.search));

        int projectID = getIntent().getIntExtra("projectID",0);
        String status = getIntent().getStringExtra("status");
        handle_search(getSearch(), projectID, status);
    }

    private void handle_search(Button search, int projectID, String status) {
        search.setOnClickListener(e->{
            /*
                Get the month value,
                Get the keyword value
                make a search request for a specifi projID and status
                SELECT * FROM task where projectID = %s AND status  = %s
                --> AND date = %s
             */
            String keyword = getKeyword().getText().toString();
            String month = getMonthSpinner().getSelectedItem().toString();

            Intent intent = new Intent(this, TaskList.class);
            intent.putExtra("projectID",projectID );
            intent.putExtra("status",status );
            intent.putExtra("isSearch",true);
            intent.putExtra("keyword",keyword);
            intent.putExtra("month",month );
            startActivity(intent);
        });
    }

    /*
    Getter and Setters
     */
    public EditText getKeyword() {
        return keyword;
    }
    public void setKeyword(EditText keyword) {
        this.keyword = keyword;
    }
    public Spinner getMonthSpinner() {
        return monthSpinner;
    }
    public void setMonthSpinner(Spinner monthSpinner) {
        this.monthSpinner = monthSpinner;
    }
    public Button getSearch() {
        return search;
    }
    public void setSearch(Button search) {
        this.search = search;
    }
}