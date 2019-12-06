package com.example.gymmanagmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLException;

public class BookedSessions extends AppCompatActivity {

    private LinearLayout sessionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_sessions);

        sessionList = (LinearLayout) findViewById(R.id.bookedSessionsLL);
        try {
            populateFields();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void populateFields() throws SQLException {
        LayoutInflater l = (LayoutInflater) getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        Cursor sessions = GymManagementController.viewBookedSessions(GymManagementController.getUserID());
        sessions.moveToFirst();
        int numOfSessions = sessions.getCount();
        for(int i = 0; i<numOfSessions; i++) {
            final View v = l.inflate(R.layout.activity_booked_sessions,null);
            TextView sessionID = v.findViewById(R.id.bookedsessionsIDTextView);
            sessionID.setText(sessions.getString(sessions.getColumnIndexOrThrow("SessionID")));
            System.out.println(sessions.getString(sessions.getColumnIndexOrThrow("SessionID")));
            TextView ST = v.findViewById(R.id.bookedstartTimeTextView);
            ST.setText(sessions.getString(sessions.getColumnIndexOrThrow("StartTime")));
            TextView sessionType = v.findViewById(R.id.bookedsessionsTypeTextView);
            sessionType.setText(sessions.getString(sessions.getColumnIndexOrThrow("SessionType")));
            TextView muscleGroup = v.findViewById(R.id.bookedmuscleGroupTextView);
            muscleGroup.setText(sessions.getString(sessions.getColumnIndexOrThrow("MuscleGroup")));
            TextView roomNumber = v.findViewById(R.id.bookedroomNumberTextView);
            roomNumber.setText(sessions.getString(sessions.getColumnIndexOrThrow("RoomNumber")));

            if (v.getParent() != null) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            sessionList.addView(v, i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            sessions.moveToNext();

        }

    }
}










