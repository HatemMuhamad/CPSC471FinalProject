package com.example.gymmanagmentsystem;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Database databaseHelper = new Database(this.getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signInBtn = (Button)findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CHECK CREDENTIALS AGAINST DATABASE, SIGN INTO EITHER MEMBER OR TRAINER VIEW DEPENDING ON DATABASE DATA
            }
        });

        Button createAccountBtn = (Button)findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set database in sign up view
                SignUpView signUpView = new SignUpView();
                signUpView.setDatabase(databaseHelper);

                //TAKE TO SIGN UP (CREATE ACCOUNT) PAGE
                Intent startIntent = new Intent(getApplicationContext(),signUpView.getClass());
                startActivity(startIntent);
            }
        });
    }
}
