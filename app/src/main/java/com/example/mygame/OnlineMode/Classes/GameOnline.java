package com.example.mygame.OnlineMode.Classes;

import com.example.mygame.Country;
import com.example.mygame.Effect;
import com.example.mygame.Storage;
import com.example.mygame.Week;

import java.io.Serializable;
import java.util.ArrayList;

public class GameOnline implements Serializable {
    public String idOfRoom;
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
    public Post post;

    public GameOnline(){
        users = new ArrayList<>();
        alliances = new ArrayList<>();
        post = new Post();
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

    public ArrayList<CountryOnline> getBlankCountries(){
        ArrayList<CountryOnline> result = new ArrayList<>();
        AllianceOnline allianceOnline = users.get(yourUserCode).country.alliance;
        if (allianceOnline.countries.size() == 0){
            int except = yourCountryId;
            for (int i = 0; i < countries.length; ++i){
                if (countries[i].id != except){
                    result.add(countries[i]);
                }
            }
        }
        else{
            ArrayList<Integer> except = new ArrayList<>();
            for (int i = 0; i < users.get(yourUserCode).country.alliance.countries.size(); ++i){
                except.add(users.get(yourUserCode).country.alliance.countries.get(i).id);
            }
            for (int i = 0; i < countries.length; ++i){
                if (!except.contains(countries[i].id)){
                    result.add(countries[i]);
                }
            }
        }
        return result;
    }

    public void setWeek(){
        week = new Week(storage);
    }
}
