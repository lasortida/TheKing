package com.example.mygame.OnlineMode.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mygame.OnlineMode.Classes.AdapterToInvitations;
import com.example.mygame.OnlineMode.Classes.GameOnline;
import com.example.mygame.R;

public class InvitationActivity extends AppCompatActivity {

    RecyclerView recycler;
    static GameOnline game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        Bundle args = getIntent().getExtras();

        game = (GameOnline) args.getSerializable("GAME");
        AdapterToInvitations adapter = (AdapterToInvitations) args.getSerializable("ADAPTER");
        TextView dialog = findViewById(R.id.textViewDialog);
        recycler = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        Button back = findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InvitationActivity.this, GovernMenuActivityOnline.class).putExtra("GAME", game));
            }
        });
    }

    public static void setGame(GameOnline game1){
        game = game1;
    }

}