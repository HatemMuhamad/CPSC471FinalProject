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
                //TODO CHECK CREDENTIALS AGAINST DATABASE, SIGN INTO EITHER MEMBER OR TRAINER VIEW DEPENDING ON DATABASE DATA
                Intent startIntent = new Intent(getApplicationContext(),signInView.class);
                startActivity(startIntent);
            }
        });

        Button createAccountBtn = (Button)findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TAKE TO SIGN UP (CREATE ACCOUNT) PAGE
                Intent startIntent = new Intent(getApplicationContext(),SignUpView.class);
                startActivity(startIntent);
            }
        });
        Button trainerBtn = (Button)findViewById(R.id.trainerBtn);
        trainerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CHECK CREDENTIALS AGAINST DATABASE, SIGN INTO EITHER MEMBER OR TRAINER VIEW DEPENDING ON DATABASE DATA
                Intent startIntent = new Intent(getApplicationContext(),TrainerMainPageView.class);
                startActivity(startIntent);
            }
        });
        Button memberBtn = (Button)findViewById(R.id.memberBtn);
        memberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CHECK CREDENTIALS AGAINST DATABASE, SIGN INTO EITHER MEMBER OR TRAINER VIEW DEPENDING ON DATABASE DATA
                Intent startIntent = new Intent(getApplicationContext(),MemberMainPageView.class);
                startActivity(startIntent);
            }
        });
    }
}
