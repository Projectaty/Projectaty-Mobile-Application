package com.projectaty.activities.teamsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.projectaty.R;
import com.projectaty.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MembersList extends AppCompatActivity {

    private MemberIDAdapter memberListAdapter;
    private Button findMember;
    private Button goToDash;
    private ListView memberListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.members_list);
        initialize();
    }

    private void initialize() {
        setMemberListView(findViewById(R.id.membersListView));

        List<Team> members = new ArrayList<>();

//        MemberIDAdapter memberListAdapter = new MemberIDAdapter();
//        getMemberListView().setAdapter(memberListAdapter);

        setFindmember(findViewById(R.id.findMember));
        setGoToDash(findViewById(R.id.goToDash));
        handle_goToDash(getGoToDash());
        hadnle_findMember(getFindmember());
    }

    /*
    Buttons Handlers
     */
    private void hadnle_findMember(Button findMember) {
        findMember.setOnClickListener(e -> {
            Intent intent = new Intent(this, SearchMember.class);
            startActivity(intent);
        });
    }

    private void handle_goToDash(Button goToDash) {
        goToDash.setOnClickListener(e -> {
            Intent intent = new Intent(this, TeamDashboard.class);
            startActivity(intent);
        });
    }

    private void handlememberListView() {

    }

     /*
    Getters & setters
     */

    public Button getFindmember() {
        return findMember;
    }

    public void setFindmember(Button findMember) {
        this.findMember = findMember;
    }

    public Button getGoToDash() {
        return goToDash;
    }

    public void setGoToDash(Button goToDash) {
        this.goToDash = goToDash;
    }

    public ListView getMemberListView() {
        return memberListView;
    }

    public void setMemberListView(ListView memberListView) {
        this.memberListView = memberListView;
    }
}