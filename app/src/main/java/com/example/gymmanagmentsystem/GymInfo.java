package com.example.gymmanagmentsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
        Cursor gymInfo = GymManagementController.viewGymInformation();
        gymInfo.moveToFirst();
        String ID = gymInfo.getString(gymInfo.getColumnIndexOrThrow("GymID"));
        String gymName = gymInfo.getString(gymInfo.getColumnIndexOrThrow("Name"));
        String gymPhone = gymInfo.getString(gymInfo.getColumnIndexOrThrow("Phone"));
        String gymStreet = gymInfo.getString(gymInfo.getColumnIndexOrThrow("Street"));
        String gymCity = gymInfo.getString(gymInfo.getColumnIndexOrThrow("City"));
        String gymProvince = gymInfo.getString(gymInfo.getColumnIndexOrThrow("ProvState"));
        String gymPostal = gymInfo.getString(gymInfo.getColumnIndexOrThrow("Postal"));

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
