package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;



public class signIn extends AppCompatActivity {
    DatabaseController dbc = null;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.sign_in);
                dbc = new DatabaseController(this);
                try {
                    signInBtnListener();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }


        private void signInBtnListener() throws SQLException{
            Button signInBtn = (Button) findViewById(R.id.signInBtn);
            signInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SignIn();
                }
        });
        }

        public void SignIn(){
            EditText personGymID = (EditText) findViewById(R.id.gymIDTextField);
                try {
                    String gymID = personGymID.getText().toString();
                    int signInStatus = dbc.signIn(gymID);

                    if (signInStatus == 1) {
                        Intent startIntent = new Intent(getApplicationContext(), MemberPage.class);
                        startIntent.putExtra("memberID", gymID);
                        startActivity(startIntent);
                    } else if (signInStatus == 0) {
                        Intent startIntent = new Intent(getApplicationContext(), TrainerPage.class);
                        startIntent.putExtra("trainerID", gymID);
                        startActivity(startIntent);
                    } else {
                        Toast.makeText(signIn.this, "Invalid ID please try again!", Toast.LENGTH_LONG).show();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
}
}






