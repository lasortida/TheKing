package com.example.mygame;

import java.io.Serializable;

public class Operation implements Serializable {
    private String title;

    public Operation(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}