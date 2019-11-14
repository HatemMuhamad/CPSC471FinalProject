package com.example.gymmanagmentsystem;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button memberBtn = (Button)findViewById(R.id.memberBtn);
        memberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),memberView.class);
                startActivity(startIntent);
            }
        });

        Button trainerBtn = (Button)findViewById(R.id.trainerBtn);
        trainerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),trainerView.class);
                startActivity(startIntent);
            }
        });
    }
}
