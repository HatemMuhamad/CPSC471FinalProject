package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.sql.*;

import androidx.appcompat.app.AppCompatActivity;

public class TrainerMainPageView extends AppCompatActivity {
    int personGymID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_view);

        // Query needed for GymInfo
        Button gymInfoBtn = (Button)findViewById(R.id.gymInfoBtn);
        gymInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ViewGymInfoView.class);
                startActivity(startIntent);
            }
        });


        Button sessionInfoBtn = (Button)findViewById(R.id.viewSessionsBtn);
        sessionInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TrainerViewSessionInfoView.class);
                PreparedStatement viewBookedSession = connection.PreparedStatement (
                        "SELECT S.sessionID, S.sessionType, S.MuscleGroup, R.StartTime FROM Session AS S, Reserves AS R  WHERE S.PersonGymID = ? AND S.sessionID = R.sessionID”);
                viewBookedSession.setString(1, personGymID);
                ResultSet rs = viewBookedSession.executeQuery();
                startActivity(startIntent);
            }
        });


        Button viewAccountBtn = (Button)findViewById(R.id.viewAccountBtn);
        viewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), viewAccount.class);
                PreparedStatement viewAccountInfo = connection.PreparedStatement (
                        "SELECT P.emergencyContactPhone, P.PersonGymID, P.Phone, P.Street, P.City, P.Province/ state, P.Postal, P.MFlag, B.MonthlyPayDate, M.PricePerMonth, FROM Person AS P, Buys AS B, Membership AS M WHERE P.PersonGymID = ? AND B.PersonGymID = P.PersonGymID AND M.PersonGymID = B.PersonGymID”);
                        viewAccountInfo.setString (1, personGymID);
                ResultSet rs = viewAccountInfo.executeQuery();

                startActivity(startIntent);
            }
        });






    }
}
