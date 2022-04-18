package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;

public class HeroesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        Bundle arguments = getIntent().getExtras();
        Game game = (Game) arguments.getSerializable("GAME");
        Hero[] heroes = game.storage.postHeroes;
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < heroes.length; ++i){
            data.add(heroes[i].getPost() + heroes[i].getName());
        }
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Adapter(game.storage.postHeroes.length, data));
    }
}