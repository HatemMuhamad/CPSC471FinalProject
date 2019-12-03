package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class editAccount extends AppCompatActivity {
    static Connection myConnection;
    String ID;
    String ENC;
    String street;
    String prov;
    String phone;
    String postal;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);

        Button saveChangesBtn = (Button)findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Account.class);
                EditText IDText = (EditText) findViewById(R.id.IDTextField);
                EditText ENCText = (EditText) findViewById(R.id.ECNTextField);
                EditText streetText = (EditText) findViewById(R.id.streetTextField);
                EditText provinceText = (EditText) findViewById(R.id.provinceTextField);
                EditText phoneText = (EditText) findViewById(R.id.phoneTextField);
                EditText postalText = (EditText) findViewById(R.id.postalTextField);
                EditText cityText = (EditText) findViewById(R.id.cityTextField);

                 ID = IDText.getText().toString();
                 ENC = ENCText.getText().toString();
                 street = streetText.getText().toString();
                 prov = provinceText.getText().toString();
                 phone = phoneText.getText().toString();
                 postal = postalText.getText().toString();
                 city = cityText.getText().toString();

                 try{
                     editAccount();
                 }catch(SQLException e){
                     e.printStackTrace();
                 }

            }
        });


    }
    void editAccount()throws SQLException {
        PreparedStatement updateAccountInfo = myConnection.prepareStatement (
                " UPDATE Person AS P SET P.emergencyContactPhone = ?, P.Phone = ?, P.Street = ?, P.City = ?, P.ProvState = ?, P.Postal = ? WHERE P.PersonGymID = ? ");
        updateAccountInfo.setString (1, ENC);
        updateAccountInfo.setString(2, ID);
        updateAccountInfo.setString(3, phone);
        updateAccountInfo.setString(4, street);
        updateAccountInfo.setString(5, city);
        updateAccountInfo.setString(6, prov);
        updateAccountInfo.executeUpdate();
    }
}
