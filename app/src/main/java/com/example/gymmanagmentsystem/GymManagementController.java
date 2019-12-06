
package com.example.gymmanagmentsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.Date;
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
                "StartTime Varchar(45),\n" +
                "SessionType Varchar(30) DEFAULT 'Generic',\n" +
                "MuscleGroup Varchar(30),\n" +
                "TrainerID Varchar(15) NOT NULL,\n" +
                "RoomNumber Varchar(15),\n" +
                "PRIMARY KEY(SessionID),\n" +
                "UNIQUE (SessionID) );");
        createSessionStatement.execute();

        SQLiteStatement createReservesStatement = sqLiteDatabase.compileStatement("CREATE TABLE Reserves (\n" +
                "PersonGymID Varchar(15) NOT NULL,\n" +
                "ManufactureNumber Varchar(15),\n" +
                "Date Varchar(45) NOT NULL,\n" +
                "StartTime Varchar(45) NOT NULL,\n" +
                "EndTime Varchar(45) NOT NULL,\n" +
                "WeightRange Varchar(45),\n" +
                "PRIMARY KEY(PersonGymID,Date,StartTime),\n" +
                "FOREIGN KEY(ManufactureNumber) REFERENCES Reservable(ManufactureNumber),\n" +
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




    public static int signUp(String ENC, String PID, String phone, String street, String city, String provState, String postal, int TFlag, int MFlag) throws SQLException {

        System.out.println("Sign Up Person");

        String args[] = {ENC, PID, phone, street, city, provState, postal, ((Integer)TFlag).toString(), ((Integer)MFlag).toString()};

        try {
            Cursor cs = db.rawQuery("INSERT INTO person (EmergencyContactPhone, PersonGymID, Phone, Street, City, ProvState, Postal, TFlag, MFlag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", args);
            cs.moveToFirst();
            cs.close();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }

        return 1;

    }



    public static Cursor viewAccountInformation(String personGymID) throws SQLException {

        String args[] = {personGymID};

        return db.rawQuery("SELECT EmergencyContactPhone, PersonGymID, Phone, Street, City, ProvState, Postal FROM Person  WHERE PersonGymID = ?", args);

    }



    public static void editAccountInformation(String ENC,String personGymID,String phone, String street, String city, String provState, String postal) throws SQLException {

        String args[] = {ENC, phone, street, city, provState, postal, personGymID};

        Cursor cs = db.rawQuery(" UPDATE Person  SET emergencyContactPhone = ?, Phone = ?, Street = ?, City = ?, ProvState = ?, Postal = ? WHERE PersonGymID = ?", args);

        cs.moveToFirst();
        cs.close();
    }
    public static Cursor viewmembership(String memberID){
        String arg[] = {memberID};
        return db.rawQuery("SELECT * FROM membership AS M, buys AS B WHERE M.PersonGymID = B.PersonGymID AND B.PersonGymID = ?", arg);
    }
    public static void loadMembership(){
            Cursor cs = db.rawQuery("INSERT INTO membership (PersonGymID,RenewalDate,TrainingRoomAccess,PoolAccess,PricePerMonth) VALUES ('0000','8/4/2020','True','True','150')",null);
            cs.moveToFirst();
            cs.close();

    }
    public static void loadBuys(){
        Cursor cs = db.rawQuery("INSERT INTO Buys (PersonGymID,MonthlyPatDate) VALUES ('0000','150')",null);
        cs.moveToFirst();
        cs.close();
    }


    public static Cursor viewOngoingSessions() throws SQLException {
        String result = "";

        return db.rawQuery("SELECT * FROM session", null);
    }



    public static Cursor viewBookedSessions(String PersonGymID) throws SQLException {

        String args[] = {PersonGymID};

        return db.rawQuery("SELECT S.sessionID,S.StartTime, S.sessionType, S.MuscleGroup, S.RoomNumber  FROM Session AS S, Books AS B WHERE B.PersonGymID = ? AND S.sessionID = B.sessionID", args);


    }


    public static Cursor viewAssignedSessions(String trainerID) throws SQLException {

        String args[] = {trainerID};

        return db.rawQuery("SELECT S.sessionID,S.StartTime, S.sessionType, S.MuscleGroup, S.RoomNumber FROM session AS S WHERE S.TrainerID = ?", args);

    }



    public static Cursor viewGymInformation()throws SQLException{

        return db.rawQuery("SELECT * FROM gym", null);

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




    public static void bookSession(String SID, String PID)throws SQLException{

        String args[] = {SID, PID};


        Cursor cs = db.rawQuery("INSERT INTO Books VALUES (?,?)", args);


        cs.moveToFirst();
        cs.close();

    }




    public static void reserveEquipment(String PID, String MFN, Date date, Time startTime, Time endTime, int weightRange) throws SQLException{

        String args[] = {PID, MFN, date.toString(), startTime.toString(), endTime.toString(), ((Integer)weightRange).toString()};

        Cursor cs = db.rawQuery("INSERT INTO Reserves VALUES (?,?,NULL,NULL,?,?,?,?)", null);

        cs.moveToFirst();
        cs.close();
    }
    public static Cursor viewAllEquipment() throws SQLException{

        return db.rawQuery("SELECT * FROM Reservable", null);
    }
    
    public static String getUserID(){
        return user.getID();
    }

    public static void deleteUser(){
        user = null;
    }


}
