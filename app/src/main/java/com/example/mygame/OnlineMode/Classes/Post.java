package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;

public class Post implements Serializable {
    public double moneyStatus;
    public double armyStatus;
    public double businessStatus;
    public double workerStatus;
    public double foodStatus;

    public String nameOfOwnAlliance; // имя альянса, который вы создали
    public String nameOfAnotherAlliance; // имя альянса, в который вы вступили
    public int myAllianceInvitation; // какой стране вы отправили приглашение?
    public boolean isAllianceAccepted; // приняли ли вы приглашение в Альянс?

    public int tradeWith; // id пользователя, которому вы отправили trade
    public int tradeInvitation; // id пользователя, который отправил trade  !!!!!!!!!!!!!!
    public boolean isTradeAccepted; // приняли ли вы предложение на обмен    !!!!!!!!!!!!!!!
    public int stateUp; // то, что вы отдаёте
    public int stateDown; //то, что вы получаете;
}
