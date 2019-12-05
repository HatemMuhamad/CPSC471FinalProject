package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class Account extends AppCompatActivity {

    String trainerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        trainerID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        try {
            ViewAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        editAccountBtnListener();

    }

    private void editAccountBtnListener(){
        Button editAccBtn = (Button) findViewById(R.id.editAccountBtn);
        editAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), editAccount.class);
                startIntent.putExtra("trainerID", trainerID);
                startActivity(startIntent);
            }
        });
    }

    public void ViewAccount() throws SQLException {
            TextView ENC = (TextView) findViewById(R.id.ENCTextView);
            TextView st = (TextView) findViewById(R.id.streetTextView);
            TextView prov = (TextView) findViewById(R.id.provinceTextView);
            TextView phone = (TextView) findViewById(R.id.phoneTextView);
            TextView postal = (TextView) findViewById(R.id.postalTextView);
            TextView city = (TextView) findViewById(R.id.cityTextView);
            Cursor accountInfo = GymManagementController.viewAccountInformation(trainerID);
            accountInfo.moveToNext();
            String ENCres =  accountInfo.getString(accountInfo.getColumnIndex("EmergencyContactPhone"));
            String Phoneres = accountInfo.getString(accountInfo.getColumnIndex("Phone"));
            String Stres = accountInfo.getString(accountInfo.getColumnIndex("Street"));
            String Cityres = accountInfo.getString(accountInfo.getColumnIndex("City"));
            String Provres = accountInfo.getString(accountInfo.getColumnIndex("ProvState"));
            String postalres = accountInfo.getString(accountInfo.getColumnIndex("Postal"));
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

    }
}
