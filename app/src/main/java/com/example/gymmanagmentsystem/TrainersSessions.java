package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class TrainersSessions extends AppCompatActivity {
    DatabaseController dbc;
    String trainerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        trainerID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainers_sessions);
        dbc = new DatabaseController(this);
    }
    public void populateFields() throws SQLException {
        String sessionInfoResult = dbc.viewBookedSessions(trainerID);
        String[] sessInfo = sessionInfoResult.split(",");
        int numOfSessions = Integer.parseInt(sessInfo[5]);
        }
}

