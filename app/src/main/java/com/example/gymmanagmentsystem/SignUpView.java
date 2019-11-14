package com.example.gymmanagmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);

        Button createAccountBtn = (Button)findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO GENERATE UNIQUE GYMID AND SAVE SOMEWHERE
                //TODO DATABASE WRITE - CREATE NEW USER WITH ALL OF THIS INFORMATION
            }
        });
    }
}
