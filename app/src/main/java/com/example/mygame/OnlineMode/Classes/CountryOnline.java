package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;
import java.util.HashMap;

public class CountryOnline implements Serializable {
    public String name;
    public int id;
    public int idOfImage;
    public int idOfMarker;
    public int relationshipValue;
    public boolean occupied;
    public double moneyStatus;
    public double armyStatus;
    public double businessStatus;
    public double workerStatus;
    public double foodStatus;
    public int idOfAlliance = -1;
    public boolean wasTrade;
    public int safe;
    public int treasure;
    public int weekOfOffer;
    public HashMap<Integer, Integer> foreignTrade;

    public CountryOnline(String name, int id, int idOfImage, int idOfMarker){
        this.name = name;
        this.id = id;
        this.idOfImage = idOfImage;
        this.idOfMarker = idOfMarker;
        occupied = false;
        moneyStatus = 0.5;
        armyStatus = 0.5;
        businessStatus = 0.5;
        workerStatus = 0.5;
        foodStatus = 0.5;
        relationshipValue = 50;
        wasTrade = false;
        idOfAlliance = -1;
        foreignTrade = new HashMap<>();
    }

    public int getFullState(){
        double[] array = {
                moneyStatus, armyStatus, businessStatus, workerStatus, foodStatus
        };
        for (int i = 0; i < array.length; ++i){
            if (array[i] >= 0.85){
                return i;
            }
        }
        return 0;
    }

    public boolean isGameLow(){
        double[] array = {
                moneyStatus, armyStatus, businessStatus, workerStatus, foodStatus
        };
        for (int i = 0; i < array.length; ++i){
            if (array[i] <= 0){
                return true;
            }
        }
        return false;
    }

    public double getOneStatus(int number){
        double[] array = {
                moneyStatus, armyStatus, businessStatus, workerStatus, foodStatus
        };
        return array[number];
    }
    public void removeOneStateOfStatus(int number){
        safe = number;
        switch (number){
            case 0:
                moneyStatus -= 0.17;
                break;
            case 1:
                armyStatus -= 0.17;
                break;
            case 2:
                businessStatus -= 0.17;
                break;
            case 3:
                workerStatus -= 0.17;
                break;
            case 4:
                foodStatus -= 0.17;
                break;
        }
    }

    public boolean ifCanTrade(){
        double[] array = {
                moneyStatus, armyStatus, businessStatus, workerStatus, foodStatus
        };
        int count = 0;
        for (int i = 0; i < array.length; ++i){
            if (array[i] <= 0.17){
                count++;
            }
        }
        if (count == array.length){
            return false;
        }
        else{
            return true;
        }
    }
}
