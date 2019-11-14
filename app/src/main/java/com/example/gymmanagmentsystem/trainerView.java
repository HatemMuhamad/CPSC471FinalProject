package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class trainerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_view);

        Button gymInfoBtn = (Button)findViewById(R.id.gymInfoBtn);
        gymInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),gymInfoView.class);
                startActivity(startIntent);
            }
        });

        Button sessionInfoBtn = (Button)findViewById(R.id.viewSessionsBtn);
        sessionInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),sessionInfoView.class);
                startActivity(startIntent);
            }
        });
    }
}
