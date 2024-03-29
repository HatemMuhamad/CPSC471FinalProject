package com.example.gymmanagmentsystem.DatabaseController;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.gymmanagmentsystem.ToFinish.SingletonUser;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class GymManagementController extends SQLiteOpenHelper {

    static SingletonUser user;


    public static final String DATABASE_NAME = "GymManagementSystem.db";
    public static SQLiteDatabase db = null;

    public GymManagementController(@Nullable Context context) {
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
                "TFlag VARCHAR(45) NOT NULL DEFAULT '0',\n" +
                "MFlag VARCHAR(45) NOT NULL DEFAULT '1',\n" +
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
                "RenewalDate VARCHAR(45) NOT NULL,\n" +
                "TrainingRoomAccess VARCHAR DEFAULT '1',\n" + //Java does not have typedef TRUE, instead TRUE = 1
                "PoolAccess VARCHAR DEFAULT '1',\n" + //Java does not have typedef TRUE, instead TRUE = 1
                "PricePerMonth VARCHAR(45) NOT NULL,\n" +
                "PRIMARY KEY(PersonGymID),\n" +
                "UNIQUE (PersonGymID) );");
        createMembershipStatement.execute();

        SQLiteStatement createBillingReportStatement = sqLiteDatabase.compileStatement("CREATE TABLE BillingReport (\n" +
                "GymID Varchar(15) NOT NULL,\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "ReceiptPrice VARCHAR(45),\n" +
                "Date VARCHAR(45) NOT NULL,\n" +
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
                "MonthlyPayDate VARCHAR(45) NOT NULL,\n" +
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


    public static int signIn(String personGymID) throws SQLException {

        String args[] = {personGymID};

        Cursor cs = db.rawQuery("SELECT * FROM person WHERE PersonGymID = ?", args);

        if (cs.moveToNext()) {
            user = SingletonUser.getInstance(cs.getString(cs.getColumnIndexOrThrow("PersonGymID")),
                    Integer.parseInt(cs.getString(cs.getColumnIndexOrThrow("MFlag"))),
                    Integer.parseInt(cs.getString(cs.getColumnIndexOrThrow("TFlag"))));

            return cs.getInt(cs.getColumnIndexOrThrow("MFlag")); //returns 0 if trainer, 1 if member
        }
        else{
            return -1; //returns -1 if unauthorized
        }
    }



    public static void signUp(String ENC, String PID, String phone, String street, String city, String provState, String postal, int TFlag, int MFlag) throws SQLException {

        String args[] = {ENC, PID, phone, street, city, provState, postal, ((Integer)TFlag).toString(), ((Integer)MFlag).toString()};

        db.rawQuery("INSERT INTO person (EmergencyContactPhone, PersonGymID, Phone, Street, City, ProvState, Postal, TFlag, MFlag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", args);

    }



    public static String viewAccountInformation(String personGymID) throws SQLException {
        String result = "";

        String args[] = {personGymID};

        Cursor cs = db.rawQuery("SELECT P.EmergencyContactPhone P.PersonGymID, P.Phone, P.Street, P.City, P.ProvState, P.Postal, P.MFlag FROM person AS P WHERE P.PersonGymID = ?", args);

        while (cs.moveToNext()) {
            result += cs.getString(cs.getColumnIndexOrThrow("EmergencyContactPhone")) + "," + cs.getString(cs.getColumnIndexOrThrow("PersonGymID")) + "," +
                    cs.getString(cs.getColumnIndexOrThrow("Phone")) + "," + cs.getString(cs.getColumnIndexOrThrow("Street")) + "," +
                    cs.getString(cs.getColumnIndexOrThrow("City")) + "," + cs.getString(cs.getColumnIndexOrThrow("ProvState")) + "," + cs.getString(cs.getColumnIndexOrThrow("Postal"));
        }
        return result;
    }



    public static void editAccountInformation(String ENC,String personGymID,String phone, String street, String city, String provState, String postal) throws SQLException {

        String args[] = {ENC, phone, street, city, provState, postal, personGymID};

        db.rawQuery(" UPDATE person AS P SET P.emergencyContactPhone = ?, P.Phone = ?, P.Street = ?, P.City = ?, P.ProvState = ?, P.Postal = ? WHERE P.PersonGymID = ?", args);

    }



    public static String viewOngoingSessions() throws SQLException {
        String result = "";

        Cursor cs = db.rawQuery("SELECT * FROM session", null);


        while (cs.moveToNext()) {
            result += cs.getString(cs.getColumnIndexOrThrow("SessionID")) + "," + cs.getString(cs.getColumnIndexOrThrow("PersonGymID")) + "," +
                    cs.getString(cs.getColumnIndexOrThrow("SessionType")) + "," + cs.getString(cs.getColumnIndexOrThrow("MuscleGroup")) + "," +
                    cs.getString(cs.getColumnIndexOrThrow("TrainerID"));
        }

        return result;
    }



    public static String viewBookedSessions(String PersonGymID) throws SQLException {
        String result = "";
        int count = 0;

        String args[] = {PersonGymID};

        Cursor cs = db.rawQuery("SELECT S.sessionID, S.sessionType, S.MuscleGroup, R.StartTime FROM session AS S, reserves AS R WHERE S.PersonGymID = ? AND S.sessionID = R.sessionID", args);

        while (cs.moveToNext()) {
            result += cs.getString(cs.getColumnIndexOrThrow("SessionID")) + "," + cs.getString(cs.getColumnIndexOrThrow("PersonGymID")) + "," +
                    cs.getString(cs.getColumnIndexOrThrow("SessionType")) + "," + cs.getString(cs.getColumnIndexOrThrow("MuscleGroup")) + "," +
                    cs.getString(cs.getColumnIndexOrThrow("TrainerID"));
            count++;
        }
        result +=","+ String.valueOf(count);
        return result;
    }




    public static String viewGymInformation()throws SQLException{
        String result = "";

        Cursor cs = db.rawQuery("SELECT * FROM gym", null);

        while(cs.moveToNext()){
            result += cs.getString(cs.getColumnIndexOrThrow("Name"))+","+cs.getString(cs.getColumnIndexOrThrow("GymID"))+","+
                    cs.getString(cs.getColumnIndexOrThrow("Phone"))+","+cs.getString(cs.getColumnIndexOrThrow("Street"))
                    +","+cs.getString(cs.getColumnIndexOrThrow("City"))+","+cs.getString(cs.getColumnIndexOrThrow("ProvState"))+","+
                    cs.getString(cs.getColumnIndexOrThrow("Postal"));
        }
        return result;
    }




    public static String viewMembershipInformation(String PersonGymID) throws SQLException{
        String result = "";

        Cursor cs = db.rawQuery("SELECT * FROM membership", null);

        while (cs.moveToNext()){
            result += cs.getString(cs.getColumnIndexOrThrow("RenewalDate"))+","+cs.getString(cs.getColumnIndexOrThrow("RenewalDate"))+","+
                    cs.getString(cs.getColumnIndexOrThrow("PoolAccess"))+","+ cs.getString(cs.getColumnIndexOrThrow("PoolAccess"));
        }
        return result;
    }




    public static void bookSession(String SID, String PID, String sessionT, String mg, String TgymID, String MFN, String RN, Date date, Time startTime, Time endTime, int weightRange)throws SQLException{

        String args1[] = {SID, PID, sessionT, mg, TgymID};
        String args2[] = {PID, MFN, RN, SID, date.toString(), startTime.toString(), endTime.toString(), ((Integer)weightRange).toString()};

        db.rawQuery("INSERT INTO Session VALUES (?,?,?,?,?)", args1);
        db.rawQuery("INSERT INTO reserves VALUES (?,?,?,?,?,?,?,?)", args2);

    }




    public static void reserveEquipment(String PID, String MFN, Date date, Time startTime, Time endTime, int weightRange) throws SQLException{

        String args[] = {PID, MFN, date.toString(), startTime.toString(), endTime.toString(), ((Integer)weightRange).toString()};

        db.rawQuery("INSERT INTO Reserves VALUES (?,?,NULL,NULL,?,?,?,?)", null);

    }




}
