package com.example.gymmanagmentsystem.ToFinish;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OngoingSessions extends AppCompatActivity {
    static Connection myConnection;
    public String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing_sessions);
        if(getIntent().hasExtra("gymID")){
            id = getIntent().getExtras().getString("gymID");
        }
    }
    void ongoingSessionsQuery() throws SQLException{
        PreparedStatement viewOngoingSession = myConnection.prepareStatement (
                "SELECT * FROM Session AS S WHERE S.PersonGymID = ? ");
        viewOngoingSession.setString(1, id);
        ResultSet rs = viewOngoingSession.executeQuery();
    }
}
