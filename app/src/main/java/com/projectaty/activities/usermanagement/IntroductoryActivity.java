package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class IntroductoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean hasAccount = sharedPreferences.getBoolean("hasAccount", false);
        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);

        if (hasAccount) {
            setContentView(R.layout.welcome_back);
            Button loginButton = findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(IntroductoryActivity.this, LoginActivity.class));
                }
            });

            if (rememberMe) {
                startActivity(new Intent(IntroductoryActivity.this, StudentProfile.class));
                finish();
            }

        } else {
            setContentView(R.layout.getting_started);
            Button createAccountButton = findViewById(R.id.createAccountButton);
            createAccountButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(IntroductoryActivity.this, CreateAccount.class));
                    finish();
                }
            });
        }
    }
}
