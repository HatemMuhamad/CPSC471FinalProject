package com.example.gymmanagmentsystem;

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
    DatabaseController dbc;
    private LinearLayout ongoingSessionList;
    private String sessionID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        memberID = extras.getString("memberID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing_sessions);
        ongoingSessionList = (LinearLayout) findViewById(R.id.ongoingSessionsLL);
        try {
            populateFields();
        }catch (SQLException e){
            e.printStackTrace();
        }
        setFilterButtonListener();
        clickedSessionListener();
        bookSessionverifyBtnListener();

        }

    private void clickedSessionListener(){
            final View child = ongoingSessionList.getChildAt(0);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sessionID += ((TextView)child.findViewById(R.id.sessionIDTV)).getText().toString();
                }
            });

    }
    private void bookSessionverifyBtnListener(){
        Button signInBtn = (Button) findViewById(R.id.verifybookSessionBtn);
        if(sessionID != null) {
            signInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //bookSession(sessionID,memberID);
                    Toast.makeText(OngoingSessions.this, "Session successfully booked!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
   /* public void bookSession(String SID, String PID){
        try {
            dbc.bookSession(SID, PID);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }*/

    public void populateFields() throws SQLException {
        LayoutInflater l = (LayoutInflater) getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        //String sessionInfoResult = dbc.viewOngoingSessions();
        //String[] sessInfo = sessionInfoResult.split(",");
        //int numOfSessions = Integer.parseInt(sessInfo[7]);
        for(int i = 0; i<3; i++) {
            final View v = l.inflate(R.layout.ongoing_sessions,null);
            TextView sessionID = v.findViewById(R.id.sessionIDTV);
            //sessionID.setText(sessInfo[0]);
            sessionID.setText("1234");
            TextView sessionType = v.findViewById(R.id.sessionTypeTV);
            //sessionType.setText(sessInfo[1]);
            sessionType.setText("Cardio");
            TextView date = v.findViewById(R.id.dateTV);
            //date.setText(sessInfo[2]);
            date.setText("9th December 2019");
            TextView startTime = v.findViewById(R.id.startTimeTV);
            //startTime.setText(sessInfo[3]);
            startTime.setText("05:00");
            TextView endTime = v.findViewById(R.id.endTimeTV);
            //endTime.setText(sessInfo[4]);
            endTime.setText("07:00");
            TextView muscleGroup = v.findViewById(R.id.muscleGroupTV);
            //muscleGroup.setText(sessInfo[5]);
            muscleGroup.setText("Heart");
            TextView TID = v.findViewById(R.id.trainerIDTV);
            //TID.setText(sessInfo[6]);
            if (v.getParent() != null) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            ongoingSessionList.addView(v, i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        }

    }

    private void setFilterButtonListener(){
        Spinner filterSpinner = (Spinner) findViewById(R.id.sessionFilterSpinner);
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



