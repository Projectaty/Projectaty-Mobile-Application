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
import androidx.appcompat.app.AppCompatActivity;
import com.projectaty.R;

public class StudentProfile extends AppCompatActivity {

    private TextView nameTextView, idTextView, usernameTextView, emailTextView;
    private ImageView profileImageView;
    private Button updateProfileButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

        nameTextView = findViewById(R.id.nameTextView);
        idTextView = findViewById(R.id.idTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        profileImageView = findViewById(R.id.profileImageView);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        logoutButton = findViewById(R.id.logoutButton);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString("username", "");
        String userId = sharedPreferences.getString("id", "");
        String userEmail = sharedPreferences.getString("email", "");
        String userProfilePhotoPath = sharedPreferences.getString("profile_photo_uri", "");

        nameTextView.setText(userName);
        idTextView.setText(userId);
        usernameTextView.setText(sharedPreferences.getString("username", ""));
        emailTextView.setText(userEmail);

        if (!userProfilePhotoPath.isEmpty()) {
            profileImageView.setImageURI(Uri.parse(userProfilePhotoPath));
        } else {
            profileImageView.setImageResource(R.drawable.profile);
        }

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentProfile.this, UpdateProfile.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                startActivity(new Intent(StudentProfile.this, LoginActivity.class));
                finish();
            }
        });
    }
}
