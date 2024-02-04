package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.projectaty.R;

public class StudentProfile extends AppCompatActivity {

    private TextView nameTextView;
    private ImageView profileImageView;
    private Button updateProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

        // Initialize views
        nameTextView = findViewById(R.id.nameStudent);
        profileImageView = findViewById(R.id.profileImageView);
        updateProfileButton = findViewById(R.id.updateProfileButton);

        // Load user data from SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString("userName", "");

        // Set user name to the TextView
        nameTextView.setText(userName);

        // Set OnClickListener for profileImageView to handle photo profile upload
        profileImageView.setOnClickListener(v -> {
            // Handle profile photo upload logic
        });

        // Set OnClickListener for the Update Profile button
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the UpdateProfile activity when the button is clicked
                startActivity(new Intent(StudentProfile.this, UpdateProfile.class));
            }
        });
    }
}
