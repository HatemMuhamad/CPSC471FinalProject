package com.example.gymmanagmentsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.gymmanagmentsystem.editAccount.myConnection;

public class OngoingSessions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing_sessions);

        try{
            ongoingSessions();
        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    void ongoingSessions()throws SQLException {
        PreparedStatement viewBookedSessions = myConnection.prepareStatement (
                "SELECT S.sessionID, S.sessionType, S.MuscleGroup, R.StartTime  FROM Session AS S, Reserves AS R  WHERE S.PersonGymID = ?  AND S.sessionID = R.sessionID");
        viewBookedSessions.setString(1, member.gymID);
        ResultSet rs = viewBookedSessions.executeQuery();
        viewBookedSessions.executeUpdate();
    }
}
