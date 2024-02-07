package com.projectaty.activities.usermanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class UpdateProfile extends AppCompatActivity {

    private EditText usernameEditText, idEditText, emailEditText, passwordEditText;
    private ImageView profileImageView;
    private Button changeProfilePicButton, saveChangesButton, deleteAccountButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        idEditText = findViewById(R.id.idEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        profileImageView = findViewById(R.id.profileImageView);
        changeProfilePicButton = findViewById(R.id.changeProfilePicButton);
        saveChangesButton = findViewById(R.id.saveChangesButton);
        deleteAccountButton = findViewById(R.id.deleteAccountButton);

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

        // Set OnClickListener for deleteAccountButton to handle deleting the account
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void saveChanges() {
        String newUsername = usernameEditText.getText().toString();
        String newId = idEditText.getText().toString();
        String newEmail = emailEditText.getText().toString();
        String newPassword = passwordEditText.getText().toString();

        // Display a toast message indicating that changes are saved
        Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show();

        // Assuming you want to navigate back to the profile after saving changes
        startActivity(new Intent(this, StudentProfile.class));
        finish(); // Finish the current activity to prevent user from coming back to update profile
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure you want to delete your account?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Implement logic to delete the account
                // This can include deleting user data from database, clearing preferences, etc.
                Toast.makeText(UpdateProfile.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                // Redirect to the login screen or any appropriate screen after account deletion
                startActivity(new Intent(UpdateProfile.this, LoginActivity.class));
                finish(); // Finish the current activity
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, simply dismiss the dialog
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
