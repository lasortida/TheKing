package com.example.mygame.OnlineMode.Classes;

public class Format {
    public String id;
    public int userCode;
    public int countryId;
    public boolean start;
    public boolean error;
    public int[] users;
    public int numberOfWeek;
    public double moneyStatus;
    public double armyStatus;
    public double businessStatus;
    public double workerStatus;
    public double foodStatus;
    public boolean timerStart;
    public int timerReminder;

    public boolean isTradeAccepted;
    public int[] tradeWith;
    public int[] tradeAway;
    public int[] tradeToMe;

    public boolean next;

    public GameOnline getInitialGameOnline(){
        StorageOnline storage = new StorageOnline();
        GameOnline game = new GameOnline();
        game.idOfRoom = id;
        game.numberOfWeek = numberOfWeek;
        game.countries = storage.countries;
        game.storage = storage;
        game.setWeek();
        game.time = 90000;
        for (int i = 0; i < users.length; ++i){
            game.countries[users[i]].occupied = true;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Добро пожаловать! \n\n");
        builder.append("Заботьтесь о процветании вашей страны и её жителей! Отстаивайте свои интересы, объединяйтесь в Альянсы с другими странами и поддерживайте друг друга! \n\n");
        builder.append("Весь процесс игры разделён на игровые недели! Одна игровая неделя - 1 минута и 30 секунд реального времени!\n\n");
        builder.append("На карте мира можно рассмотреть свою страну, а также обстановку в целом!");
        game.news = builder.toString();
        game.yourUserCode = userCode;
        game.yourCountryId = countryId;
        return game;
    }
}
