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
import com.projectaty.data.UserRequest;


public class StudentProfile extends AppCompatActivity {

    private TextView nameTextView, idTextView, usernameTextView, emailTextView;
    private ImageView profileImageView;
    private Button updateProfileButton, logoutButton;
    private UserRequest userRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

        nameTextView = findViewById(R.id.nameStudent);
        idTextView = findViewById(R.id.idTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        profileImageView = findViewById(R.id.profileImageView);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        logoutButton = findViewById(R.id.logoutButton);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String studentId = sharedPreferences.getString("student_id", "");

        userRequest = new UserRequest(this);
        if (!studentId.isEmpty()) {
            getStudentData(studentId);
        } else {
            Toast.makeText(this, "Student ID not found", Toast.LENGTH_SHORT).show();
        }

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the UpdateProfile activity when the button is clicked
                startActivity(new Intent(StudentProfile.this, UpdateProfile.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        startActivity(new Intent(StudentProfile.this, LoginActivity.class));
        finish();
    }
}
