package com.example.gymmanagmentsystem;

import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random;

public class CreateAccountView extends AppCompatActivity {
    private String personGymID;
    private String emergContactNumber;
    private String phoneNumber;
    private String streetName;
    private String cityName;
    private String provinceName;
    private String postalCode;
    private String Person;
    private int TFlag = 0;
    private int MFlag = 1;
    private Spinner personType;
    static java.sql.Connection myConnection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

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


//                personGymID = String.valueOf(rand.nextInt(10000));
                emergContactNumber = emergContactNoTextView.getText().toString();
                phoneNumber = userPhoneNoTextView.getText().toString();
                streetName = streetTextView.getText().toString();
                cityName = cityTextView.getText().toString();
                provinceName = provinceTextView.getText().toString();
                postalCode = postalTextView.getText().toString();

                //FIND A BETTER WAY TO DO THIS - I AM JUST DOING IT THIS WAY FOR NOW. IS THERE SOME ASSERT METHOD/FLAG ON THE TEXTVIEW ITSELF?
                if (emergContactNumber.isEmpty()) {
                    Toast.makeText(CreateAccountView.this, "You must supply an Emergency Contact Number",
                            Toast.LENGTH_SHORT).show();
                } else if (phoneNumber.isEmpty()) {
                    Toast.makeText(CreateAccountView.this, "You must supply a Phone Number",
                            Toast.LENGTH_SHORT).show();
                } else if (streetName.isEmpty()) {
                    Toast.makeText(CreateAccountView.this, "You must supply a Street Name",
                            Toast.LENGTH_SHORT).show();
                } else if (cityName.isEmpty()) {
                    Toast.makeText(CreateAccountView.this, "You must supply a City Name",
                            Toast.LENGTH_SHORT).show();
                } else if (provinceName.isEmpty()) {
                    Toast.makeText(CreateAccountView.this, "You must supply a Province Number",
                            Toast.LENGTH_SHORT).show();
                } else if (postalCode.isEmpty()) {
                    Toast.makeText(CreateAccountView.this, "You must supply a Postal Code",
                            Toast.LENGTH_SHORT).show();
                }

                //TODO get this to work
//                SecureRandom random = new SecureRandom();
//                byte[] salt = new byte[16];
//                random.nextBytes(salt);
//
//                MessageDigest md = null;
//                try {
//                    md = MessageDigest.getInstance("SHA-512");
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                }
//                md.update(salt);
//
//                byte[] rand = md.digest(phoneNumber.getBytes(StandardCharsets.UTF_8));
//                String personGymID = rand.toString().substring(0, 8);

                Random rand = new Random();
                int number = rand.nextInt(10000);
                personGymID = ((Integer)number).toString();

                addListenerOnSpinnerItemSelection();
                try{
                    CreateUser();
                }catch(SQLException e){
                    e.printStackTrace();
                }
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
                else if(Person.equals("Member")){
                    MFlag = 1;
                    TFlag = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                MFlag = 1;
                TFlag = 0;
            }
        });
    }


    void CreateUser()throws SQLException{
//        PreparedStatement CreateUser = myConnection.prepareStatement (
//                "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
//        CreateUser.setString (1, emergContactNumber);
//        CreateUser.setString(2, personGymID);
//        CreateUser.setString(3, phoneNumber);
//        CreateUser.setString(4, streetName);
//        CreateUser.setString(5, cityName);
//        CreateUser.setString(6, provinceName);
//        CreateUser.setString(7, postalCode);
//        CreateUser.setInt(8, TFlag);
//        CreateUser.setInt(9, MFlag);
//        CreateUser.executeQuery();


        int success = GymManagementController.signUp(emergContactNumber, personGymID, phoneNumber, streetName, cityName, provinceName,
                postalCode, TFlag, MFlag);

        if(success == 0){

        }

        else if (success == 1){

        }

//        SQLiteStatement statement = GymManagementController.getDatabase().compileStatement("INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
//        statement.bindString(1, emergContactNumber);
//        statement.bindString(2, personGymID);
//        statement.bindString(3, phoneNumber);
//        statement.bindString(4, streetName);
//        statement.bindString(5, cityName);
//        statement.bindString(6, provinceName);
//        statement.bindString(7, postalCode);
//        statement.bindDouble(8, TFlag);
//        statement.bindDouble(9, MFlag);
//        statement.execute();



        //TODO CHECK WHETHER ACCOUNT WAS CREATED, IF THEY WERE THEN RETURN TO THE MAIN PAGE AND TOAST WITH THEIR ID.
        //TODO IF THEY WERENT, RETURN TO THE MAIN PAGE AND TOAST WITH AN ERROR MESSAGE
    }
}














