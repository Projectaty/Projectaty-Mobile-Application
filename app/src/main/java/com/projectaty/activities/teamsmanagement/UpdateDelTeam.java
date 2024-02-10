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

public class UpdateDelTeam extends AppCompatActivity {

    private EditText teamNameEdtTxtUpdate;
    private EditText memberIDUpdateEdtTxt;
    private EditText projectNameUpdateEdtTxt;
    private EditText descriptionUpdateEditText;
    private Button updateTeamButton;
    private Button deleteTeam;
    private Button updateMembersBtn;
    private CheckBox isPrivateTeamUpdate;
    private RecyclerView recyclerViewMemberIDsUpdate;
    private MemberIDAdapter memberIDAdapter;
    private List<String> memberIDsList;
    private EditText warningUpdate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_del_team);
        initilaize();
    }

    private void initilaize() {
        teamNameEdtTxtUpdate = findViewById(R.id.teamNameEdtTxtUpdate);
        descriptionUpdateEditText = findViewById(R.id.descriptionUpdateEditText);
        updateTeamButton = findViewById(R.id.updateTeamButton);
        deleteTeam = findViewById(R.id.deleteTeam);
        updateMembersBtn = findViewById(R.id.updateMembersBtn);
        isPrivateTeamUpdate = findViewById(R.id.isPrivateTeamUpdate);
        recyclerViewMemberIDsUpdate = findViewById(R.id.recyclerViewMemberIDs);
        recyclerViewMemberIDsUpdate.setLayoutManager(new LinearLayoutManager(this));

        memberIDsList = new ArrayList<>();
        memberIDAdapter = new MemberIDAdapter(memberIDsList);
        recyclerViewMemberIDsUpdate.setAdapter(memberIDAdapter);

        handle_update_team(getUpdateTeam());
        handleAddIDs(getUpdateMembersBtn());
        handleDeleteTeam(getDeleteTeam());
    }

    private void handleDeleteTeam(Button deleteTeam) {
        deleteTeam.setOnClickListener(e -> {

        });
    }

    private void handleAddIDs(Button addMembersBtn) {

        addMembersBtn.setOnClickListener(e -> {
            String memberID = getMemberIDUpdateEdtTxt().getText().toString().trim();

            if (!memberID.isEmpty()) {

                int id = Integer.parseInt(memberID);
                if (!memberIDsList.contains(memberID)) {
                    memberIDsList.add(memberID);
                    memberIDAdapter.notifyDataSetChanged();
                    memberIDUpdateEdtTxt.setText("");
                } else {
                    showToast("This member ID is already added.");
                }

            } else {
                showToast("Invalid input. Please enter a member ID.");
            }
        });
    }

    private void handle_update_team(Button updateTeamButton) {
        updateTeamButton.setOnClickListener(e -> {
            String teamName = getTeamNameEdtTxtUpdate().getText().toString().trim();
            String description = getDescriptionUpdateEdtTxt().getText().toString().trim();
            String projectName = getProjectNameUpdateEdtTxt().getText().toString().trim();


            if (!teamName.isEmpty()) {
                if (isMemberIDsListEmpty()) {
                    setWarningUpdate(findViewById(R.id.warning));
                    getWarningUpdate().setVisibility(View.VISIBLE);
                    showToast("Cannot create a team when there are no existing members.");
                    return;
                }

                Intent intent = new Intent(UpdateDelTeam.this, TeamDashboard.class);
                startActivity(intent);
                finish();
            } else {
                setWarningUpdate(findViewById(R.id.warning));
                getWarningUpdate().setVisibility(View.VISIBLE);
                showToast("Please fill in all fields.");
            }
        });
    }

    private boolean isMemberIDsListEmpty() {
        return memberIDsList.isEmpty();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    /*
        Getters & setters
     */
    public EditText getTeamNameEdtTxtUpdate() {
        return teamNameEdtTxtUpdate;
    }

    public void setTeamNameEdtTxtUpdate(EditText teamNameEdtTxtUpdate) {
        this.teamNameEdtTxtUpdate = teamNameEdtTxtUpdate;
    }

    public EditText getDescriptionUpdateEdtTxt() {
        return descriptionUpdateEditText;
    }

    public void setDescriptionUpdateEdtTxt(EditText descriptionUpdateEditText) {
        this.descriptionUpdateEditText = descriptionUpdateEditText;
    }

    public EditText getProjectNameUpdateEdtTxt() {
        return projectNameUpdateEdtTxt;
    }

    public void setProjectNameUpdateEdtTxt(EditText projectNameUpdateEdtTxt) {
        this.projectNameUpdateEdtTxt = projectNameUpdateEdtTxt;
    }

    public EditText getMemberIDUpdateEdtTxt() {
        return memberIDUpdateEdtTxt;
    }

    public void setMemberIDUpdateEdtTxt(EditText memberIDUpdateEdtTxt) {
        this.memberIDUpdateEdtTxt = memberIDUpdateEdtTxt;
    }

    public Button getUpdateTeam() {
        return updateTeamButton;
    }

    public void setUpdateTeam(Button updateTeamButton) {
        this.updateTeamButton = updateTeamButton;
    }

    public Button getUpdateMembersBtn() {
        return updateMembersBtn;
    }

    public void setUpdateMembersBtn(Button updateMembersBtn) {
        this.updateMembersBtn = updateMembersBtn;
    }

    public Button getDeleteTeam() {
        return deleteTeam;
    }

    public void setDeleteTeam(Button deleteTeam) {
        this.deleteTeam = deleteTeam;
    }

    public RecyclerView getRecyclerViewMemberIDsUpdate() {
        return recyclerViewMemberIDsUpdate;
    }

    public void setRecyclerViewMemberIDsUpdate(RecyclerView recyclerViewMemberIDsUpdate) {
        this.recyclerViewMemberIDsUpdate = recyclerViewMemberIDsUpdate;
    }

    public EditText getWarningUpdate() {
        return warningUpdate;
    }

    public void setWarningUpdate(EditText warningUpdate) {
        this.warningUpdate = warningUpdate;
    }

    public CheckBox getIsPrivateTeamUpdate() {
        return isPrivateTeamUpdate;
    }

    public void setIsPrivateTeamUpdate(CheckBox isPrivateTeamUpdate) {
        this.isPrivateTeamUpdate = isPrivateTeamUpdate;
    }

}