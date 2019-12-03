package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberMainPageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_view);
        Button viewAccountBtn = (Button)findViewById(R.id.viewAccountBtn);
        viewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),viewAccount.class);
                PreparedStatement viewAccountInfo = connection.PreparedStatement (
                        "SELECT P.emergencyContactPhone, P.PersonGymID, P.Phone, P.Street, P.City, P.Province/ state, P.Postal, P.MFlag, B.MonthlyPayDate, M.PricePerMonth, FROM Person AS P, Buys AS B, Membership AS M WHERE P.PersonGymID = ? AND B.PersonGymID = P.PersonGymID AND M.PersonGymID = B.PersonGymID”);
                        viewAccountInfo.setString (1, personGymID);
                ResultSet rs = viewAccountInfo.executeQuery();
                startActivity(startIntent);
            }
        });
    }
}
