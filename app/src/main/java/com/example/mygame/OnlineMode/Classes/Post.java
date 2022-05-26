package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;

public class Post implements Serializable {
    public double moneyStatus;
    public double armyStatus;
    public double businessStatus;
    public double workerStatus;
    public double foodStatus;

    public int tradeWith;
    public int tradeAway;
    public int tradeToMe;

    public boolean isTradeAccepted;
    public int confirmation;
}
