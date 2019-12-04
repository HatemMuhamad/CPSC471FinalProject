package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class TrainersSessions extends AppCompatActivity {
    DatabaseController dbc;
    private LinearLayout sessionList;
    String trainerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        trainerID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainers_sessions);
        sessionList = (LinearLayout) findViewById(R.id.trainerSessionLL);
        try {
            populateFields();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        dbc = new DatabaseController(this);

        sessionList = (LinearLayout) findViewById(R.id.trainerSessionLL);
    }
    public void populateFields() throws SQLException {
        LayoutInflater l = (LayoutInflater) getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        System.out.println("Created thissss");
        String sessionInfoResult = dbc.viewAssignedSessions(trainerID);
        String[] sessInfo = sessionInfoResult.split(",");
        int numOfSessions = Integer.parseInt(sessInfo[5]);
            for(int i = 0; i<numOfSessions; i++) {
                final View v = l.inflate(R.layout.trainers_sessions,null);
                TextView sessionID = v.findViewById(R.id.sessionIDTextView);
                sessionID.setText(sessInfo[0]);
                TextView memberID = v.findViewById(R.id.memberIDTextView);
                memberID.setText(sessInfo[1]);
                TextView sessionType = v.findViewById(R.id.sessionTypeTextView);
                sessionType.setText(sessInfo[2]);
                TextView muscleGroup = v.findViewById(R.id.muscleGroupTextView);
                muscleGroup.setText(sessInfo[3]);
                TextView TID = v.findViewById(R.id.trainerIDTextView);
                TID.setText(sessInfo[4]);
                if (v.getParent() != null) {
                    ((ViewGroup) v.getParent()).removeView(v);
                }
                sessionList.addView(v, i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

            }

    }

}
