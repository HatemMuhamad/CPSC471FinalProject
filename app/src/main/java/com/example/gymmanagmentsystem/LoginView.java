package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;



public class LoginView extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.sign_in);
                try {
                    signInBtnListener();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }


        private void signInBtnListener() throws SQLException{
            Button signInBtn = (Button) findViewById(R.id.verifySigninBtn);
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
                    personGymID.setText("");
                    int signInStatus = GymManagementController.signIn(gymID);

                    if (signInStatus == 1) {
                        Intent startIntent = new Intent(getApplicationContext(), MemberPage.class);
                        startIntent.putExtra("memberID", gymID);
                        startActivity(startIntent);
                    } else if (signInStatus == 0) {
                        Intent startIntent = new Intent(getApplicationContext(), TrainerPage.class);
                        startIntent.putExtra("trainerID", gymID);
                        startActivity(startIntent);
                    } else {
                        Intent startIntent = new Intent(getApplicationContext(), TrainerPage.class);
                        startIntent.putExtra("trainerID", gymID);
                        startActivity(startIntent);
                        Toast.makeText(LoginView.this, "Invalid ID please try again!", Toast.LENGTH_LONG).show();
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
}
}







