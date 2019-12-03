package com.example.gymmanagmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.ResultSet;
import android.content.*;
import java.lang.*;

public class signInView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_view);
    }

    EditText personGymID = (EditText)findViewById(R.id.gymIDTextField);
    String gymID = personGymID.getText().toString();

    Prepared Statement signIn = connection.preparedStatement (
    "SELECT MFlag FROM Person WHERE PersonGymID = ?");
    sql.SetString(1, gymID);
    ResultSet rs = signIn.executeQuery();

    if(rs.next() == 1){
        Intent startIntent = new Intent(getApplicationContext(),MemberMainPageView.class);
        startActivity(startIntent);
    }
    else if(rs.next() == 0){
        Intent startIntent = new Intent(getApplicationContext(),TrainerMainPageView.class);
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
                        Intent startIntent = new Intent(getApplicationContext(),signInView.class);
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



    };



}



