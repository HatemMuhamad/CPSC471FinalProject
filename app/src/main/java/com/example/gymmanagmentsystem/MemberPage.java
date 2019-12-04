package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MemberPage extends AppCompatActivity {
    public String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_page);
        Button viewAccountBtn = (Button)findViewById(R.id.viewAccountBtn);
        if(getIntent().hasExtra("gymID")){
             id = getIntent().getExtras().getString("gymID");
        }
        viewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Account.class);
                startIntent.putExtra("gymID", id);
                startActivity(startIntent);
            }
        });
//        Button bookSessionBtn = (Button)findViewById(R.id.bookSessionsBtn);
//        bookSessionBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v){
//                Intent startIntent = new Intent(getApplicationContext(), OngoingSessions.class);
//                startIntent.putExtra("gymID", id);
//                startActivity(startIntent);
//            }
//        });
    }
}
