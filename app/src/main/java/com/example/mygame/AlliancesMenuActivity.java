package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlliancesMenuActivity extends AppCompatActivity {

    TextView textBrief;
    static TextView topic;

    Button back;
    Button join;
    Button create;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alliances_menu);
        Bundle args = getIntent().getExtras();
        Game game = (Game) args.getSerializable("GAME");

        textBrief = findViewById(R.id.textViewBrief);
        back = findViewById(R.id.buttonBack);
        join = findViewById(R.id.buttonJoin);
        create = findViewById(R.id.buttonCreate);
        recyclerView = findViewById(R.id.recycler);
        topic = findViewById(R.id.textViewTopic);

        if (game.alliances.size() != 0){
            textBrief.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new AllianceAdapter(game));
        }
    }
}