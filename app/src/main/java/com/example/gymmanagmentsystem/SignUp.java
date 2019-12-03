package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SignUp extends AppCompatActivity  {
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
        setContentView(R.layout.sign_up);
        System.out.println("Hello From Sign up");
         EditText emergContactNoTextView = (EditText) findViewById(R.id.ECNTextField);
         EditText userPhoneNoTextView = (EditText) findViewById(R.id.phoneTextField);
         EditText streetTextView = (EditText) findViewById(R.id.streetTextField);
         EditText cityTextView = (EditText) findViewById(R.id.cityTextField);
         EditText provinceTextView = (EditText) findViewById(R.id.provinceTextField);
         EditText postalTextView = (EditText) findViewById(R.id.postalTextField);


        Random rand = new Random();
        personGymID = String.valueOf(rand.nextInt(10000));
        emergContactNumber = emergContactNoTextView.getText().toString();
        phoneNumber = userPhoneNoTextView.getText().toString();
        streetName = streetTextView.getText().toString();
        cityName = cityTextView.getText().toString();
        provinceName = provinceTextView.getText().toString();
        postalCode = postalTextView.getText().toString();
        emergContactNumber = "12345";
        phoneNumber = "54434";
        streetName = "Hatem street";
        cityName = "Calgary";
        provinceName = "Alberta";
        postalCode = "T2N";
        TFlag = 0;
        MFlag = 1;
        Button createAccountBtn = (Button) findViewById(R.id.createAccountBtn);

            System.out.println(personGymID);


            System.out.println("String is not empty");
            System.out.println(streetName);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // DO your work here
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                    myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsc471", "root",
                            "Hatoom@1933");
                    System.out.println("Connected to the DB");
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    catch(ClassNotFoundException e){
                        e.printStackTrace();
                        System.out.println("Class not found");
                    }
                }
            }).start();






        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {

                //TODO GENERATE UNIQUE GYMID AND SAVE SOMEWHERE
                //TODO DATABASE WRITE - CREATE NEW USER WITH ALL OF THIS INFORMATION

                //FIND A BETTER WAY TO DO THIS - I AM JUST DOING IT THIS WAY FOR NOW. IS THERE SOME ASSERT METHOD/FLAG ON THE TEXTVIEW ITSELF?
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

                try{
                    CreateUser();
                }catch(SQLException e){
                    e.printStackTrace();
                }
             }
        });
    }

    void CreateUser()throws SQLException{
        PreparedStatement CreateUser = myConnection.prepareStatement (
                "INSERT INTO person (EmergencyContactPhone, PersonGymID, Phone, Street, City, ProvState, Postal, TFlag, MFlag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        CreateUser.setString (1, emergContactNumber);
        CreateUser.setString(2, personGymID);
        CreateUser.setString(3, phoneNumber);
        CreateUser.setString(4, streetName);
        CreateUser.setString(5, cityName);
        CreateUser.setString(6, provinceName);
        CreateUser.setString(7, postalCode);
        CreateUser.setInt(8, TFlag);
        CreateUser.setInt(9, MFlag);
        CreateUser.executeUpdate();
    }
}



