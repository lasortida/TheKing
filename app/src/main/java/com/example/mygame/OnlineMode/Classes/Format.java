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

    public GameOnline castToGameOnline(GameOnline game){
        return null;
    }

    public GameOnline getInitialGameOnline(){
        StorageOnline storage = new StorageOnline();
        GameOnline game = new GameOnline();
        game.numberOfWeek = 1;
        game.countries = storage.countries;
        for (int i = 0; i < users.length; ++i){
            UserOnline user = new UserOnline(i);
            user.name = userNames[i];
            user.country = storage.countries[users[i]];
            game.users.add(user);
        }
        game.yourUserCode = userCode;
        game.yourCountryId = countryId;

        StringBuilder builder = new StringBuilder();
        builder.append("Добро пожаловать! \n")
                .append("Список игроков! \n");
        for (UserOnline u: game.users){
            builder.append(u.name);
            builder.append(" - страна: ");
            builder.append(u.country.name);
            builder.append("\n");
        }
        game.news = builder.toString();
        return game;
    }
}
