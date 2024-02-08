package com.projectaty.activities.usermanagement;
import android.app.LocaleManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.config.LangugaeManager;
import com.projectaty.config.Prefrences;

public class IntroductoryActivity extends AppCompatActivity {
    /*
        Custom Introductory Activity Based on user prefrences
     */
    Button lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Check if first time users go to getting started
        otherwise go to welcome back
         */
        if (!Prefrences.isFirstTime(this)) {
            setContentView(R.layout.welcome_back);
            Button loginButton = findViewById(R.id.LoginButton);
            loginButton.setOnClickListener(e-> startActivity(new Intent(IntroductoryActivity.this, LoginActivity.class)));
            if (Prefrences.isRememberMe(this)) {
                startActivity(new Intent(IntroductoryActivity.this, StudentProfile.class));
                finish();
            }
        } else {
            Prefrences.setNotFirstTime(this);
            setContentView(R.layout.getting_started);
            Button createAccountButton = findViewById(R.id.createAccountButton);
            createAccountButton.setOnClickListener(e-> startActivity(new Intent(IntroductoryActivity.this, CreateAccount.class)));
        }
        setLang(findViewById(R.id.btnlang));
        getLang().setOnClickListener(e->{
            String currentLang = Prefrences.getPrefLang(this);
            String newLang = (currentLang.equals("ar")) ? "en" : "ar";

            LangugaeManager.setLocale(this, newLang);
            Prefrences.setLanguage(this, newLang);

            recreate();
        });
    }

    public Button getLang() {
        return lang;
    }

    public void setLang(Button lang) {
        this.lang = lang;
    }
}
