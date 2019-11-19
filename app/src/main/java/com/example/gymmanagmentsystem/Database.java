package com.example.gymmanagmentsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context){
        super(context, "GymManagementDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String createTable_Gym = "CREATE TABLE Gym (\n" +
                "Name Varchar(15),\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "Phone Varchar(11),\n" +
                "Street Varchar(30),\n" +
                "City Varchar(15),\n" +
                "ProvState Varchar(30),\n" +
                "Postal Varchar(6),\n" +
                "PRIMARY KEY(GymID),\n" +
                "UNIQUE(GymID) );";

        String createTable_Person = "CREATE TABLE Person (\n" +
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
                "UNIQUE (PersonGymID) );";

        String createTable_WorksFor = "CREATE TABLE WorksFor(\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY (GymID , PersonGymID),\n" +
                "UNIQUE(GymID , PersonGymID),\n" +
                "FOREIGN KEY (GymID) REFERENCES Gym(GymID),\n" +
                "FOREIGN KEY (PersonGymID) REFERENCES Person(PersonGymID) );";

        String createTable_Has = "CREATE TABLE Has (\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY (GymID , PersonGymID),\n" +
                "UNIQUE (GymID,PersonGymID),\n" +
                "FOREIGN KEY (GymID) REFERENCES Gym(GymID),\n" +
                "FOREIGN KEY (PersonGymID) REFERENCES Person(PersonGymID) );";

        String createTable_Membership = "CREATE TABLE Membership (\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "RenewalDate DATE NOT NULL,\n" +
                "TrainingRoomAccess BOOLEAN,\n" +
                "PoolAccess BOOLEAN,\n" +
                "PricePerMonth FLOAT NOT NULL,\n" +
                "PRIMARY KEY(PersonGymID),\n" +
                "UNIQUE (PersonGymID) );";

        String createTable_BillingReport = "CREATE TABLE BillingReport (\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "ReceiptPrice FLOAT,\n" +
                "Date DATE NOT NULL,\n" +
                "PRIMARY KEY(PersonGymID,Date),\n" +
                "FOREIGN KEY(PersonGymID) REFERENCES Person(PersonGymID),\n" +
                "UNIQUE (PersonGymID,Date) );";

        String createTable_Reservable = "CREATE TABLE Reservable (\n" +
                "ManufactureNumber Varchar(15) NOT NULL,\n" +
                "Description Varchar(100),\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(ManufactureNumber),\n" +
                "UNIQUE (ManufactureNumber) );";

        String createTable_Machine = "CREATE TABLE Machine (\n" +
                "MuscleType Varchar(15),\n" +
                "ManufactureNumber Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(ManufactureNumber),\n" +
                "FOREIGN KEY(ManufactureNumber) REFERENCES Reservable(ManufactureNumber),\n" +
                "UNIQUE (ManufactureNumber) );";

        String createTable_Accessory = "CREATE TABLE Accessory (\n" +
                "AccessoryType Varchar(15),\n" +
                "ManufactureNumber Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(ManufactureNumber),\n" +
                "FOREIGN KEY(ManufactureNumber) REFERENCES Reservable(ManufactureNumber),\n" +
                "UNIQUE (ManufactureNumber) );";

        String createTable_Room = "CREATE TABLE Room (\n" +
                "ActivityType Varchar(15),\n" +
                "RoomNumber Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(RoomNumber),\n" +
                "UNIQUE (RoomNumber) );";

        String createTable_Session = "CREATE TABLE Session (\n" +
                "SessionID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "SessionType Varchar(30) DEFAULT 'Generic',\n" +
                "MuscleGroup Varchar(30),\n" +
                "TrainerID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(SessionID),\n" +
                "UNIQUE (SessionID) );";

        String createTable_Reserves = "CREATE TABLE Reserves (\n" +
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
                "UNIQUE (PersonGymID,Date,StartTime) );";

        String createTable_Books = "CREATE TABLE Books (\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "SessionID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(PersonGymID,SessionID),\n" +
                "FOREIGN KEY(PersonGymID) REFERENCES Person(PersonGymID),\n" +
                "FOREIGN KEY(SessionID) REFERENCES Session(SessionID),\n" +
                "UNIQUE (PersonGymID,SessionID) );";

        String createTable_Buys = "CREATE TABLE Buys (\n" +
                "MonthlyPayDate DATE NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "PRIMARY KEY(MonthlyPayDate,PersonGymID),\n" +
                "FOREIGN KEY(PersonGymID) REFERENCES Membership(PersonGymID),\n" +
                "UNIQUE (PersonGymID) );";

        sqLiteDatabase.execSQL(createTable_Gym);
        sqLiteDatabase.execSQL(createTable_Person);
        sqLiteDatabase.execSQL(createTable_WorksFor);
        sqLiteDatabase.execSQL(createTable_Has);
        sqLiteDatabase.execSQL(createTable_Membership);
        sqLiteDatabase.execSQL(createTable_BillingReport);
        sqLiteDatabase.execSQL(createTable_Reservable);
        sqLiteDatabase.execSQL(createTable_Machine);
        sqLiteDatabase.execSQL(createTable_Accessory);
        sqLiteDatabase.execSQL(createTable_Room);
        sqLiteDatabase.execSQL(createTable_Session);
        sqLiteDatabase.execSQL(createTable_Reserves);
        sqLiteDatabase.execSQL(createTable_Books);
        sqLiteDatabase.execSQL(createTable_Buys);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Gym");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Person");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS WorksFor");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Has");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Membership");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BillingReport");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Reservable");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Machine");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Accessory");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Room");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Session");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Reserves");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Buys");

        onCreate(sqLiteDatabase);
    }

    public SQLiteDatabase getDatabase(){
        return this.getWritableDatabase(); //might need a getReadableDatabase also
    }

    public void closeDatabaseConnection(){
        this.close();
    }


}
