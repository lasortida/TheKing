package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;

public class Post implements Serializable {
    public double moneyStatus;
    public double armyStatus;
    public double businessStatus;
    public double workerStatus;
    public double foodStatus;

    public int tradeWith = -1;
    public int tradeAway = -1;
    public int tradeToMe = -1;

    public boolean isTradeAccepted;
    public int confirmation;
}
