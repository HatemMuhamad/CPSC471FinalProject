package com.example.gymmanagmentsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLException;

public class ReserveEquipment extends AppCompatActivity {
    private LinearLayout equipmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_equipment);
        equipmentList = (LinearLayout) findViewById(R.id.equipmentLL);
        try {
            populateFields();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void populateFields() throws SQLException {
        LayoutInflater l = (LayoutInflater) getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        Cursor equipment = GymManagementController.viewAllEquipment();
        equipment.moveToFirst();
        int numOfSessions = equipment.getCount();
        for(int i = 0; i<numOfSessions; i++) {
            final View v = l.inflate(R.layout.reserve_equipment,null);
            TextView MNF = v.findViewById(R.id.MNTextView);
            MNF.setText(equipment.getString(equipment.getColumnIndexOrThrow("ManufactureNumber")));

            TextView Desc = v.findViewById(R.id.DescriptionTextView);
            Desc.setText(equipment.getString(equipment.getColumnIndexOrThrow("Description")));
            TextView gymID = v.findViewById(R.id.GymIDTextView);
            gymID.setText(equipment.getString(equipment.getColumnIndexOrThrow("GymID")));
            TextView empty = v.findViewById(R.id.empty);
            empty.setText("");


            if (v.getParent() != null) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            equipmentList.addView(v, i, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            equipment.moveToNext();

        }

    }
}
