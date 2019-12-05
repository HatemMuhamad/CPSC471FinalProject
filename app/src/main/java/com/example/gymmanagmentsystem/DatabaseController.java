package com.example.gymmanagmentsystem;

import android.content.Context;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class DatabaseController {

    private Connection myConnection;
    private final Context mainContext;
    private  Person person;


    public DatabaseController(Context context){
        this.mainContext = context;
    try {
        myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480_schema", "root",
                "Hatoom@1933");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public int signIn(String PersonGymID) throws SQLException {
        PreparedStatement signIn = myConnection.prepareStatement(
                "SELECT MFlag FROM person WHERE PersonGymID = ?");
        signIn.setString(1, PersonGymID);
        ResultSet rs = signIn.executeQuery();

        if (rs.next()) {
            if(rs.getInt("MFlag") == 1){
                person = new Member(PersonGymID);
            }
            else if(rs.getInt("MFlag") == 0){
                person = new Trainer(PersonGymID);
            }
            return (rs.getInt("MFlag"));
        } else {
            return 2;
        }
    }

    public void signUp(String ENC, String PID, String phone, String street, String city, String provState, String postal, int TFlag, int MFlag) throws SQLException {
        PreparedStatement CreateUser = myConnection.prepareStatement(
                "INSERT INTO person (EmergencyContactPhone, PersonGymID, Phone, Street, City, ProvState, Postal, TFlag, MFlag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        CreateUser.setString(1, ENC);
        CreateUser.setString(2, PID);
        CreateUser.setString(3, phone);
        CreateUser.setString(4, street);
        CreateUser.setString(5, city);
        CreateUser.setString(6, provState);
        CreateUser.setString(7, postal);
        CreateUser.setInt(8, TFlag);
        CreateUser.setInt(9, MFlag);
        CreateUser.executeUpdate();
    }

    public String viewAccountInformation(String personGymID) throws SQLException {
        String result = "";
        PreparedStatement viewAccountInfo = myConnection.prepareStatement(
                "SELECT P.EmergencyContactPhone P.PersonGymID, P.Phone, P.Street, P.City, P.ProvState, P.Postal, P.MFlag FROM person AS P WHERE P.PersonGymID = ? ");
        viewAccountInfo.setString(1, personGymID);
        ResultSet rs = viewAccountInfo.executeQuery();

        while (rs.next()) {
            result += rs.getString("EmergencyContactPhone") + "," + rs.getString("PersonGymID") + "," + rs.getString("Phone") + "," +
                    rs.getString("Street") + "," + rs.getString("City") + "," + rs.getString("ProvState") + "," +
                    rs.getString("Postal");
        }
        return result;
    }

    public void editAccountInformation(String ENC,String personGymID,String phone, String street, String city, String provState, String postal) throws SQLException {
        PreparedStatement updateAccountInfo = myConnection.prepareStatement(
                " UPDATE person AS P SET P.emergencyContactPhone = ?, P.Phone = ?, P.Street = ?, P.City = ?, P.ProvState = ?, P.Postal = ? WHERE P.PersonGymID = ? ");
        updateAccountInfo.setString(1, ENC);
        updateAccountInfo.setString(2, phone);
        updateAccountInfo.setString(3, street);
        updateAccountInfo.setString(4, city);
        updateAccountInfo.setString(5, provState);
        updateAccountInfo.setString(6, postal);
        updateAccountInfo.setString(7, personGymID);
        updateAccountInfo.executeUpdate();
    }

    public String viewOngoingSessions() throws SQLException {
        String result = "";
        int count = 0;
        PreparedStatement viewOngoingSession = myConnection.prepareStatement(
                "SELECT * FROM session");
        ResultSet rs = viewOngoingSession.executeQuery();
        while (rs.next()) {
            result += rs.getString("SessionID") + "," + rs.getString("SessionType") + "," +
                    rs.getString("Date") + "," +rs.getString("StartTime")+ "," +rs.getString("EndTime")+","+
                    rs.getString("MuscleGroup") + "," + rs.getString("TrainerID");
            count++;
        }
        result +=","+ count;
        return result;
    }

    public String viewBookedSessions(String memberID) throws SQLException {
        String result = "";
        int count = 0;
        PreparedStatement viewBookedSession = myConnection.prepareStatement(
                "SELECT S.sessionID, S.sessionType, S.MuscleGroup, S.TrainerID, S.StartTime FROM session AS S, books AS B WHERE B.PersonGymID = ? AND S.sessionID = B.sessionID");
        viewBookedSession.setString(1, memberID);
        ResultSet rs = viewBookedSession.executeQuery();
        while (rs.next()) {
            result += rs.getString("SessionID") + "," + rs.getString("SessionType") + "," +
                    rs.getString("MuscleGroup") + "," + rs.getString("TrainerID") + "," + rs.getString("StartTime");
            count++;
        }
        result +=","+ count;
        return result;
    }
    public String viewAssignedSessions(String trainerID) throws SQLException {
        String result = "";
        int count = 0;
        PreparedStatement viewBookedSession = myConnection.prepareStatement(
                "SELECT S.sessionID, S.sessionType, S.MuscleGroup, S.StartTime FROM session AS S WHERE S.TrainerID = ?");
        viewBookedSession.setString(1, trainerID);
        ResultSet rs = viewBookedSession.executeQuery();
        while (rs.next()) {
            result += rs.getString("SessionID") + "," + rs.getString("SessionType") + "," +
                    rs.getString("MuscleGroup") + "," + rs.getString("StartTime");
            count++;
        }
        result +=","+ count;
        return result;
    }
    public String viewGymInformation()throws SQLException{
        String result = "";
        PreparedStatement viewGymInformation = myConnection.prepareStatement(
                "SELECT * FROM gym");
        ResultSet rs = viewGymInformation.executeQuery();
        while(rs.next()){
            result += rs.getString("Name")+","+rs.getString("GymID")+","+rs.getString("Phone")+","+rs.getString("Street")
                   +","+rs.getString("City")+","+rs.getString("ProvState")+","+rs.getString("Postal");
        }
        return result;
    }
    public String viewMembershipInformation(String PersonGymID) throws SQLException{
        String result = "";
        PreparedStatement viewMembershipInformation = myConnection.prepareStatement(
                "SELECT * FROM membership");
        ResultSet rs = viewMembershipInformation.executeQuery();
        while (rs.next()){
            result += rs.getString("RenewalDate")+","+rs.getString("TanningRoomAccess")+","+rs.getString("PoolAccess")+","+
                      rs.getString("PricePerMonth");
        }
        return result;
    }
    public void bookSession(String SID, String PID)throws SQLException{
        PreparedStatement bookSession = myConnection.prepareStatement (
                "INSERT INTO books VALUES (?,?)");
        bookSession.setString(1, SID);
        bookSession.setString(2, PID);
        bookSession.executeUpdate();

    }
    public void reserveEquipment(String PID, String MFN, Date date, Time startTime, Time endTime, int weightRange) throws SQLException{
        PreparedStatement reserveEquipment = myConnection.prepareStatement (
                "INSERT INTO Reserves VALUES (?,?,NULL,NULL,?,?,?,?)");
        reserveEquipment.setString(1, PID);
        reserveEquipment.setString(2,MFN);
        reserveEquipment.setDate(3,date);
        reserveEquipment.setTime(4, startTime);
        reserveEquipment.setTime(5, endTime);
        reserveEquipment.setInt(6, weightRange);
        reserveEquipment.executeUpdate();

    }
    public Person getPerson(){
        return person;
    }
}





