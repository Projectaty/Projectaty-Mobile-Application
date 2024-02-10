package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.projectaty.R;
import com.projectaty.activities.projectmanagment.Dashboard;
import com.projectaty.config.Prefrences;
import com.projectaty.data.UserRequest;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsernameLogin, editTextPasswordLogin;
    private CheckBox checkBoxRememberMe;
    private Button buttonGoToCreateAcc, buttonLogin;
    private UserRequest userRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initialize();
        userRequest = new UserRequest(this);
    }

    private void initialize() {
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
            checkBoxRememberMe.setChecked(true);
        }

        buttonGoToCreateAcc.setOnClickListener(e -> {
            Intent intent = new Intent(this, CreateAccount.class);
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(v -> loginUser());
    }

    public void loginUser() {
        String enteredUsername = editTextUsernameLogin.getText().toString();
        String enteredPassword = editTextPasswordLogin.getText().toString();
        userRequest.getStudentByNameAndPassword(enteredUsername, enteredPassword, new UserRequest.StudentByNameAndPasswordListener() {
            @Override
            public void onSuccess(int studentId, String username, String password, String email, String profilePic) {
                Prefrences.setStudentID(LoginActivity.this, studentId);
                start();
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void start() {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}
