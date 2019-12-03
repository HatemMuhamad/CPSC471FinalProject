package com.example.gymmanagmentsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.gymmanagmentsystem.editAccount.myConnection;

public class TrainersSessions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainers_sessions);

        try{
            viewSessions();
        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    void viewSessions()throws SQLException {
        PreparedStatement viewBookedSessions = myConnection.prepareStatement (
                "SELECT S.sessionID, S.sessionType, S.MuscleGroup, R.StartTime  FROM Session AS S, Reserves AS R  WHERE S.PersonGymID = ?  AND S.sessionID = R.sessionID");
        viewBookedSessions.setString(1, trainer.gymID);
        ResultSet rs = viewBookedSessions.executeQuery();
        viewBookedSessions.executeUpdate();
    }
}
