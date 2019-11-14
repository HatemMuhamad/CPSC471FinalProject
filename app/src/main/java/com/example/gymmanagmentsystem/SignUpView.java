package com.example.gymmanagmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.core.util.Preconditions.checkNotNull;

public class SignUpView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);

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

                System.out.println(phoneNumber);

                //FIND A BETTER WAY TO DO THIS - I AM JUST DOING IT THIS WAY FOR NOW
                if(emergContactNumber.isEmpty()){
                    Toast.makeText(SignUpView.this, "You must supply an Emergency Contact Number",
                            Toast.LENGTH_SHORT).show();
                }
                else if(phoneNumber.isEmpty()){
                    Toast.makeText(SignUpView.this,"You must supply a Phone Number",
                            Toast.LENGTH_SHORT).show();
                }
                else if(streetName.isEmpty()){
                    Toast.makeText(SignUpView.this,"You must supply a Street Name",
                            Toast.LENGTH_SHORT).show();
                }
                else if(cityName.isEmpty()){
                    Toast.makeText(SignUpView.this,"You must supply a City Name",
                            Toast.LENGTH_SHORT).show();
                }
                else if(provinceName.isEmpty()){
                    Toast.makeText(SignUpView.this,"You must supply a Province Number",
                            Toast.LENGTH_SHORT).show();
                }
                else if(postalCode.isEmpty()){
                    Toast.makeText(SignUpView.this,"You must supply a Postal Code",
                            Toast.LENGTH_SHORT).show();
                }

                
            }
        });
    }
}
