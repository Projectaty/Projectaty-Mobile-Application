package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.projectaty.R;

public class UpdateProfile extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private ImageView profileImageView;
    private Button changeProfilePicButton, saveChangesButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        profileImageView = findViewById(R.id.profileImageView);
        changeProfilePicButton = findViewById(R.id.changeProfilePicButton);
        saveChangesButton = findViewById(R.id.saveChangesButton);

        // Set OnClickListener for changeProfilePicButton to handle changing profile picture
        changeProfilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logic to change profile picture
                // You can open a file picker or camera intent here
                // and set the selected image to profileImageView
                Toast.makeText(UpdateProfile.this, "Change Profile Picture", Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for saveChangesButton to handle saving profile changes
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }

    private void saveChanges() {
        String newUsername = usernameEditText.getText().toString();
        String newPassword = passwordEditText.getText().toString();

        // Implement logic to save the changes to the user's profile
        // For example, you can update the username and password in the database
        // You can also save the new profile picture if it's changed

        // Display a toast message indicating that changes are saved
        Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show();

        // Assuming you want to navigate back to the profile after saving changes
        startActivity(new Intent(this, StudentProfile.class));
        finish(); // Finish the current activity to prevent user from coming back to update profile
    }
}
