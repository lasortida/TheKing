package com.example.mygame.OnlineMode.ForServer;

import com.example.mygame.Effect;
import com.example.mygame.OnlineMode.Classes.StorageOnline;
import com.example.mygame.Week;

import java.io.Serializable;

public class Game implements Serializable {
    public String idOfRoom;
    public int numberOfWeek;
    public int usersCount;
    public User user;
    public boolean end;
    public String news;
    public boolean isJobDone;
    public boolean isGameOver;
    public Week week;
    public StorageOnline storage;

    public Game(Reply reply){
        StorageOnline storageOnline = new StorageOnline();
        idOfRoom = reply.idOfRoom;
        numberOfWeek = reply.numberOfWeek;
        usersCount = reply.usersCount;
        user = reply.user;
        user.country.idOfIcon = storageOnline.countries[reply.user.country.id].idOfImage;
        user.country.idOfMarker = storageOnline.countries[reply.user.country.id].idOfMarker;
    }

    public void takeChanges(Effect effect){
        user.country.moneyStatus += effect.getMoneyStatusChange();
        user.country.armyStatus += effect.getArmyMoodChange();
        user.country.businessStatus += effect.getBourgeoisMoodChange();
        user.country.workerStatus += effect.getWorkersMoodChange();
        user.country.foodStatus += effect.getFoodStatusChange();
    }

    public void setWeek(){
        week = new Week(storage);
    }
}
