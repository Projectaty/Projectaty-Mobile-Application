package com.projectaty.activities.taskmanagement;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class SearchTask extends AppCompatActivity {
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
        handle_search(getSearch());
    }

    private void handle_search(Button search) {
        search.setOnClickListener(e->{

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