package com.example.gymmanagmentsystem.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.R;

public class TrainerViewAccountInfoView extends AppCompatActivity {

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
        Button editAccBtn = (Button)findViewById(R.id.editAccountBtn);
        editAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TrainerEditAccountInfoView.class);
                startActivity(startIntent);
            }
        });


    }
}
