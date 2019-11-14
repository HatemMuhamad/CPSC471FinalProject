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

        Button signInBtn = (Button)findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent startIntent = new Intent(getApplicationContext(),MemberMainPageView.class);
                //startActivity(startIntent);

                //TODO TAKE TO SIGN IN PAGE
            }
        });

        Button createAccountBtn = (Button)findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent startIntent = new Intent(getApplicationContext(),TrainerMainPageView.class);
                //startActivity(startIntent);

                //TODO TAKE TO SIGN UP (CREATE ACCOUNT) PAGE
            }
        });
    }
}
