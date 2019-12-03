package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SignUpView extends AppCompatActivity {
    private String personGymID;
    private String emergContactNumber;
    private String phoneNumber;
    private String streetName;
    private String cityName;
    private String provinceName;
    private String postalCode;
    private String Person;
    private int TFlag;
    private int MFlag;
    private Spinner personType;
    static Connection myConnection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);

        final TextView emergContactNoTextView = (TextView) findViewById(R.id.ECNTextField);
        final TextView userPhoneNoTextView = (TextView) findViewById(R.id.phoneTextField);
        final TextView streetTextView = (TextView) findViewById(R.id.streetTextField);
        final TextView cityTextView = (TextView) findViewById(R.id.cityTextField);
        final TextView provinceTextView = (TextView) findViewById(R.id.provinceTextField);
        final TextView postalTextView = (TextView) findViewById(R.id.postalTextField);

        Button createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

                //TODO GENERATE UNIQUE GYMID AND SAVE SOMEWHERE
                //TODO DATABASE WRITE - CREATE NEW USER WITH ALL OF THIS INFORMATION
                try{
                    myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsc471gymmanagementsystemschema", "root",
                            "Gmcia330@");
                    System.out.println("Connection Successfully Made");
                }catch(SQLException e){
                    e.printStackTrace();
                }

//                Random rand = new Random(); //TODO We need to make sure that this is unique as well as random
//
//                 personGymID = String.valueOf(rand.nextInt(10000));
//                 emergContactNumber = emergContactNoTextView.getText().toString();
//                 phoneNumber = userPhoneNoTextView.getText().toString();
//                 streetName = streetTextView.getText().toString();
//                 cityName = cityTextView.getText().toString();
//                 provinceName = provinceTextView.getText().toString();
//                 postalCode = postalTextView.getText().toString();
//
//                //FIND A BETTER WAY TO DO THIS - I AM JUST DOING IT THIS WAY FOR NOW. IS THERE SOME ASSERT METHOD/FLAG ON THE TEXTVIEW ITSELF?
//                if (emergContactNumber.isEmpty()) {
//                    Toast.makeText(SignUpView.this, "You must supply an Emergency Contact Number",
//                            Toast.LENGTH_SHORT).show();
//                } else if (phoneNumber.isEmpty()) {
//                    Toast.makeText(SignUpView.this, "You must supply a Phone Number",
//                            Toast.LENGTH_SHORT).show();
//                } else if (streetName.isEmpty()) {
//                    Toast.makeText(SignUpView.this, "You must supply a Street Name",
//                            Toast.LENGTH_SHORT).show();
//                } else if (cityName.isEmpty()) {
//                    Toast.makeText(SignUpView.this, "You must supply a City Name",
//                            Toast.LENGTH_SHORT).show();
//                } else if (provinceName.isEmpty()) {
//                    Toast.makeText(SignUpView.this, "You must supply a Province Number",
//                            Toast.LENGTH_SHORT).show();
//                } else if (postalCode.isEmpty()) {
//                    Toast.makeText(SignUpView.this, "You must supply a Postal Code",
//                            Toast.LENGTH_SHORT).show();
//                }
//                addListenerOnSpinnerItemSelection();
//                try{
//                    CreateUser();
//                }catch(SQLException e){
//                    e.printStackTrace();
//                }
             }
        });
    }
    public void addListenerOnSpinnerItemSelection() {
        personType = (Spinner) findViewById(R.id.Person_spinner);
        personType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Person = parent.getItemAtPosition(position).toString();
                        if(Person.equals("Trainer")){
                            TFlag = 1;
                            MFlag = 0;
                        }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    void CreateUser()throws SQLException{
        PreparedStatement CreateUser = myConnection.prepareStatement (
                "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        CreateUser.setString (1, emergContactNumber);
        CreateUser.setString(2, personGymID);
        CreateUser.setString(3, phoneNumber);
        CreateUser.setString(4, streetName);
        CreateUser.setString(5, cityName);
        CreateUser.setString(6, provinceName);
        CreateUser.setString(7, postalCode);
        CreateUser.setInt(8, TFlag);
        CreateUser.setInt(9, MFlag);
        CreateUser.executeQuery();
    }
}



