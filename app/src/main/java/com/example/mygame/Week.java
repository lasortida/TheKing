package com.example.mygame;

import com.example.mygame.OnlineMode.Classes.StorageOnline;

import java.io.Serializable;

public class Week implements Serializable {
    private Act[] acts = new Act[3];

    public Week(StorageOnline storage){
        for(int i = 0; i < 3; ++i){
            acts[i] = storage.getRandomAct();
        }
    }

    public Act[] getActs(){
        return acts;
    }
}
