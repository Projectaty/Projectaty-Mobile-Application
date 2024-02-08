package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;
import com.projectaty.activities.projectmanagment.Dashboard;
import com.projectaty.config.Prefrences;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsernameLogin, editTextPasswordLogin;
    private CheckBox checkBoxRememberMe;
    private Button buttonGoToCreateAcc, buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initialize();
    }
    private void initialize(){
        editTextUsernameLogin = findViewById(R.id.editTextUsernameLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
        buttonGoToCreateAcc = findViewById(R.id.buttonGoToCreateAcc);
        buttonLogin = findViewById(R.id.buttonLogin);


        boolean rememberMe = Prefrences.isRememberMe(this);
        if (rememberMe) {
            String savedUsername = Prefrences.getUsername(this);
            String savedPassword = Prefrences.getPassword(this);
            editTextUsernameLogin.setText(savedUsername);
            editTextPasswordLogin.setText(savedPassword);
            /*
                After you get the values saved in Remeber me
                make a login query to make sure I think on login click
             */
            checkBoxRememberMe.setChecked(true);
        }

        buttonGoToCreateAcc.setOnClickListener(e->{
            Intent intent = new Intent(this, CreateAccount.class);
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(v -> loginUser());
    }

    public void loginUser() {
        String enteredUsername = editTextUsernameLogin.getText().toString();
        String enteredPassword = editTextPasswordLogin.getText().toString();
        /*
            You took the username and password from the feilds
            now you need to check if the request is good or not
            from the database not the prefrence
            teh prefrence will save a rember me and put the values to the editText
         */
        if (true) {
            /*
            Make a query and save the student ID in the prefrence
            instaed of 1
             */
            int studentId =1;
            Prefrences.setStudentID(this, studentId);
            start();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void start() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}