package com.projectaty.activities.teamsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.Gson;
import com.projectaty.R;
import com.projectaty.data.TaskRequest;
import com.projectaty.data.TeamRequest;
import com.projectaty.data.VolleySingleton;
import com.projectaty.data.TaskRequest;
import com.projectaty.data.VolleySingleton;
import com.projectaty.model.MemberIDAdapter;
import com.projectaty.model.Team;

import org.json.JSONException;

import org.json.JSONException;

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
        int teamID = getIntent().getIntExtra("teamID",0);
        String teamName = getIntent().getStringExtra("teamName");
        String description = getIntent().getStringExtra("describtion");

        setTeamNameEdtTxtUpdate(findViewById(R.id.teamNameEdtTxtUpdate));
        setMemberIDUpdateEdtTxt(findViewById(R.id.memberIDUpdateEdtTxt));
        setDescriptionUpdateEdtTxt(findViewById(R.id.descriptionUpdateEditText));
        setUpdateTeam(findViewById(R.id.updateTeamButton));
        setDeleteTeam(findViewById(R.id.deleteTeam));
        setUpdateMembersBtn(findViewById(R.id.updateMembersBtn));
        setIsPrivateTeamUpdate(findViewById(R.id.isPrivateTeamUpdate));
        setRecyclerViewMemberIDsUpdate(findViewById(R.id.recyclerViewMemberIDs));
        recyclerViewMemberIDsUpdate.setLayoutManager(new LinearLayoutManager(this));

        getTeamNameEdtTxtUpdate().setText(teamName);
        getDescriptionUpdateEdtTxt().setText(description);

        memberIDsList = new ArrayList<>();
        memberIDAdapter = new MemberIDAdapter(memberIDsList);
        recyclerViewMemberIDsUpdate.setAdapter(memberIDAdapter);

        setOldValues(teamID);

        String isPrivate = getIntent().getStringExtra("isPrivate");

        handleAddIDs(getUpdateMembersBtn());
        handle_delete_team(getDeleteTeam(), teamID);
        handle_update_team(getUpdateTeam(), teamID, isPrivate);
    }

    private void handle_delete_team(Button delete, int teamID) {
        delete.setOnClickListener(e -> {

            TaskRequest.deleteTaskByID(VolleySingleton.getInstance(this), new TaskRequest.TaskResponseCallback() {
                @Override
                public void onSuccess(Object response) {
                    String res = (String) response;
                    if (res.equals("deleted")) {
                        Toast.makeText(UpdateDelTeam.this, "Team deleted successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateDelTeam.this, TeamList.class);
                        intent.putExtra("teamID", teamID);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                }
            }, teamID);
        });
    }

    private void setOldValues(int teamIDInt) {
        /* Make volley request and fill the data into the textfeilds */
        TaskRequest.getTaskByID(VolleySingleton.getInstance(this), new TaskRequest.TaskResponseCallback() {
            @Override
            public void onSuccess(Object response) {
                Team team = (Team) response;
                getTeamNameEdtTxtUpdate().setText(team.getTeamName());
                getDescriptionUpdateEdtTxt().setText(team.getDescription());
//                        getIsPrivateTeamUpdate().setText();

            }

            @Override
            public void onError(String errorMessage) {
                Log.d("error", errorMessage);
            }
        }, teamIDInt);
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

    private void handle_update_team(Button updateTaskButton, int teamIDInt, String isPrivate) {
        updateTaskButton.setOnClickListener(e -> {
            String teamName = getTeamNameEdtTxtUpdate().getText().toString().trim();
            String description = getDescriptionUpdateEdtTxt().getText().toString().trim();


            if (!teamName.isEmpty()) { /* Make  a volley request to update the data */
                Team team = new Team(teamIDInt, teamName, description, isPrivate);
                try {
                    TeamRequest.updateTeamByID(VolleySingleton.getInstance(this), new TeamRequest.TeamResponseCallback() {
                        @Override
                        public void onSuccess(Object response) {
                            String res = (String) response;
                            if (res.equals("updated")) {
                                Toast.makeText(UpdateDelTeam.this, "Team updated successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateDelTeam.this, TeamList.class);
                                intent.putExtra("isPrivate", isPrivate);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Log.d("error", errorMessage);
                        }
                    }, teamIDInt, new Gson().toJson(team));
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                setWarningUpdate(findViewById(R.id.warningC));
                // At least the title should be added
                getWarningUpdate().setVisibility(View.VISIBLE);
            }
        });
    }

    private void handle_delete_team(Button delete, int teamID, String isPrivate) {
        delete.setOnClickListener(e -> {

            TaskRequest.deleteTaskByID(VolleySingleton.getInstance(this), new TaskRequest.TaskResponseCallback() {
                @Override
                public void onSuccess(Object response) {
                    String res = (String) response;
                    if (res.equals("deleted")) {
                        Toast.makeText(UpdateDelTeam.this, "Team deleted successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateDelTeam.this, TeamList.class);
                        intent.putExtra("teamID", teamID);
                        intent.putExtra("isPrivate", isPrivate);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                }
            }, teamID);
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