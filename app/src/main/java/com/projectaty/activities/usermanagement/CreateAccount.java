package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.projectaty.R;

public class CreateAccount extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private TextView textViewLoginPrompt;
    private Button buttonGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        textViewLoginPrompt = findViewById(R.id.textViewLoginPrompt);
        buttonGoToLogin = findViewById(R.id.buttonGoToLogin);

        if (hasAccount()) {
            // If the user already has an account, show the login prompt and button
            textViewLoginPrompt.setVisibility(View.VISIBLE);
            buttonGoToLogin.setVisibility(View.VISIBLE);

            buttonGoToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Go to the login screen
                    goToLogin();
                }
            });
        }

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Register the user
                registerUser();
            }
        });
    }

    public void registerUser() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);

        if (!hasAccount()) {
            // If the user doesn't have an account, register them
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();

            start();
        } else {
            // If the user already has an account, show a message or take appropriate action
            // For simplicity, let's just show a toast message
            Toast.makeText(this, "You already have an account. Please log in.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasAccount() {
        // Check if the user already has an account
        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);
        return preferences.contains("username") && preferences.contains("password");
    }

    private void start() {

        Intent intent = new Intent(this, StudentProfile.class);
        startActivity(intent);
        finish();
    }

    private void goToLogin() {
        // Go to the login screen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
