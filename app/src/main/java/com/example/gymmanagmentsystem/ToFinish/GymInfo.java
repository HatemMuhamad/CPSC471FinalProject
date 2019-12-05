package com.example.gymmanagmentsystem.ToFinish;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.DatabaseController.GymManagementController;
import com.example.gymmanagmentsystem.R;

import java.sql.SQLException;

public class GymInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_info_);
        try{
            viewGymInformation();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void viewGymInformation()throws SQLException {
        EditText IDText = (EditText) findViewById(R.id.ID);
        EditText nameText = (EditText) findViewById(R.id.gymName);
        EditText phoneText = (EditText) findViewById(R.id.gymPhone);
        EditText streetText = (EditText) findViewById(R.id.streetTextField);
        EditText cityText = (EditText) findViewById(R.id.cityTextField);
        EditText provinceText = (EditText) findViewById(R.id.provinceTextField);
        EditText postalText = (EditText) findViewById(R.id.postalTextField);
        String gymInfo = GymManagementController.viewGymInformation();
        String[] GymInfo = gymInfo.split(",");
        String gymName = GymInfo[0];
        String ID = GymInfo[1];
        String gymPhone = GymInfo[2];
        String gymStreet = GymInfo[3];
        String gymCity = GymInfo[4];
        String gymProvince = GymInfo[5];
        String gymPostal = GymInfo[6];

        if (ID.equals("NULL")) {
            ID = "";
        }
        if (gymName.equals("NULL")) {
            gymName = "";
        }
        if (gymPhone.equals("NULL")) {
            gymPhone = "";
        }
        if (gymStreet.equals("NULL")) {
            gymStreet = "";
        }
        if (gymCity.equals("NULL")) {
            gymCity = "";
        }
        if (gymProvince.equals("NULL")) {
            gymProvince = "";
        }
        if (gymPostal.equals("NULL")) {
            gymPostal = "";
        }
        IDText.setText(ID);
        nameText.setText(gymName);
        phoneText.setText(gymPhone);
        streetText.setText(gymStreet);
        cityText.setText(gymCity);
        provinceText.setText(gymProvince);
        postalText.setText(gymPostal);
    }
}
