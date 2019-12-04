package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MemberPage extends AppCompatActivity {
    public String memberID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        memberID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_page);

        Button viewAccountBtn = (Button)findViewById(R.id.viewAccountBtn);
        viewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Account.class);
                startIntent.putExtra("memberID", memberID);
                startActivity(startIntent);
            }
        });

        Button bookSessionBtn = (Button)findViewById(R.id.bookSessionsBtn);
        bookSessionBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), OngoingSessions.class);
                startIntent.putExtra("memberID", memberID);
                startActivity(startIntent);
            }
        });

        Button membershipInfoBtn = (Button) findViewById(R.id.membershipBtn);
        membershipInfoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), MembershipInfo.class);
                startIntent.putExtra("memberID", memberID);
                startActivity(startIntent);
            }
        });
        Button gymInfoBtn = (Button) findViewById(R.id.gymInfoBtn);
        gymInfoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), GymInfo.class);
                startIntent.putExtra("memberID", memberID);
                startActivity(startIntent);
            }
        });

    }
}
