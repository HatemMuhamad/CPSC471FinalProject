package com.example.gymmanagmentsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signIn extends AppCompatActivity {
    private Person person;
    public String gymID;
    static Connection myConnection;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.sign_in);

                EditText personGymID = (EditText) findViewById(R.id.gymIDTextField);
                String gymID = personGymID.getText().toString();

                Button signInBtn = (Button) findViewById(R.id.signInBtn);
                signInBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            SignIn();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }



            void SignIn() throws SQLException{
            PreparedStatement signIn = myConnection.prepareStatement (
                    "SELECT MFlag FROM Person WHERE PersonGymID = ?");
            signIn.setString(1, gymID);
            ResultSet rs = signIn.executeQuery();

            if(rs.next() && rs.getInt("MFlag") == 1){
                person = new Member(gymID);
                Intent startIntent = new Intent(getApplicationContext(),MemberPage.class);
                startIntent.putExtra("gymID", person.getID());
                startActivity(startIntent);
            }
            else if(rs.next() && rs.getInt("MFlag") == 0){
                person = new Trainer(gymID);
                Intent startIntent = new Intent(getApplicationContext(),TrainerPage.class);
                startIntent.putExtra("gymID", person.getID());
                startActivity(startIntent);
            }
            else{
                Context context = getApplicationContext();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Uh oh ");

                builder.setMessage("Wrong Gym ID!" +
                        "Please Retry")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent startIntent = new Intent(getApplicationContext(),signIn.class);
                                startActivity(startIntent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        }
}







