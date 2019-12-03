package com.example.gymmanagmentsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.gymmanagmentsystem.editAccount.myConnection;

public class MembershipInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_info);
    }

//Need a query for membership info
}
