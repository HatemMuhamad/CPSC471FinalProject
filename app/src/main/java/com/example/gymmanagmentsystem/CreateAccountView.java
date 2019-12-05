package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        Button memberAccountBtn = (Button) findViewById(R.id.createMemberAccountBtn);
        Button trainerAccountBtn = (Button) findViewById(R.id.createTrainerAccountBtn);

        memberAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // personGymID = String.valueOf(rand.nextInt(10000));
                emergContactNumber = emergContactNoTextView.getText().toString();
                phoneNumber = userPhoneNoTextView.getText().toString();
                streetName = streetTextView.getText().toString();
                cityName = cityTextView.getText().toString();
                provinceName = provinceTextView.getText().toString();
                postalCode = postalTextView.getText().toString();

                //TODO Get this to work
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
                personGymID = ((Integer) number).toString();

                try {

                    int success = GymManagementController.signUp(emergContactNumber, personGymID, phoneNumber, streetName, cityName, provinceName,
                            postalCode, 0, 1);

                    if (success == 0) {
                        Toast.makeText(CreateAccountView.this, "Could Not Create Account",
                                Toast.LENGTH_SHORT).show();

                        emergContactNoTextView.setText("");
                        userPhoneNoTextView.setText("");
                        streetTextView.setText("");
                        cityTextView.setText("");
                        provinceTextView.setText("");
                        postalTextView.setText("");

                    } else if (success == 1) {
                        Toast.makeText(CreateAccountView.this, "Your Gym ID is: " + personGymID,
                                Toast.LENGTH_LONG).show();

                        emergContactNoTextView.setText("");
                        userPhoneNoTextView.setText("");
                        streetTextView.setText("");
                        cityTextView.setText("");
                        provinceTextView.setText("");
                        postalTextView.setText("");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        trainerAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // personGymID = String.valueOf(rand.nextInt(10000));
                emergContactNumber = emergContactNoTextView.getText().toString();
                phoneNumber = userPhoneNoTextView.getText().toString();
                streetName = streetTextView.getText().toString();
                cityName = cityTextView.getText().toString();
                provinceName = provinceTextView.getText().toString();
                postalCode = postalTextView.getText().toString();

                //TODO Get this to work
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
                personGymID = ((Integer) number).toString();

                try {

                    int success = GymManagementController.signUp(emergContactNumber, personGymID, phoneNumber, streetName, cityName, provinceName,
                            postalCode, 1, 0);


                    if (success == 0) {
                        Toast.makeText(CreateAccountView.this, "Could Not Create Account",
                                Toast.LENGTH_SHORT).show();

                        emergContactNoTextView.setText("");
                        userPhoneNoTextView.setText("");
                        streetTextView.setText("");
                        cityTextView.setText("");
                        provinceTextView.setText("");
                        postalTextView.setText("");

                    } else if (success == 1) {
                        Toast.makeText(CreateAccountView.this, "Your Gym ID is: " + personGymID,
                                Toast.LENGTH_LONG).show();

                        emergContactNoTextView.setText("");
                        userPhoneNoTextView.setText("");
                        streetTextView.setText("");
                        cityTextView.setText("");
                        provinceTextView.setText("");
                        postalTextView.setText("");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}















