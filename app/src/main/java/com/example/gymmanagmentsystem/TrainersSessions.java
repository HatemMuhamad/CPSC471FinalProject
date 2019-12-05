package com.example.gymmanagmentsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class TrainersSessions extends AppCompatActivity {

    private LinearLayout sessionList;

    String trainerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        trainerID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainers_sessions);
//    }
//    public void populateFields() throws SQLException {
//        String sessionInfoResult = GymManagementController.viewBookedSessions(trainerID);
//        String[] sessInfo = sessionInfoResult.split(",");
//        int numOfSessions = Integer.parseInt(sessInfo[5]);
//    }
        sessionList = (LinearLayout) findViewById(R.id.trainerSessionLL);
        try {
            populateFields();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        sessionList = (LinearLayout) findViewById(R.id.trainerSessionLL);
    }

    public void populateFields() throws SQLException {
        LayoutInflater l = (LayoutInflater) getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        Cursor sessions = GymManagementController.viewAssignedSessions(GymManagementController.getUserID());
        System.out.println(trainerID);
        sessions.moveToFirst();
        int numOfSessions = sessions.getCount();
        for(int i = 0; i<numOfSessions; i++) {
            final View v = l.inflate(R.layout.trainers_sessions,null);
            TextView sessionID = v.findViewById(R.id.sessionIDTextView);
            sessionID.setText(sessions.getString(sessions.getColumnIndexOrThrow("SessionID")));
            System.out.println(sessions.getString(sessions.getColumnIndexOrThrow("SessionID")));
            TextView ST = v.findViewById(R.id.startTimeTextView);
            ST.setText(sessions.getString(sessions.getColumnIndexOrThrow("StartTime")));
            TextView sessionType = v.findViewById(R.id.sessionTypeTextView);
            sessionType.setText(sessions.getString(sessions.getColumnIndexOrThrow("SessionType")));
            TextView muscleGroup = v.findViewById(R.id.muscleGroupTextView);
            muscleGroup.setText(sessions.getString(sessions.getColumnIndexOrThrow("MuscleGroup")));
            TextView roomNumber = v.findViewById(R.id.roomNumberTextView);
            roomNumber.setText(sessions.getString(sessions.getColumnIndexOrThrow("RoomNumber")));

            if (v.getParent() != null) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            sessionList.addView(v, i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            sessions.moveToNext();

        }

    }

}



