package com.example.gymmanagmentsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GymManagementSystem.db";
    public static SQLiteDatabase db = null;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase(); //this will create the database
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        System.out.println("\n\nCreating SQLLite database\n\n");

        SQLiteStatement createGymStatement = sqLiteDatabase.compileStatement("CREATE TABLE Gym (\n" +
                "Name Varchar(15),\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "Phone Varchar(11),\n" +
                "Street Varchar(30),\n" +
                "City Varchar(15),\n" +
                "ProvState Varchar(30),\n" +
                "Postal Varchar(6),\n" +
                "PRIMARY KEY(GymID),\n" +
                "UNIQUE(GymID) );");
        createGymStatement.execute();

        SQLiteStatement createPersonStatement = sqLiteDatabase.compileStatement("CREATE TABLE Person (\n" +
                "EmergencyContactPhone Varchar(11) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "Phone Varchar(11),\n" +
                "Street Varchar(30),\n" +
                "City Varchar(15),\n" +
                "ProvState Varchar(30),\n" +
                "Postal Varchar(6),\n" +
                "TFlag INT NOT NULL DEFAULT 0,\n" +
                "MFlag INT NOT NULL DEFAULT 1,\n" +
                "PRIMARY KEY(PersonGymID),\n" +
                "UNIQUE (PersonGymID) );");
        createPersonStatement.execute();

        SQLiteStatement createWorksForStatement = sqLiteDatabase.compileStatement("CREATE TABLE WorksFor(\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY (GymID , PersonGymID),\n" +
                "UNIQUE(GymID , PersonGymID),\n" +
                "FOREIGN KEY (GymID) REFERENCES Gym(GymID),\n" +
                "FOREIGN KEY (PersonGymID) REFERENCES Person(PersonGymID) );");
        createWorksForStatement.execute();

        SQLiteStatement createHasStatement = sqLiteDatabase.compileStatement("CREATE TABLE Has (\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY (GymID , PersonGymID),\n" +
                "UNIQUE (GymID,PersonGymID),\n" +
                "FOREIGN KEY (GymID) REFERENCES Gym(GymID),\n" +
                "FOREIGN KEY (PersonGymID) REFERENCES Person(PersonGymID) );");
        createHasStatement.execute();

        SQLiteStatement createMembershipStatement = sqLiteDatabase.compileStatement("CREATE TABLE Membership (\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "RenewalDate DATE NOT NULL,\n" +
                "TrainingRoomAccess BOOLEAN DEFAULT 1,\n" + //Java does not have typedef TRUE, instead TRUE = 1
                "PoolAccess BOOLEAN DEFAULT 1,\n" + //Java does not have typedef TRUE, instead TRUE = 1
                "PricePerMonth FLOAT NOT NULL,\n" +
                "PRIMARY KEY(PersonGymID),\n" +
                "UNIQUE (PersonGymID) );");
        createMembershipStatement.execute();

        SQLiteStatement createBillingReportStatement = sqLiteDatabase.compileStatement("CREATE TABLE BillingReport (\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "ReceiptPrice FLOAT,\n" +
                "Date DATE NOT NULL,\n" +
                "PRIMARY KEY(PersonGymID,Date),\n" +
                "FOREIGN KEY(PersonGymID) REFERENCES Person(PersonGymID),\n" +
                "UNIQUE (PersonGymID,Date) );");
        createBillingReportStatement.execute();

        SQLiteStatement createReservableStatement = sqLiteDatabase.compileStatement("CREATE TABLE Reservable (\n" +
                "ManufactureNumber Varchar(15) NOT NULL,\n" +
                "Description Varchar(100),\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(ManufactureNumber),\n" +
                "UNIQUE (ManufactureNumber) );");
        createReservableStatement.execute();

        SQLiteStatement createMachineStatement = sqLiteDatabase.compileStatement("CREATE TABLE Machine (\n" +
                "MuscleType Varchar(15),\n" +
                "ManufactureNumber Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(ManufactureNumber),\n" +
                "FOREIGN KEY(ManufactureNumber) REFERENCES Reservable(ManufactureNumber),\n" +
                "UNIQUE (ManufactureNumber) );");
        createMachineStatement.execute();

        SQLiteStatement createAccessoryStatement = sqLiteDatabase.compileStatement("CREATE TABLE Accessory (\n" +
                "AccessoryType Varchar(15),\n" +
                "ManufactureNumber Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(ManufactureNumber),\n" +
                "FOREIGN KEY(ManufactureNumber) REFERENCES Reservable(ManufactureNumber),\n" +
                "UNIQUE (ManufactureNumber) );");
        createAccessoryStatement.execute();

        SQLiteStatement createRoomStatement = sqLiteDatabase.compileStatement("CREATE TABLE Room (\n" +
                "ActivityType Varchar(15),\n" +
                "RoomNumber Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(RoomNumber),\n" +
                "UNIQUE (RoomNumber) );");
        createRoomStatement.execute();

        SQLiteStatement createSessionStatement = sqLiteDatabase.compileStatement("CREATE TABLE Session (\n" +
                "SessionID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "SessionType Varchar(30) DEFAULT 'Generic',\n" +
                "MuscleGroup Varchar(30),\n" +
                "TrainerID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(SessionID),\n" +
                "UNIQUE (SessionID) );");
        createSessionStatement.execute();

        SQLiteStatement createReservesStatement = sqLiteDatabase.compileStatement("CREATE TABLE Reserves (\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "ManufactureNumber Varchar(15),\n" +
                "RoomNumber Varchar(15),\n" +
                "SessionID Varchar(15),\n" +
                "Date DATE NOT NULL,\n" +
                "StartTime TIME NOT NULL,\n" +
                "EndTime TIME NOT NULL,\n" +
                "WeightRange INT,\n" +
                "PRIMARY KEY(PersonGymID,Date,StartTime),\n" +
                "FOREIGN KEY(ManufactureNumber) REFERENCES Reservable(ManufactureNumber),\n" +
                "FOREIGN KEY(RoomNumber) REFERENCES Room(RoomNumber),\n" +
                "FOREIGN KEY(SessionID) REFERENCES Session(SessionID),\n" +
                "UNIQUE (PersonGymID,Date,StartTime) );");
        createReservesStatement.execute();

        SQLiteStatement createBooksStatement = sqLiteDatabase.compileStatement("CREATE TABLE Books (\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "SessionID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(PersonGymID,SessionID),\n" +
                "FOREIGN KEY(PersonGymID) REFERENCES Person(PersonGymID),\n" +
                "FOREIGN KEY(SessionID) REFERENCES Session(SessionID),\n" +
                "UNIQUE (PersonGymID,SessionID) );");
        createBooksStatement.execute();

        SQLiteStatement createBuysStatement = sqLiteDatabase.compileStatement("CREATE TABLE Buys (\n" +
                "MonthlyPayDate DATE NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(MonthlyPayDate,PersonGymID),\n" +
                "FOREIGN KEY(PersonGymID) REFERENCES Membership(PersonGymID),\n" +
                "UNIQUE (PersonGymID) );");
        createBuysStatement.execute();

        System.out.println("\n\nSQLite Database should be created\n\n");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO how should we handle an upgrade?
    }

    public static SQLiteDatabase getDatabase(){
        return db;
    }

}
