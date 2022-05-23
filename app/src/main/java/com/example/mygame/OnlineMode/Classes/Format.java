package com.example.mygame.OnlineMode.Classes;

public class Format {
    public String id;
    public int userCode;
    public int usersCount;
    public int countryId;
    public boolean start;
    public boolean error;
    public int[] users;
    public String[] userNames;
    public int numberOfWeek;
    public double moneyStatus;
    public double armyStatus;
    public double businessStatus;
    public double workerStatus;
    public double foodStatus;

    public GameOnline castToGameOnline(GameOnline game){
        game.numberOfWeek = numberOfWeek;
        game.countries[game.yourCountryId].moneyStatus = moneyStatus;
        game.countries[game.yourCountryId].armyStatus = armyStatus;
        game.countries[game.yourCountryId].businessStatus = businessStatus;
        game.countries[game.yourCountryId].workerStatus = workerStatus;
        game.countries[game.yourCountryId].foodStatus = foodStatus;
        game.setWeek();
        game.time = 90000;
        return game;
    }

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
            UserOnline user = new UserOnline(i);
            user.name = userNames[i];
            user.country = storage.countries[users[i]];
            game.users.add(user);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Добро пожаловать! \n")
                .append("Список игроков! \n\n");
        for (UserOnline u: game.users){
            builder.append(u.name);
            builder.append(" - страна: ");
            builder.append(u.country.name);
            builder.append("\n");
        }
        builder.append("Заботьтесь о процветании вашей страны и её жителей! Отстаивайте свои интересы, объединяйтесь в Альянсы с другими странами и поддерживайте друг друга! \n\n");
        builder.append("Весь процесс игры разделён на игровые недели! Одна игровая неделя - 1 минута и 30 секунд реального времени!\n\n");
        builder.append("На карте мира можно рассмотреть свою страну, а также обстановку в целом!");
        game.news = builder.toString();
        game.yourUserCode = userCode;
        game.yourCountryId = countryId;
        return game;
    }
}
