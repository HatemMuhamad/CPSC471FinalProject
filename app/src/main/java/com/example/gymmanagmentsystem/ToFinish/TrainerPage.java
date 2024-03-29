package com.example.gymmanagmentsystem.ToFinish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.R;

public class TrainerPage extends AppCompatActivity {
    String trainerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        trainerID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_page);

        Button gymInfoBtn = (Button)findViewById(R.id.gymInfoBtn);
        gymInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GymInfo.class);
                startIntent.putExtra("personGymID", trainerID);
                startActivity(startIntent);
            }
        });

        Button sessionInfoBtn = (Button)findViewById(R.id.checkSessionsBtn);
        sessionInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TrainersSessions.class);
                startIntent.putExtra("trainerID", trainerID);
                startActivity(startIntent);
            }
        });

        Button viewAccountBtn = (Button)findViewById(R.id.viewAccountBtn);
        viewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Account.class);
                startIntent.putExtra("trainerID", trainerID);
                startActivity(startIntent);
            }
        });
    }
}
