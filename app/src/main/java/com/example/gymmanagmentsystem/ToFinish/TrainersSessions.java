package com.example.gymmanagmentsystem.ToFinish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.DatabaseController.GymManagementController;
import com.example.gymmanagmentsystem.R;

import java.sql.SQLException;

public class TrainersSessions extends AppCompatActivity {
    String trainerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        trainerID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainers_sessions);
    }
    public void populateFields() throws SQLException {
        String sessionInfoResult = GymManagementController.viewBookedSessions(trainerID);
        String[] sessInfo = sessionInfoResult.split(",");
        int numOfSessions = Integer.parseInt(sessInfo[5]);
        }
}

