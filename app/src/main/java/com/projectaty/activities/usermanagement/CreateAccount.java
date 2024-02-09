package com.projectaty.activities.usermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.projectaty.R;
import com.projectaty.data.UserRequest;

public class CreateAccount extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextId, editTextEmail;
    private TextView textViewLoginPrompt;
    private Button buttonGoToLogin, buttonChoosePhoto,  buttonRegister;
    private static final int PICK_IMAGE = 1;
    private Uri selectedImageUri;
    private ImageView view;

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
        view = findViewById(R.id.imageViewProfile);
        buttonGoToLogin.setVisibility(View.VISIBLE);
        buttonGoToLogin.setOnClickListener(e-> goToLogin());

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(o-> registerUser());

        buttonChoosePhoto.setOnClickListener(e->{
            Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            view.setImageURI(selectedImageUri);
        }
    }

    public void registerUser() {
        String id = editTextId.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        if (!id.isEmpty() && !username.isEmpty() && !email.isEmpty() && !password.isEmpty() && selectedImageUri != null) {
            UserRequest userRequest = new UserRequest(this);
            userRequest.addStudent(Integer.parseInt(id), username, password, email, selectedImageUri.toString());
        } else {
            Toast.makeText(getApplicationContext(), "Please fill in all required fields and select an image", Toast.LENGTH_SHORT).show();
        }

        start();
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
