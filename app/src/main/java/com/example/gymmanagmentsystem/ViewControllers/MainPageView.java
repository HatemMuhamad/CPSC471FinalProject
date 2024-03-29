package com.example.gymmanagmentsystem.ViewControllers;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.R;
import com.example.gymmanagmentsystem.ToFinish.CreateAccountView;
import com.example.gymmanagmentsystem.DatabaseController.GymManagementController;
import com.example.gymmanagmentsystem.ToFinish.LoginView;

public class MainPageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GymManagementController sqlLiteDB = new GymManagementController(this.getApplicationContext());


        Button signInBtn = (Button)findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CHECK CREDENTIALS AGAINST DATABASE, SIGN INTO EITHER MEMBER OR TRAINER VIEW DEPENDING ON DATABASE DATA
                Intent startIntent = new Intent(getApplicationContext(), LoginView.class);
                startActivity(startIntent);
            }
        });

        Button createAccountBtn = (Button)findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TAKE TO SIGN UP (CREATE ACCOUNT) PAGE
                Intent startIntent = new Intent(getApplicationContext(), CreateAccountView.class);
                startActivity(startIntent);
            }
        });

    }
}
