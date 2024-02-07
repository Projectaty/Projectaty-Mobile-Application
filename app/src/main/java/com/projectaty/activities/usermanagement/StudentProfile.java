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

    private TextView nameTextView, idTextView, usernameTextView, emailTextView;
    private ImageView profileImageView;
    private Button updateProfileButton, logoutButton; // Add logoutButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

        // Initialize views
        nameTextView = findViewById(R.id.nameTextView);
        idTextView = findViewById(R.id.idTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        profileImageView = findViewById(R.id.profileImageView);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        logoutButton = findViewById(R.id.logoutButton); // Initialize logoutButton

        // Load user data from SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString("userName", "");
        String userId = sharedPreferences.getString("userId", "");
        String userEmail = sharedPreferences.getString("userEmail", "");
        String userProfilePhotoPath = sharedPreferences.getString("userProfilePhotoPath", "");

        // Set user data to the respective TextViews
        nameTextView.setText(userName);
        idTextView.setText(userId);
        usernameTextView.setText(sharedPreferences.getString("username", ""));
        emailTextView.setText(userEmail);

        // Handle profile photo logic, you can set image here if you have it
        // profileImageView.setImageResource(R.drawable.profile);

        // Set OnClickListener for the Update Profile button
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the UpdateProfile activity when the button is clicked
                startActivity(new Intent(StudentProfile.this, UpdateProfile.class));
            }
        });

        // Set OnClickListener for the Log Out button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle log out logic here
                // For example, clear user session and navigate to the login screen
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Clear all saved preferences
                editor.apply();

                // Redirect to the login screen
                startActivity(new Intent(StudentProfile.this, LoginActivity.class));
                finish(); // Close the current activity
            }
        });
    }
}
