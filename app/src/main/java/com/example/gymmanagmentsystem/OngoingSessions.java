package com.example.gymmanagmentsystem;

import android.database.Cursor;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.sql.SQLException;

public class OngoingSessions extends AppCompatActivity {
    public String memberID;
    private LinearLayout ongoingSessionList;
    private String sessionInfo = "";
    String SessionID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        memberID = extras.getString("memberID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing_sessions);
        ongoingSessionList =  findViewById(R.id.ongoingSessionsLL);
        try {
            populateFields();
        }catch(SQLException e){
            e.printStackTrace();
        }


        }




    public void populateFields() throws SQLException {
        LayoutInflater l = (LayoutInflater) getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        Cursor sessionInfoResult = GymManagementController.viewOngoingSessions();
        sessionInfoResult.moveToFirst();
        int numOfSessions = sessionInfoResult.getCount();
        for(int i = 0; i<numOfSessions; i++) {
            final View v = l.inflate(R.layout.ongoing_sessions,null);
            TextView sessionID = v.findViewById(R.id.sessionIDTV);
            SessionID = sessionInfoResult.getString(sessionInfoResult.getColumnIndexOrThrow("SessionID"));
            sessionID.setText(SessionID);
            TextView sessionType = v.findViewById(R.id.sessionTypeTV);
            sessionType.setText(sessionInfoResult.getString(sessionInfoResult.getColumnIndexOrThrow("SessionType")));
            TextView muscleGroup = v.findViewById(R.id.muscleGroupTV);
            muscleGroup.setText(sessionInfoResult.getString(sessionInfoResult.getColumnIndexOrThrow("MuscleGroup")));
            TextView startTime = v.findViewById(R.id.startTimeTV);
            startTime.setText(sessionInfoResult.getString(sessionInfoResult.getColumnIndexOrThrow("StartTime")));
            Button myBtn = (Button)v.findViewById(R.id.bookingbutton);
            myBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    String personGymID = GymManagementController.getUserID();
                    Toast.makeText(OngoingSessions.this, "Session successfully booked!", Toast.LENGTH_LONG).show();
                    try {
                        GymManagementController.bookSession(SessionID, personGymID);
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            });
            if (v.getParent() != null) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            ongoingSessionList.addView(v, i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            sessionInfoResult.moveToNext();

        }
    }

    private void setFilterButtonListener(View view){
        Spinner filterSpinner = view.findViewById(R.id.sessionFilterSpinner);
        String[] filterList = {"None","Cardio", "WeightLifting", "Crossfit"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filterList);
        filterSpinner.setAdapter(arrayAdapter);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When switching between fragments, sometimes onItemSelected() is activated without a view, resulting in NullPointerException.
                if (view == null){
                    return;
                }
                TextView selectedFilter = (TextView) view;
                String filterResult = selectedFilter.getText().toString();

                for (int i = 0; i < ongoingSessionList.getChildCount(); i++){
                    View aView = ongoingSessionList.getChildAt(i);
                    TextView sessionType = aView.findViewById(R.id.sessionTypeTV);
                    String sessionResult = sessionType.getText().toString();
                    if (!sessionResult.equals("Cardio") && filterResult.equals("Cardio")){
                        aView.setVisibility(View.GONE);
                    }
                    else if (!sessionResult.equals("WeightLifting") && filterResult.equals("WeightLifting")){
                        aView.setVisibility(View.GONE);
                    }
                    else if(!sessionResult.equals("Crossfit") && filterResult.equals("Crossfit")){
                        aView.setVisibility(View.GONE);
                    }
                    else {
                        aView.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}



