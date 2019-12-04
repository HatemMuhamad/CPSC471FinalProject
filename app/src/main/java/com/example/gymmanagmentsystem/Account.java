package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account extends AppCompatActivity {
    DatabaseController dbc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        dbc = new DatabaseController(this);
        try {
            ViewAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Button editAccBtn = (Button) findViewById(R.id.editAccountBtn);
        editAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), editAccount.class);
                startActivity(startIntent);
            }
        });


    }



    public void ViewAccount() {
        try {
            TextView ENC = (TextView) findViewById(R.id.ENCTextView);
            TextView st = (TextView) findViewById(R.id.streetTextView);
            TextView prov = (TextView) findViewById(R.id.provinceTextView);
            TextView phone = (TextView) findViewById(R.id.phoneTextView);
            TextView postal = (TextView) findViewById(R.id.postalTextView);
            TextView city = (TextView) findViewById(R.id.cityTextView);
            String accountInfo = dbc.viewAccountInformation();
            String[] accInfo = accountInfo.split(",");
            String ENCres = accInfo[0];
            String Stres = accInfo[1];
            String Provres = accInfo[2];
            String Phoneres = accInfo[3];
            String postalres = accInfo[4];
            String Cityres = accInfo[5];
            if (ENCres.equals("NULL")) {
                ENCres = "";
            }
            if (Stres.equals("NULL")) {
                Stres = "";
            }
            if (Provres.equals("NULL")) {
                Provres = "";
            }
            if (Phoneres.equals("NULL")) {
                Phoneres = "";
            }
            if (postalres.equals("NULL")) {
                postalres = "";
            }
            if (Cityres.equals("NULL")) {
                Cityres = "";
            }

            ENC.setText(ENCres);
            st.setText(Stres);
            prov.setText(Provres);
            phone.setText(Phoneres);
            postal.setText(postalres);
            city.setText(Cityres);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
