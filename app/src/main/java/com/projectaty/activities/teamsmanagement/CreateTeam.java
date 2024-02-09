package com.projectaty.activities.teamsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projectaty.R;
import com.projectaty.model.MemberIDAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateTeam extends AppCompatActivity {

    private int teamIDCounter = 1;
    private EditText teamNameEdtTxt;
    private EditText memberIDEdtTxt;
    private EditText projectNameEdtTxt;
    private EditText descriptionEdtTxt;
    private CheckBox isPrivateTeam;
    private Button createTeam;
    private Button discardTeam;
    private Button addMembersBtn;
    private RecyclerView recyclerViewMemberIDs;
    private MemberIDAdapter memberIDAdapter;
    private List<String> memberIDsList;
    private EditText warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_team);
        initilaize();
    }

    private void initilaize() {
        teamNameEdtTxt = findViewById(R.id.teamNameEdtTxt);
        memberIDEdtTxt = findViewById(R.id.memberIDEdtTxt);
        projectNameEdtTxt = findViewById(R.id.projectNameEdtTxt);
        descriptionEdtTxt = findViewById(R.id.descriptionEdtTxt);
        isPrivateTeam = findViewById(R.id.isPrivateTeam);
        createTeam = findViewById(R.id.createTeam);
        discardTeam = findViewById(R.id.discardTeam);
        addMembersBtn = findViewById(R.id.addMembersBtn);
        recyclerViewMemberIDs = findViewById(R.id.recyclerViewMemberIDs);
        recyclerViewMemberIDs.setLayoutManager(new LinearLayoutManager(this));

        memberIDsList = new ArrayList<>();
        memberIDAdapter = new MemberIDAdapter(memberIDsList);
        recyclerViewMemberIDs.setAdapter(memberIDAdapter);

        handle_create_team(getCreateTeam());
        handleAddIDs(getAddMembersBtn());
        handleDiscardTeam(getDiscardTeam());
    }

    private void handle_create_team(Button createTeamButton) {
        createTeamButton.setOnClickListener(e -> {
            String teamName = getTeamNameEdtTxt().getText().toString().trim();
            String description = getDescriptionEdtTxt().getText().toString().trim();
            String projectName = getProjectNameEdtTxt().getText().toString().trim();


            if (!teamName.isEmpty()) {
                if (isMemberIDsListEmpty()) {
                    setWarning(findViewById(R.id.warning));
                    getWarning().setVisibility(View.VISIBLE);
                    showToast("Cannot create a team when there are no existing members.");
                    return;
                }

                int teamID = generateTeamID();


                Intent intent = new Intent(CreateTeam.this, TeamList.class);
                startActivity(intent);
                finish();
            } else {
                setWarning(findViewById(R.id.warning));
                getWarning().setVisibility(View.VISIBLE);
                showToast("Please fill in all fields.");
            }
        });
    }

    private void handleAddIDs(Button addMembersBtn) {

        addMembersBtn.setOnClickListener(e -> {
            String memberID = getMemberIDEdtTxt().getText().toString().trim();

            if (!memberID.isEmpty()) {

                int id = Integer.parseInt(memberID);
                if (!memberIDsList.contains(memberID)) {
                    memberIDsList.add(memberID);
                    memberIDAdapter.notifyDataSetChanged();
                    memberIDEdtTxt.setText("");
                } else {
                    showToast("This member ID is already added.");
                }

            } else {
                showToast("Invalid input. Please enter a member ID.");
            }
        });
    }


    private void handleDiscardTeam(Button discardTeam) {
        discardTeam.setOnClickListener(e -> {
            getTeamNameEdtTxt().setText("");
            getMemberIDEdtTxt().setText("");
            getDescriptionEdtTxt().setText("");
            getProjectNameEdtTxt().setText("");

            memberIDsList.clear();
            memberIDAdapter.notifyDataSetChanged();

            Intent intent = new Intent(CreateTeam.this, TeamList.class);
            startActivity(intent);
            finish();
        });
    }


    private boolean isMemberIDsListEmpty() {
        return memberIDsList.isEmpty();
    }

    private int generateTeamID() {
        int teamID = teamIDCounter;
        teamIDCounter++;
        return teamID;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*
        Getters & setters
     */
    public EditText getTeamNameEdtTxt() {
        return teamNameEdtTxt;
    }

    public void setTeamNameEdtTxt(EditText teamNameEdtTxt) {
        this.teamNameEdtTxt = teamNameEdtTxt;
    }

    public EditText getDescriptionEdtTxt() {
        return descriptionEdtTxt;
    }

    public void setDescriptionEdtTxt(EditText descriptionEdtTxt) {
        this.descriptionEdtTxt = descriptionEdtTxt;
    }

    public EditText getProjectNameEdtTxt() {
        return projectNameEdtTxt;
    }

    public void setProjectNameEdtTxt(EditText projectNameEdtTxt) {
        this.projectNameEdtTxt = projectNameEdtTxt;
    }

    public EditText getMemberIDEdtTxt() {
        return memberIDEdtTxt;
    }

    public void setMemberIDEdtTxt(EditText memberIDEdtTxt) {
        this.memberIDEdtTxt = memberIDEdtTxt;
    }

    public Button getCreateTeam() {
        return createTeam;
    }

    public void setCreateTeam(Button createTeam) {
        this.createTeam = createTeam;
    }

    public Button getAddMembersBtn() {
        return addMembersBtn;
    }

    public void setAddMembersBtn(Button addMembersBtn) {
        this.addMembersBtn = addMembersBtn;
    }

    public Button getDiscardTeam() {
        return discardTeam;
    }

    public void setDiscardTeam(Button discardTeam) {
        this.discardTeam = discardTeam;
    }

    public RecyclerView getRecyclerViewMemberIDs() {
        return recyclerViewMemberIDs;
    }

    public void setRecyclerViewMemberIDs(RecyclerView recyclerViewMemberIDs) {
        this.recyclerViewMemberIDs = recyclerViewMemberIDs;
    }

    public EditText getWarning() {
        return warning;
    }

    public void setWarning(EditText warning) {
        this.warning = warning;
    }

    public CheckBox getIsPrivateTeam() {
        return isPrivateTeam;
    }

    public void setIsPrivateTeam(CheckBox isPrivateTeam) {
        this.isPrivateTeam = isPrivateTeam;
    }

}
