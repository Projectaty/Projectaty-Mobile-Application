package com.projectaty.activities.teamsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.projectaty.R;

public class SearchMember extends AppCompatActivity {

    private EditText memberKey;
    private EditText memberIDKey;
    private Button searchMemberBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_team);
        initialize();
    }

    private void initialize() {
        setMemberKey(findViewById(R.id.memberKey));
        setMemberIDKey(findViewById(R.id.memberIDKey));
        setSearchMemberBtn(findViewById(R.id.searchMemberBtn));
        handle_searchMember(getSearchMemberBtn());
    }

    private void handle_searchMember(Button searchMemberBtn) {
        searchMemberBtn.setOnClickListener(e -> {

        });
    }

    /*
    Getter and Setters
     */
    public EditText getMemberIDKey() {
        return memberIDKey;
    }

    public void setMemberIDKey(EditText memberIDKey) {
        this.memberIDKey = memberIDKey;
    }

    public EditText getMemberKey() {
        return memberKey;
    }

    public void setMemberKey(EditText memberKey) {
        this.memberKey = memberKey;
    }

    public Button getSearchMemberBtn() {
        return searchMemberBtn;
    }

    public void setSearchMemberBtn(Button searchMemberBtn) {
        this.searchMemberBtn = searchMemberBtn;
    }
}