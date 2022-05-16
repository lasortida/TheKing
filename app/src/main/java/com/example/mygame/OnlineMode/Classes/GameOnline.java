package com.example.mygame.OnlineMode.Classes;

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

    public GameOnline(){
        users = new ArrayList<>();
        alliances = new ArrayList<>();
    }
}
