package com.example.gymmanagmentsystem.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.R;

public class TrainerEditAccountInfoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        Button saveChangesBtn = (Button)findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TAKE TO SIGN UP (CREATE ACCOUNT) PAGE
                Intent startIntent = new Intent(getApplicationContext(), TrainerViewAccountInfoView.class);
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
            }
        });
    }
}
