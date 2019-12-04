package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.Random;

public class SignUp extends AppCompatActivity {
    DatabaseController dbc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        dbc = new DatabaseController(this);
        try{
            createAccountBtnListener();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void SignUp() {
        EditText emergContactNoTextView = (EditText) findViewById(R.id.ECNTextField);
        EditText userPhoneNoTextView = (EditText) findViewById(R.id.phoneTextField);
        EditText streetTextView = (EditText) findViewById(R.id.streetTextField);
        EditText cityTextView = (EditText) findViewById(R.id.cityTextField);
        EditText provinceTextView = (EditText) findViewById(R.id.provinceTextField);
        EditText postalTextView = (EditText) findViewById(R.id.postalTextField);
        try {
            Random rand = new Random();
            String personGymID = String.valueOf(rand.nextInt(10000));
            String emergContactNumber = emergContactNoTextView.getText().toString();
            String phoneNumber = userPhoneNoTextView.getText().toString();
            String streetName = streetTextView.getText().toString();
            String cityName = cityTextView.getText().toString();
            String provinceName = provinceTextView.getText().toString();
            String postalCode = postalTextView.getText().toString();
            int TFlag = 0;
            int MFlag = 1;
            if (emergContactNumber.isEmpty()) {
                Toast.makeText(SignUp.this, "You must supply an Emergency Contact Number",
                        Toast.LENGTH_SHORT).show();
            } else if (phoneNumber.isEmpty()) {
                Toast.makeText(SignUp.this, "You must supply a Phone Number",
                        Toast.LENGTH_SHORT).show();
            } else if (streetName.isEmpty()) {
                Toast.makeText(SignUp.this, "You must supply a Street Name",
                        Toast.LENGTH_SHORT).show();
            } else if (cityName.isEmpty()) {
                Toast.makeText(SignUp.this, "You must supply a City Name",
                        Toast.LENGTH_SHORT).show();
            } else if (provinceName.isEmpty()) {
                Toast.makeText(SignUp.this, "You must supply a Province Number",
                        Toast.LENGTH_SHORT).show();
            } else if (postalCode.isEmpty()) {
                Toast.makeText(SignUp.this, "You must supply a Postal Code",
                        Toast.LENGTH_SHORT).show();
            }

            Spinner personType = (Spinner) findViewById(R.id.Person_spinner);
            personType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String Person = parent.getItemAtPosition(position).toString();
                    if (Person.equals("Trainer")) {
                        int TFlag = 1;
                        int MFlag = 0;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            dbc.signUp(emergContactNumber, personGymID, phoneNumber, streetName, cityName, provinceName, postalCode, TFlag, MFlag);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createAccountBtnListener() throws SQLException {
        Button createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }
}














