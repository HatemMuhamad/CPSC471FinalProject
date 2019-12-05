package com.example.gymmanagmentsystem;

public class SingletonUser {

    private static SingletonUser user = null;

    private String gymID;
    private int MFlag;
    private int TFlag;

    public SingletonUser(String id, int MFlag, int TFlag) {
        gymID = id;
        this.MFlag = MFlag;
        this.TFlag = TFlag;
    }

    public static SingletonUser getInstance(String id, int MFlag, int TFlag){
        if(user == null){
            user = new SingletonUser(id, MFlag, TFlag);
        }

        return user;
    }


    private String getID() {
        return gymID;
    }

    private int getMFlag() {
        return MFlag;
    }

    private int getTFlag(){
        return TFlag;
    }
}
