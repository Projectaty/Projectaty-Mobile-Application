package com.projectaty.activities.teamsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.projectaty.R;

public class SearchTeam extends AppCompatActivity {

    private EditText teamKey;
    private EditText teamIDKey;
    private Button searchTeamBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_team);
        initialize();
    }

    private void initialize() {
        setTeamKey(findViewById(R.id.teamKey));
        setTeamIDKey(findViewById(R.id.teamIDKey));
        setSearchTeamBtn(findViewById(R.id.searchTeamBtn));
        handle_search(getSearchTeamBtn());
    }

    private void handle_search(Button search) {
        search.setOnClickListener(e -> {

        });
    }

    /*
    Getter and Setters
     */
    public EditText getTeamIDKey() {
        return teamIDKey;
    }

    public void setTeamIDKey(EditText teamIDKey) {
        this.teamIDKey = teamIDKey;
    }

    public EditText getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(EditText teamKey) {
        this.teamKey = teamKey;
    }

    public Button getSearchTeamBtn() {
        return searchTeamBtn;
    }

    public void setSearchTeamBtn(Button searchTeamBtn) {
        this.searchTeamBtn = searchTeamBtn;
    }
}