package com.example.gymmanagmentsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymmanagmentsystem.R;

public class MembershipInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_info);


        populateTextFields();
    }

    public void populateTextFields(){


        EditText nextPayment = (EditText) findViewById(R.id.nextPaymentDateTextView);
        EditText price = (EditText) findViewById(R.id.priceTextView);
        EditText tanningRoomAccess = (EditText) findViewById(R.id.tanningRoomAccessTextView);
        EditText poolAccess = (EditText) findViewById(R.id.poolAccessTextView);
        Cursor mem = GymManagementController.viewmembership(GymManagementController.getUserID());
        if(mem.moveToFirst()) {
            String NextPayment = mem.getString(mem.getColumnIndexOrThrow("RenewalDate"));
            System.out.println(mem.getString(mem.getColumnIndexOrThrow("RenewalDate")));
            String Price = mem.getString(mem.getColumnIndexOrThrow("PricePerMonth"));
            String Tan = mem.getString(mem.getColumnIndexOrThrow("TrainingRoomAccess"));
            String pool = mem.getString(mem.getColumnIndexOrThrow("PoolAccess"));

            if (NextPayment.equals("NULL")) {
                NextPayment = "";
            }
            if (Price.equals("NULL")) {
                Price = "";
            }
            if (Tan.equals("NULL")) {
                Tan = "";
            }
            if (pool.equals("NULL")) {
                pool = "";
            }
            nextPayment.setText(NextPayment);
            price.setText(Price);
            tanningRoomAccess.setText(Tan);
            poolAccess.setText(pool);
        }
        else{
            System.out.println("Failed Case Test");
        }

    }
}
