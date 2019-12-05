
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

        GymManagementController.loadDefaultRooms();
        GymManagementController.loadDefaultTrainers();
        GymManagementController.loadDefaultSessions();
        GymManagementController.loadGymInformation();
        GymManagementController.loadDefaultReservables();

    }


    public static void loadDefaultReservables (){

        Cursor cs = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345678',' SQUAT RACK ','16141621')", null);
        cs.moveToNext();
        cs.close();

        Cursor cs1 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345679',' BENCH PRESS ','16141621')", null);
        cs1.moveToNext();
        cs1.close();

        Cursor cs2 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345680',' INCLINE BENCH PRESS ','16141621')", null);
        cs2.moveToNext();
        cs2.close();

        Cursor cs3 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345681',' HAMMER STRENGTH MACHINE ','16141621')", null);
        cs3.moveToNext();
        cs3.close();

        Cursor cs4 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345682',' CABLES AND PULLEYS ','16141621')", null);
        cs4.moveToNext();
        cs4.close();

        Cursor cs5 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345683',' LAT PULLDOWN MACHINE ','16141621')", null);
        cs5.moveToNext();
        cs5.close();

        Cursor cs6 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345684',' LEG EXTENSION MACHINE ','16141621')", null);
        cs6.moveToNext();
        cs6.close();

        Cursor cs7 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345685',' LEG CURL MACHINE ','16141621')", null);
        cs7.moveToNext();
        cs7.close();

        Cursor cs8 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345686',' HYPER EXTENSION BENCH ','16141621')", null);
        cs8.moveToNext();
        cs8.close();

        Cursor cs9 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345687',' SMITH MACHINE ','16141621')", null);
        cs9.moveToNext();
        cs9.close();

//Accessories
        Cursor csa = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345697',' Resistance bands','16141621')", null);
        csa.moveToNext();
        csa.close();

        Cursor csa1 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345688',' Firm Grid Foam Roller  ','16141621')", null);
        csa1.moveToNext();
        csa1.close();

        Cursor csa2 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345689',' 70lb Cast Iron Kettlebell  ','16141621')", null);
        csa2.moveToNext();
        csa2.close();

        Cursor csa3 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345690',' Weighted jump rope  ','16141621')", null);
        csa3.moveToNext();
        csa3.close();

        Cursor csa4 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345691',' Medicine ball  ','16141621')", null);
        csa4.moveToNext();
        csa4.close();

        Cursor csa5 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345692',' Dumbbells  ','16141621')", null);
        csa5.moveToNext();
        csa5.close();

        Cursor csa6 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345693',' Kettlebell ','16141621')", null);
        csa6.moveToNext();
        csa6.close();

        Cursor csa7 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345694',' Balance ball ','16141621')", null);
        csa7.moveToNext();
        csa7.close();

        Cursor csa8 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345695',' Yoga mat ','16141621')", null);
        csa8.moveToNext();
        csa8.close();

        Cursor csa9 = db.rawQuery("INSERT INTO Reservable (\n" +
                "ManufactureNumber,\n" +
                "Description,\n" +
                "GymID)\n" +
                "VALUES ('12345696',' TRX ','16141621')", null);
        csa9.moveToNext();
        csa9.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO how should we handle an upgrade?
    }
    public static void loadGymInformation(){
        Cursor cs = db.rawQuery("INSERT INTO Gym (\n" +
                "Name ,\n" +
                "GymID ,\n" +
                "Phone ,\n" +
                "Street ,\n" +
                "City ,\n" +
                "ProvState ,\n" +
                "Postal)\n"+
                "VALUES ('Sportiva','16141621','5879664481','24 Ave NW','Calgary','Alberta','T2N 4V5')", null);
        cs.moveToNext();
        cs.close();
    }

    public static void loadDefaultRooms(){
        Cursor cs = db.rawQuery("INSERT INTO Room (\n" +
                "ActivityType,\n" +
                "RoomNumber)\n" +
                "VALUES ('Weighlifting room','1')", null);
        cs.moveToNext();
        cs.close();
        Cursor cs1 = db.rawQuery("INSERT INTO Room (\n" +
                "ActivityType,\n" +
                "RoomNumber)\n" +
                "VALUES ('Cardio room','2')", null);
        cs1.moveToNext();
        cs1.close();
        Cursor cs2 = db.rawQuery("INSERT INTO Room (\n" +
                "ActivityType,\n" +
                "RoomNumber)\n" +
                "VALUES ('Crossfit room','3')", null);
        cs2.moveToNext();
        cs2.close();
    }
    public static void loadDefaultTrainers(){
        Cursor cs = db.rawQuery("INSERT INTO Person (\n" +
                "EmergencyContactPhone ,\n" +
                "PersonGymID,\n" +
                "Phone ,\n" +
                "Street,\n" +
                "City,\n" +
                "ProvState,\n" +
                "Postal,\n" +
                "TFlag,\n" +
                "MFlag)\n" +
                "VALUES ('4482234481','7770','5879664481','24th Ave','Calgary','Alberta','T2N 4V5','1','0')", null);
        cs.moveToNext();
        cs.close();

        Cursor cs1 = db.rawQuery("INSERT INTO Person (\n" +
                "EmergencyContactPhone ,\n" +
                "PersonGymID,\n" +
                "Phone ,\n" +
                "Street,\n" +
                "City,\n" +
                "ProvState,\n" +
                "Postal,\n" +
                "TFlag,\n" +
                "MFlag)\n" +
                "VALUES ('3324441111','2011','5879663333','30th Ave','Calgary','Alberta','T2N 6V4','1','0')", null);
        cs1.moveToNext();
        cs1.close();
        Cursor cs2 = db.rawQuery("INSERT INTO Person (\n" +
                "EmergencyContactPhone ,\n" +
                "PersonGymID,\n" +
                "Phone ,\n" +
                "Street,\n" +
                "City,\n" +
                "ProvState,\n" +
                "Postal,\n" +
                "TFlag,\n" +
                "MFlag)\n" +
                "VALUES ('55452234376','3553','9663992881','10th Ave','Calgary','Alberta','T2N 3V5','1','0')", null);
        cs2.moveToNext();
        cs2.close();
        Cursor cs3 = db.rawQuery("INSERT INTO Person (\n" +
                "EmergencyContactPhone ,\n" +
                "PersonGymID,\n" +
                "Phone ,\n" +
                "Street,\n" +
                "City,\n" +
                "ProvState,\n" +
                "Postal,\n" +
                "TFlag,\n" +
                "MFlag)\n" +
                "VALUES ('5869665572','115','69629627','15th Ave','Calgary','Alberta','T2N 6V8','1','0')", null);
        cs3.moveToNext();
        cs3.close();
        Cursor cs4 = db.rawQuery("INSERT INTO Person (\n" +
                "EmergencyContactPhone ,\n" +
                "PersonGymID,\n" +
                "Phone ,\n" +
                "Street,\n" +
                "City,\n" +
                "ProvState,\n" +
                "Postal,\n" +
                "TFlag,\n" +
                "MFlag)\n" +
                "VALUES ('5558884431','5874','7774442211','31th Ave','Calgary','Alberta','T2N 5V7','1','0')", null);
        cs4.moveToNext();
        cs4.close();

    }
    public static void loadDefaultSessions(){
        Cursor cs = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('3312','08:00','WeightLifting','Biceps','7770','1')", null);
        cs.moveToNext();
        cs.close();
        Cursor cs1 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('2211','09:00','WeightLifting','Chest','3553','1')", null);
        cs1.moveToNext();
        cs1.close();
        Cursor cs2 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('5566','10:00','WeightLifting','Legs','7770','1')", null);
        cs2.moveToNext();
        cs2.close();
        Cursor cs3 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('7743','11:00','Cardio','N/A','115','2')", null);
        cs3.moveToNext();
        cs3.close();
        Cursor cs4 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('9843','12:00','Crossfit','LowerBody','115','3')", null);
        cs4.moveToNext();
        cs4.close();
        Cursor cs5 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('4476','01:00','Crossfit','UpperBody','2011','3')", null);
        cs5.moveToNext();
        cs5.close();
        Cursor cs6 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('3361','02:00','WeightLifting','Biceps','115','1')", null);
        cs6.moveToNext();
        cs6.close();
        Cursor cs7 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('6600','03:00','Cardio','N/A','2011','2')", null);
        cs7.moveToNext();
        cs7.close();
        Cursor cs8 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('1614','04:00','WeightLifting','Back','7770','1')", null);
        cs8.moveToNext();
        cs8.close();
        Cursor cs9 = db.rawQuery("INSERT INTO Session (\n" +
                "SessionID,\n" +
                "StartTime ,\n" +
                "SessionType,\n" +
                "MuscleGroup,\n"+
                "TrainerID,\n"+
                "RoomNumber)\n"+
                "VALUES ('3398','05:00','Crossfit','UpperBody','5874','3')", null);
        cs9.moveToNext();
        cs9.close();

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




    public static void bookSession(String SID, String PID, String sessionT, String mg, String TgymID, String MFN, String RN, Date date, Time startTime, Time endTime, int weightRange)throws SQLException{

        String args1[] = {SID, PID, sessionT, mg, TgymID};
        String args2[] = {PID, MFN, RN, SID, date.toString(), startTime.toString(), endTime.toString(), ((Integer)weightRange).toString()};

        Cursor cs1 = db.rawQuery("INSERT INTO Session VALUES (?,?,?,?,?)", args1);
        Cursor cs2 = db.rawQuery("INSERT INTO reserves VALUES (?,?,?,?,?,?,?,?)", args2);

        cs1.moveToFirst();
        cs1.close();
        cs2.moveToFirst();
        cs2.close();
    }




    public static void reserveEquipment(String PID, String MFN, Date date, Time startTime, Time endTime, int weightRange) throws SQLException{

        String args[] = {PID, MFN, date.toString(), startTime.toString(), endTime.toString(), ((Integer)weightRange).toString()};

        Cursor cs = db.rawQuery("INSERT INTO Reserves VALUES (?,?,NULL,NULL,?,?,?,?)", null);

        cs.moveToFirst();
        cs.close();
    }
    
    public static String getUserID(){
        return user.getID();
    }

    public static void deleteUser(){
        user = null;
    }


}
