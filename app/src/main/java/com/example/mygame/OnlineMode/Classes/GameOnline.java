package com.example.mygame.OnlineMode.Classes;

import com.example.mygame.Effect;
import com.example.mygame.Storage;
import com.example.mygame.Week;

import java.io.Serializable;
import java.util.ArrayList;

public class GameOnline implements Serializable {
    public int numberOfWeek;
    public String news;
    public CountryOnline[] countries;
    public ArrayList<UserOnline> users;
    public int yourUserCode;
    public int yourCountryId;
    public ArrayList<AllianceOnline> alliances;
    public boolean isJobDone;
    public boolean isGameOver;
    public Week week;
    public StorageOnline storage;
    public long time;

    public GameOnline(){
        users = new ArrayList<>();
        alliances = new ArrayList<>();
    }

    public void takeChanges(Effect effect){
        countries[yourCountryId].moneyStatus += effect.getMoneyStatusChange();
        countries[yourCountryId].armyStatus += effect.getArmyMoodChange();
        countries[yourCountryId].businessStatus += effect.getBourgeoisMoodChange();
        countries[yourCountryId].workerStatus += effect.getWorkersMoodChange();
        countries[yourCountryId].foodStatus += effect.getFoodStatusChange();

        CountryOnline country = countries[yourCountryId];
        double[] chars = {
                country.moneyStatus, country.armyStatus, country.businessStatus, country.workerStatus, country.foodStatus
        };
        for(int i = 0; i < chars.length; ++i){
            if (chars[i] <= 0 || chars[i] >= 0.85){
                isGameOver = true;
                break;
            }
        }
    }

    public CountryOnline[] getListOfForeignCountries(){
        CountryOnline[] result = new CountryOnline[countries.length - 1];
        int j = 0;
        for (int i = 0; i < countries.length; ++i){
            if (countries[i].id != yourCountryId){
                result[j] = countries[i];
                j++;
            }
        }
        return result;
    }

    public void setWeek(){
        week = new Week(storage);
    }
}
