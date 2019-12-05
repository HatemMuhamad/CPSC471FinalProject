package com.example.gymmanagmentsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class editAccount extends AppCompatActivity {
    String trainerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        trainerID = extras.getString("trainerID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);
        try{
            saveChangesBtnListener();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    private void saveChangesBtnListener()throws SQLException {
        Button saveChangesBtn = (Button) findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAccount();
                finish();
            }
        });
    }
    public void EditAccount(){

        EditText ENCText = (EditText) findViewById(R.id.ECNTextField);
        EditText streetText = (EditText) findViewById(R.id.streetTextField);
        EditText provinceText = (EditText) findViewById(R.id.provinceTextField);
        EditText phoneText = (EditText) findViewById(R.id.phoneTextField);
        EditText postalText = (EditText) findViewById(R.id.postalTextField);
        EditText cityText = (EditText) findViewById(R.id.cityTextField);

        if (ENCText.getText().toString().equals("")){
            ENCText.setText("NULL");
        }
        if (streetText.getText().toString().equals("")){
            streetText.setText("NULL");
        }
        if (provinceText.getText().toString().equals("")){
            provinceText.setText("NULL");
        }
        if (phoneText.getText().toString().equals("")){
            phoneText.setText("NULL");
        }
        if (postalText.getText().toString().equals("")){
            postalText.setText("NULL");
        }
        if (cityText.getText().toString().equals("")){
            cityText.setText("NULL");
        }
        String ENC = ENCText.getText().toString();
        String street = streetText.getText().toString();
        String prov = provinceText.getText().toString();
        String phone = phoneText.getText().toString();
        String postal = postalText.getText().toString();
        String city = cityText.getText().toString();
        try{
            GymManagementController.editAccountInformation(ENC,GymManagementController.getUserID(),phone,street,city,prov,postal);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
