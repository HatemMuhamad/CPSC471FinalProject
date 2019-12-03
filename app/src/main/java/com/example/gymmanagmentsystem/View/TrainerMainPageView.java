package com.example.gymmanagmentsystem.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.R;

public class TrainerMainPageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_view);

        Button gymInfoBtn = (Button)findViewById(R.id.gymInfoBtn);
        gymInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TrainerViewGymInfoView.class);
                startActivity(startIntent);
            }
        });

        Button sessionInfoBtn = (Button)findViewById(R.id.viewSessionsBtn);
        sessionInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TrainerViewSessionInfoView.class);
                startActivity(startIntent);
            }
        });

        Button viewAccountBtn = (Button)findViewById(R.id.viewAccountBtn);
        viewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TrainerViewAccountInfoView.class);
                startActivity(startIntent);
            }
        });
    }
}
