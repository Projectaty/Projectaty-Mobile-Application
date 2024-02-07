package com.projectaty.activities.projectmanagment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.projectaty.R;

public class SearchProject extends AppCompatActivity {
    EditText keyword;
    Button search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seatch_task);

    }
}

