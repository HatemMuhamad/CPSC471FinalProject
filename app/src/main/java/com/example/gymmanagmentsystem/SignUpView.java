package com.example.gymmanagmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Policy;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static androidx.core.util.Preconditions.checkNotNull;

public class SignUpView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // DO your work here
//                // get the data
//                if (activity_is_not_in_background) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // update UI
//                            runInBackground();
//                        }
//                    });
//                } else {
//                    runInBackground();
//                }
//            }
//        }).start();

        final TextView emergContactNoTextView = (TextView)findViewById(R.id.ECNTextField);
        final TextView userPhoneNoTextView = (TextView) findViewById(R.id.phoneTextField);
        final TextView streetTextView = (TextView)findViewById(R.id.streetTextField);
        final TextView cityTextView = (TextView)findViewById(R.id.cityTextField);
        final TextView provinceTextView = (TextView)findViewById(R.id.provinceTextField);
        final TextView postalTextView = (TextView)findViewById(R.id.postalTextField);

        Button createAccountBtn = (Button)findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO GENERATE UNIQUE GYMID AND SAVE SOMEWHERE
                //TODO DATABASE WRITE - CREATE NEW USER WITH ALL OF THIS INFORMATION

                String emergContactNumber = emergContactNoTextView.getText().toString();
                String phoneNumber = userPhoneNoTextView.getText().toString();
                String streetName = streetTextView.getText().toString();
                String cityName = cityTextView.getText().toString();
                String provinceName = provinceTextView.getText().toString();
                String postalCode = postalTextView.getText().toString();


                //FIND A BETTER WAY TO DO THIS - I AM JUST DOING IT THIS WAY FOR NOW. IS THERE SOME ASSERT METHOD/FLAG ON THE TEXTVIEW ITSELF?
                if (emergContactNumber.isEmpty()) {
                    Toast.makeText(SignUpView.this, "You must supply an Emergency Contact Number",
                            Toast.LENGTH_SHORT).show();
                } else if (phoneNumber.isEmpty()) {
                    Toast.makeText(SignUpView.this, "You must supply a Phone Number",
                            Toast.LENGTH_SHORT).show();
                } else if (streetName.isEmpty()) {
                    Toast.makeText(SignUpView.this, "You must supply a Street Name",
                            Toast.LENGTH_SHORT).show();
                } else if (cityName.isEmpty()) {
                    Toast.makeText(SignUpView.this, "You must supply a City Name",
                            Toast.LENGTH_SHORT).show();
                } else if (provinceName.isEmpty()) {
                    Toast.makeText(SignUpView.this, "You must supply a Province Number",
                            Toast.LENGTH_SHORT).show();
                } else if (postalCode.isEmpty()) {
                    Toast.makeText(SignUpView.this, "You must supply a Postal Code",
                            Toast.LENGTH_SHORT).show();
                }


                //create a unique gymID
                SecureRandom random = new SecureRandom();
                byte[] salt = new byte[16];
                random.nextBytes(salt);

                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("SHA-512");
                } catch (Exception e) {
                    System.out.println(e);
                }

                md.update(salt);
                byte[] hashedValue = md.digest(phoneNumber.getBytes(StandardCharsets.UTF_8));

                String gymID = hashedValue.toString();

                final String emergContactNumberFinal = emergContactNumber;
                final String gymIDFinal = gymID;
                final String phoneNumberFinal = phoneNumber;
                final String streetNameFinal = streetName;
                final String cityNameFinal = cityName;
                final String provinceNameFinal = provinceName;
                final String postalCodeFinal = postalCode;

                new Thread(new Runnable() { //MUST RUN SQL QUERIES ON BACKGROUND THREADS NOT TO PAUSE THE UI WITH LONG NETWORKING PROCESSES
                    @Override
                    public void run() {

                        String registerUserQueryString = "INSERT INTO person (EmergencyContactPhone, PersonGymID, Phone, Street, City, ProvState, Postal, TFlag, MFlag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement registerUserPreparedStatement = null;
                        try {
                            registerUserPreparedStatement = MySQLAccess.getDBConnection().prepareStatement(registerUserQueryString);

                            registerUserPreparedStatement.setString(1, emergContactNumberFinal);
                            registerUserPreparedStatement.setString(2, gymIDFinal);
                            registerUserPreparedStatement.setString(3, phoneNumberFinal);
                            registerUserPreparedStatement.setString(4, streetNameFinal);
                            registerUserPreparedStatement.setString(5, cityNameFinal);
                            registerUserPreparedStatement.setString(6, provinceNameFinal);
                            registerUserPreparedStatement.setString(7, postalCodeFinal);
                            registerUserPreparedStatement.setInt(1, 0);
                            registerUserPreparedStatement.setInt(2, 1); //TODO WE NEED A WAY TO CHECK WHETHER THEY ARE A MEMBER OR A TRAIANER

                            registerUserPreparedStatement.executeQuery();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
    }

}


