package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class CreateAccount extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextId, editTextEmail;
    private TextView textViewLoginPrompt;
    private Button buttonGoToLogin, buttonChoosePhoto;
    private static final int PICK_IMAGE = 1;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextId = findViewById(R.id.editTextId);
        editTextEmail = findViewById(R.id.editTextEmail);

        textViewLoginPrompt = findViewById(R.id.textViewLoginPrompt);
        buttonGoToLogin = findViewById(R.id.buttonGoToLogin);
        buttonChoosePhoto = findViewById(R.id.buttonChoosePhoto);

        // Check if user has account to show login prompt
        if (hasAccount()) {
            textViewLoginPrompt.setVisibility(View.VISIBLE);
            buttonGoToLogin.setVisibility(View.VISIBLE);
            buttonGoToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToLogin();
                }
            });
        }

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        buttonChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open gallery to choose photo
                Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
        }
    }

    public void registerUser() {
        String id = editTextId.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id", id);
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("password", password);
        // Save the selected image URI if available
        if (selectedImageUri != null) {
            editor.putString("profile_photo_uri", selectedImageUri.toString());
        }
        editor.apply();

        start();
    }

    private boolean hasAccount() {
        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);
        return preferences.contains("username") && preferences.contains("password");
    }

    private void start() {
        Intent intent = new Intent(this, StudentProfile.class);
        startActivity(intent);
        finish();
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
