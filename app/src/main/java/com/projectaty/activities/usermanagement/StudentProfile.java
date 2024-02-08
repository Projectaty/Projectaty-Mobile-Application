package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.VolleyError;
import com.projectaty.R;
import com.projectaty.config.Prefrences;
import com.projectaty.data.UserRequest;

import java.util.prefs.Preferences;


public class StudentProfile extends AppCompatActivity {

    private TextView nameTextView, idTextView, usernameTextView, emailTextView;
    private ImageView profileImageView;
    private Button updateProfileButton, logoutButton;
    private UserRequest userRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);
        initialize();
    }

    private void initialize(){

        nameTextView = findViewById(R.id.nameStudent);
        idTextView = findViewById(R.id.idTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        profileImageView = findViewById(R.id.profileImageView);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        logoutButton = findViewById(R.id.logoutButton);

        userRequest = new UserRequest(this);
        int studentId = Prefrences.getStudentid(this);

        if (studentId != 0) {
            getStudentData(String.valueOf(studentId));
        } else {
            Toast.makeText(this, "Student ID not found", Toast.LENGTH_SHORT).show();
        }

        updateProfileButton.setOnClickListener(e-> {
                // Open the UpdateProfile activity when the button is clicked
                startActivity(new Intent(StudentProfile.this, UpdateProfile.class));
        });

        logoutButton.setOnClickListener(e-> { logout();});
    }

    private void getStudentData(String studentId) {
        userRequest.getStudentById(Integer.parseInt(studentId), new UserRequest.StudentByIdListener() {
            @Override
            public void onSuccess(int studentId, String username, String password, String email, String profilePic) {
                idTextView.setText(String.valueOf(studentId));
                usernameTextView.setText(username);
                emailTextView.setText(email);
                if (!profilePic.isEmpty()) {
                    profileImageView.setImageURI(Uri.parse(profilePic));
                } else {
                    profileImageView.setImageResource(R.drawable.profile);
                }
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(StudentProfile.this, "Failed to load student data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout() {
        Prefrences.clear(this);
        startActivity(new Intent(StudentProfile.this, LoginActivity.class));
        finish();
    }
}
