package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.*;
import androidx.appcompat.app.AppCompatActivity;

import static android.os.Build.VERSION_CODES.P;

public class viewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        if(getIntent().hasExtra("NAME")){
            TextView tv = (TextView) findViewById(R.id.nameTextView);
            String text = getIntent().getExtras().getString("NAME");
            tv.setText(text);
        }
        if(getIntent().hasExtra("ID")){
            TextView tv = (TextView) findViewById(R.id.IDTextView);
            String text = getIntent().getExtras().getString("ID");
            tv.setText(text);
        }
        if(getIntent().hasExtra("ENC")){
            TextView tv = (TextView) findViewById(R.id.ENCTextView);
            String text = getIntent().getExtras().getString("ENC");
            tv.setText(text);
        }
        int personGymID;
        Button editAccBtn = (Button)findViewById(R.id.editAccountBtn);
        editAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), editAccount.class);

                startActivity(startIntent);
            }
        });
        EditText nameText = (EditText)findViewById(R.id.nameText);
        String trainerName = nameText.getText().toString();

        EditText IDText = (EditText)findViewById(R.id.IDText);
        String trainerID = IDText.getText().toString();

        EditText postalText = (EditText)findViewById(R.id.postalText);
        String trainerPostal = postalText.getText().toString();

        EditText phoneText = (EditText)findViewById(R.id.phoneText);
        String trainerPhone = phoneText.getText().toString();

        EditText cityText = (EditText)findViewById(R.id.cityText);
        String cityPhone = cityText.getText().toString();

        EditText provinceText = (EditText)findViewById(R.id.provinceText);
        String trainerProvince = provinceText.getText().toString();

        EditText streetText = (EditText)findViewById(R.id.streetText);
        String trainerStreet = streetText.getText().toString();

        EditText ENCText = (EditText)findViewById(R.id.ENCText);
        String trainerENC = ENCText.getText().toString();



    }
}
