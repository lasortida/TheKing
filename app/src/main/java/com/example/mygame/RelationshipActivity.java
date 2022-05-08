package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RelationshipActivity extends AppCompatActivity {

    Button back;
    TextView appeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship);

        Bundle args = getIntent().getExtras();
        Game game = (Game) args.getSerializable("GAME");

        RecyclerView recyclerView = findViewById(R.id.recycler);
        back = findViewById(R.id.buttonBack);
        appeal = findViewById(R.id.textViewAppeal);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new Adapter(game.getCountries()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RelationshipActivity.this, GovernMenuActivity.class).putExtra("GAME", game));
            }
        });
        thread.start();
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            while(true){
                if (Game.isImproveNeed){
                    appeal.setText("Я уже занимаюсь делом которое вы мне поручили!");
                }
                else if(Game.moneyStatus <= 0.17){
                    appeal.setText("У вас не хватает денег!");
                }
            }
        }
    };

}