package com.projectaty.activities.usermanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.projectaty.config.Prefrences;

public class UpdateProfile extends AppCompatActivity {

    private EditText usernameEditText, idEditText, emailEditText, passwordEditText;
    private ImageView profileImageView;
    private Uri selectedImageUri;
    private static final int PICK_IMAGE = 1;
    private Button changeProfilePicButton, saveChangesButton, deleteAccountButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        initialize();
    }
    private void initialize(){
        usernameEditText = findViewById(R.id.usernameEditText);
        idEditText = findViewById(R.id.idEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        profileImageView = findViewById(R.id.profileImageView);
        changeProfilePicButton = findViewById(R.id.changeProfilePicButton);
        saveChangesButton = findViewById(R.id.saveChangesButton);
        deleteAccountButton = findViewById(R.id.deleteAccountButton);

        changeProfilePicButton.setOnClickListener(e->{
                // Open gallery to choose photo
                Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
        });

        saveChangesButton.setOnClickListener(e-> saveChanges());
        deleteAccountButton.setOnClickListener(e-> showConfirmationDialog());

        loadUserData();
    }

    private void loadUserData() {
        /*
        Load the data from the query Update

        usernameEditText.setText();
        idEditText.setText();
        emailEditText.setText();
        passwordEditText.setText();
        String profileImageUri = ;
        if (!profileImageUri.isEmpty()) {
            profileImageView.setImageURI(Uri.parse(profileImageUri));
        }
        */
    }

    private void saveChanges() {
        String newUsername = usernameEditText.getText().toString();
        String newId = idEditText.getText().toString();
        String newEmail = emailEditText.getText().toString();
        String newPassword = passwordEditText.getText().toString();
        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);

        /*
            update query use the object
         */
        Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure you want to delete your account?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUserData(Prefrences.getStudentid(UpdateProfile.this)); //geting the id from the prefrence
                Toast.makeText(UpdateProfile.this, "Account Deleted", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(UpdateProfile.this, CreateAccount.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteUserData(int userId) {
     /*
        Make a delete Query ddelet by ID
        and when delet must logout
      */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            profileImageView.setImageURI(selectedImageUri);
        }
    }
}
