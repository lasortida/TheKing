package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mygame.OnlineMode.Classes.AdapterToInvitations;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.OnlineMode.Classes.Note;
import com.example.mygame.R;

import java.util.ArrayList;

public class InvitationActivity extends AppCompatActivity {

    static RecyclerView recycler;
    static GameOnline game;
    static AdapterToInvitations adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        Bundle args = getIntent().getExtras();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        game = (GameOnline) args.getSerializable("GAME");
        adapter = (AdapterToInvitations) args.getSerializable("ADAPTER");
        recycler = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        new CountDownTimer(game.time, 1000){

            @Override
            public void onTick(long l) {
                game.time = l;
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(InvitationActivity.this, RestActivity.class));
            }
        }.start();

        Button back = findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InvitationActivity.this, GovernMenuActivityOnline.class).putExtra("GAME", game));
            }
        });
    }

    public static void setGame(GameOnline game1, ArrayList<Note> notes, ArrayList<Note> offerNotes){
        game = game1;
        adapter = new AdapterToInvitations(game, notes, offerNotes);
        recycler.setAdapter(adapter);
    }

}