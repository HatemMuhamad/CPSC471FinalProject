package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;

public class editAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        Button saveChangesBtn = (Button)findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TAKE TO SIGN UP (CREATE ACCOUNT) PAGE
                Intent startIntent = new Intent(getApplicationContext(), viewAccount.class);
                EditText IDText = (EditText) findViewById(R.id.IDTextField);
                EditText nameText = (EditText) findViewById(R.id.nameTextField);
                EditText ENCText = (EditText) findViewById(R.id.ECNTextField);
                EditText streetText = (EditText) findViewById(R.id.streetTextField);
                EditText provinceText = (EditText) findViewById(R.id.provinceTextField);
                EditText phoneText = (EditText) findViewById(R.id.phoneTextField);
                EditText postalText = (EditText) findViewById(R.id.postalTextField);
                EditText cityText = (EditText) findViewById(R.id.cityTextField);

                String ID = IDText.getText().toString();
                String name = nameText.getText().toString();
                String ENC = ENCText.getText().toString();
                String street = streetText.getText().toString();
                String prov = provinceText.getText().toString();
                String phone = phoneText.getText().toString();
                String postal = postalText.getText().toString();
                String city = cityText.getText().toString();

                startIntent.putExtra("ID",ID);
                startIntent.putExtra("NAME",name);
                startIntent.putExtra("ENC",ENC);
                startIntent.putExtra("STREET",street);
                startIntent.putExtra("PROVINCE",prov);
                startIntent.putExtra("PHONE",phone);
                startIntent.putExtra("POSTAL",postal);
                startIntent.putExtra("CITY",city);

                startActivity(startIntent);

                int MFlag ;
                int TFlag;
                int personGymID;
                Button viewAccountBtn = (Button)findViewById(R.id.viewAccountBtn);
                viewAccountBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startIntent = new Intent(getApplicationContext(),viewAccount.class);
                        startActivity(startIntent);

                        PreparedStatement updateAccountInfo = connection.preparedStatement (
                                " UPDATE Person AS P SET P.emergencyContactPhone = ?, P.PersonGymID = ?, P.Phone = ?, P.Street = ?, P.City = ?, P.Province/ state = ?, P.Postal = ?, P.MFlag = ?, P.TFlag = ?, WHERE P.PersonGymID = ? ");
                        updateAccountInfo.setString (1, ENC);
                        updateAccountInfo.setString(2, phone);
                        updateAccountInfo.setString(3, street);
                        updateAccountInfo.setString(4, city);
                        updateAccountInfo.setString(5, prov);
                        updateAccountInfo.setString(6, postal);
                        updateAccountInfo.setInt(, TFlag);
                        updateAccountInfo.setInt(8, MFlag);
                        updateAccountInfo.setString(9, personGymID);
                        updateAccountInfo.executeQuery();
                    }
                }
            }
        });
    }
}
