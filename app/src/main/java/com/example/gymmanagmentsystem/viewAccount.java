package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewAccount extends AppCompatActivity {
    static Connection myConnection;
    private ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        try {
            viewAccount(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }


        Button editAccBtn = (Button)findViewById(R.id.editAccountBtn);
        editAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), editAccount.class);
                startActivity(startIntent);
            }
        });


    }

    void viewAccount(ResultSet rs) throws SQLException{
        PreparedStatement viewAccountInfo = myConnection.prepareStatement (
                "SELECT P.EmergencyContactPhone P.PersonGymID, P.Phone, P.Street, P.City, P.ProvState, P.Postal, P.MFlag FROM Person AS P WHERE P.PersonGymID = ? ");
        viewAccountInfo.setString (1, "personGymID");
        rs = viewAccountInfo.executeQuery();
        if(getIntent().hasExtra("ID")){
            TextView tv = (TextView) findViewById(R.id.IDTextView);
            String text = getIntent().getExtras().getString("ID");
            try {
                tv.setText(rs.getString("PersonGymID"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(getIntent().hasExtra("ENC")){
            TextView tv = (TextView) findViewById(R.id.ENCTextView);
            String text = getIntent().getExtras().getString("ENC");
            try {
                tv.setText(rs.getString("EmergencyContactPhone"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(getIntent().hasExtra("STREET")){
            TextView tv = (TextView) findViewById(R.id.streetTextView);
            try {
                tv.setText(rs.getString("Street"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(getIntent().hasExtra("PROVINCE")){
            TextView tv = (TextView) findViewById(R.id.provinceTextView);
            try {
                tv.setText(rs.getString("ProvState"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(getIntent().hasExtra("PHONE")){
            TextView tv = (TextView) findViewById(R.id.phoneTextView);
            try {
                tv.setText(rs.getString("Phone"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(getIntent().hasExtra("POSTAL")){
            TextView tv = (TextView) findViewById(R.id.postalTextView);
            try {
                tv.setText(rs.getString("Postal"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(getIntent().hasExtra("CITY")){
            TextView tv = (TextView) findViewById(R.id.cityTextView);
            try {
                tv.setText(rs.getString("City"));
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
