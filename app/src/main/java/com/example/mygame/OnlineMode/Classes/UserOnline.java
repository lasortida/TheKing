package com.example.mygame.OnlineMode.Classes;

import java.io.Serializable;

public class UserOnline implements Serializable {
    public int code;
    public String name;
    public CountryOnline country;

    public UserOnline(int code){
        this.code = code;
    }
}
