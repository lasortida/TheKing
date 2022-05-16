package com.example.mygame.OnlineMode.Classes;

import com.example.mygame.Alliance;

import java.io.Serializable;

public class CountryOnline implements Serializable {
    public String name;
    public int id;
    boolean occupied;
    double moneyStatus;
    double armyStatus;
    double businessStatus;
    double workerStatus;
    double foodStatus;

    Alliance alliance;

    public CountryOnline(String name, int id){
        this.name = name;
        this.id = id;
        occupied = false;
        moneyStatus = 0.5;
        armyStatus = 0.5;
        businessStatus = 0.5;
        workerStatus = 0.5;
        foodStatus = 0.5;
    }
}
