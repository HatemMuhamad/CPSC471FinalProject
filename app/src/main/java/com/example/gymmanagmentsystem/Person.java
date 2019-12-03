package com.example.gymmanagmentsystem;

public class Person {
    protected String gymID;

    public Person(String id){
        gymID = id;
    }

    protected String getID(){
        return gymID;
    }
}
