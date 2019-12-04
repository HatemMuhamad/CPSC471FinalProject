package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class OngoingSessions extends AppCompatActivity {
    public String memberID;
    DatabaseController dbc;
    private LinearLayout ongoingSessionList;
    private String sessionInfo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        memberID = extras.getString("memberID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing_sessions);
        ongoingSessionList = (LinearLayout) findViewById(R.id.ongoingSessionsLL);

        }

    private void clickedSessionListener(){
        for(int i = 0; i < ongoingSessionList.getChildCount(); i++){
            final View child = ongoingSessionList.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sessionInfo += ((TextView)child.findViewById(R.id.sessionIDTV)).getText().toString() ;
                }
            });
        }
    }


    public void populateFields() throws SQLException {
        LayoutInflater l = (LayoutInflater) getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        String sessionInfoResult = dbc.viewOngoingSessions();
        String[] sessInfo = sessionInfoResult.split(",");
        int numOfSessions = Integer.parseInt(sessInfo[5]);
        for(int i = 0; i<numOfSessions; i++) {
            final View v = l.inflate(R.layout.ongoing_sessions,null);
            TextView sessionID = v.findViewById(R.id.sessionIDTV);
            sessionID.setText(sessInfo[0]);
            TextView memberID = v.findViewById(R.id.memberIDTV);
            memberID.setText(sessInfo[1]);
            TextView sessionType = v.findViewById(R.id.sessionTypeTV);
            sessionType.setText(sessInfo[2]);
            TextView muscleGroup = v.findViewById(R.id.muscleGroupTV);
            muscleGroup.setText(sessInfo[3]);
            TextView TID = v.findViewById(R.id.trainerIDTV);
            TID.setText(sessInfo[4]);
            if (v.getParent() != null) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            ongoingSessionList.addView(v, i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

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



