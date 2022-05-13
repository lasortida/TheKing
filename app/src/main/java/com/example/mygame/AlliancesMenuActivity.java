package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlliancesMenuActivity extends AppCompatActivity {

    TextView textBrief;
    static TextView topic;
    static FragmentManager fragmentManager;

    Button back;

    static boolean isFragmentAllianceLoaded = false;
    static boolean isCreateFragmentLoaded = false;
    static AllianceFragment fragment;

    static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alliances_menu);
        Bundle args = getIntent().getExtras();
        Game game = (Game) args.getSerializable("GAME");
        fragmentManager = getSupportFragmentManager();

        textBrief = findViewById(R.id.textViewBrief);
        back = findViewById(R.id.buttonBack);
        recyclerView = findViewById(R.id.recycler);
        topic = findViewById(R.id.textViewTopic);

        if (game.activeAlliances.size() != 0){
            textBrief.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new AllianceAdapter(game));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFragmentAllianceLoaded){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.hide(fragment);
                    transaction.commit();
                    recyclerView.setVisibility(View.VISIBLE);
                    topic.setText("Список Альянсов");
                    isFragmentAllianceLoaded = false;
                }
                else {
                    startActivity(new Intent(AlliancesMenuActivity.this, GovernMenuActivity.class).putExtra("GAME", game));
                }
            }
        });
    }

    public static void showAlliance(Alliance alliance, int index){
        recyclerView.setVisibility(View.INVISIBLE);
        topic.setText("Альянс:");
        fragment = new AllianceFragment(alliance, index);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame, fragment);
        transaction.commit();
        isFragmentAllianceLoaded = true;
    }
}